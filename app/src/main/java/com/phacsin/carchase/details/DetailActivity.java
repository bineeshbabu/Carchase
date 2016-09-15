package com.phacsin.carchase.details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.phacsin.carchase.ColorAdapter;
import com.phacsin.carchase.ColorDetails;
import com.phacsin.carchase.MainActivity;
import com.phacsin.carchase.R;
import com.phacsin.carchase.tab.VersionDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    ExpandableHeightListView listView;
    ImageView back;
    private Button btn_on_road_price,emi_bt,version_bt,spec_details;
    private int[] Image = {R.drawable.p1,R.drawable.p2, R.drawable.p3};

    private String[] Title ={"Mandi","Lissa","Clark"};

    private String[] description = {"Nice car","Beutifull "," awesome "};

    private String[] Ratings ={"5","3.2","4.5"};
    private ArrayList<Beandetail> beans;
    private ListViewBaseAdapterDetail listViewBaseAdapterDetail;
    private ImageView image;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView car_name,price,fuel_type;
    private RecyclerView colorView;
    List<ColorDetails> colorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_car_details);
        btn_on_road_price =(Button) findViewById(R.id.checkonroad_price);
        emi_bt =(Button) findViewById(R.id.emi_button);
        spec_details = (Button)findViewById(R.id.speci_detail_button);
        image =(ImageView) findViewById(R.id.car_image);
        colorView =(RecyclerView) findViewById(R.id.color_view);
        Bundle extras = getIntent().getExtras();
        car_name =(TextView) findViewById(R.id.name);
        price =(TextView) findViewById(R.id.price);
        fuel_type =(TextView) findViewById(R.id.fuel_type);
        car_name.setText(getIntent().getStringExtra("name"));
        price.setText("₹ " + getIntent().getStringExtra("price") + " Lakhs");
        fuel_type.setText(getIntent().getStringExtra("fuel"));
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        colorView.setLayoutManager(horizontalLayoutManager);
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
        image.setImageBitmap(bmp);

        back = (ImageView)findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
        spec_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DetailActivity.this,ViewPagerSpecs.class);
                it.putExtra("id",getIntent().getStringExtra("id"));
                startActivity(it);
            }
        });

        // checking on road price using selected places

        btn_on_road_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog materialDialog = new MaterialDialog.Builder(DetailActivity.this)
                        .customView(R.layout.details_on_road_price, false)
                        .show();
                View view = materialDialog.getView();
                TextView rto,insurance,on_road,showroom;
                rto = (TextView) view.findViewById(R.id.id_rto_price);
                showroom = (TextView) view.findViewById(R.id.ex_showroom_text_price);
                on_road = (TextView) view.findViewById(R.id.on_road);
                insurance = (TextView) view.findViewById(R.id.insurance_text_price);
                float sh = Float.parseFloat(getIntent().getStringExtra("price"));
                showroom.setText(String.valueOf(sh*100000));
                rto.setText("33228");
                insurance.setText("18485");
                on_road.setText(String.valueOf((sh*100000)+33228+18485));
            }
        });

        // emi details of the car selected

        emi_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(DetailActivity.this)
                        .customView(R.layout.details_emi_prices, false)
                        .show();
            }
        });
        getColors();
    }

    private void getColors() {
        String URL = "http://phacsin.com/cars/getcolors.php?id="+getIntent().getStringExtra("id");
        Log.d("url",URL);
        JsonArrayRequest strReq = new JsonArrayRequest(Request.Method.GET,
                URL,null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", response.toString());
                try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject json = response.getJSONObject(i);
                    ColorDetails color = new ColorDetails();
                    color.name=json.getString("colour");
                    color.value=json.getString("colour_value");
                    colorList.add(color);
                }
                    ColorAdapter adapter = new ColorAdapter(colorList);
                    colorView.setAdapter(adapter);
               } catch (JSONException e) {
                    Log.d("json_error", e.toString());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("vError", "Error: " + error.toString());
                String errorMsg;
                if(error instanceof NoConnectionError)
                    errorMsg = "Network Error";
                else if(error instanceof TimeoutError)
                    errorMsg = "Timeout Error";
                else
                    errorMsg = "Unknown Error";
                Snackbar.make(findViewById(android.R.id.content), errorMsg, Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getColors();
                            }
                        }).show();
            }

        });

// Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(strReq);
    }
}
