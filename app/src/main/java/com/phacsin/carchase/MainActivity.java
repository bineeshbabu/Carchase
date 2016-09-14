package com.phacsin.carchase;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    List<CarDetails> carList = new ArrayList<>();
    CarAdapter adapter;
    Button removeAll;
    RelativeLayout compareLayout;
    private Button sort_btn,filter_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.carList);
        removeAll = (Button) findViewById(R.id.remove_all_btn);
        sort_btn = (Button) findViewById(R.id.sort_btn);
        filter_btn = (Button) findViewById(R.id.filter_btn);
        compareLayout = (RelativeLayout) findViewById(R.id.compare_rellayout);
        toolbar.setTitle("New Cars");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        createList();
        adapter = new CarAdapter(carList,this);
        recyclerView.setAdapter(adapter);
        sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this)
                        .title("Sort By")
                        .items("Price - Lowest","Price - Highest")
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                try {
                                    if (text.equals("Price - Highest"))
                                        Collections.sort(carList, new MyPriceyComp());
                                    else {
                                        Collections.sort(carList, new MyPriceyComp());
                                        Collections.reverse(carList);
                                    }
                                    adapter.notifyDataSetChanged();
                                }catch (NullPointerException e)
                                {
                                    Log.d("exception",e.toString());
                                }
                                return true;
                            }
                        })
                        .widgetColor(getResources().getColor(R.color.Blue))
                        .positiveColor(getResources().getColor(R.color.Blue))
                        .positiveText("Choose")
                        .show();
            }
        });
        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FilterActivity.class));
            }
        });
        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareLayout.setVisibility(View.GONE);
                clearData();
                createList();
                adapter.CompareList.clear();
                adapter.notifyItemRangeInserted(0,carList.size());
            }
        });

    }

    private void createList() {
        String URL = "http://phacsin.com/cars/cars_all.php";
        JsonArrayRequest strReq = new JsonArrayRequest(Request.Method.GET,
                URL,null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("response", response.toString());
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject json = response.getJSONObject(i);
                        CarDetails carDetails = new CarDetails();
                        carDetails.name=json.getString("name");
                        carDetails.make=json.getString("make");
                        carDetails.price=json.getString("price");
                        carDetails.id=json.getString("id");
                        carDetails.image = "http://phacsin.com/cars/car_images/" + json.getString("image_url");
                        carList.add(carDetails);
                    }
                    adapter.notifyItemRangeInserted(0,response.length());
                }catch (JSONException e)
                {
                    Log.d("json_error", response.toString());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("vError", "Error: " + error.getMessage());
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
                                createList();
                            }
                        }).show();
            }

        });

// Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(strReq);
    }

    class MyPriceyComp implements Comparator<CarDetails> {

        @Override
        public int compare(CarDetails c1, CarDetails c2) {
            if(Float.parseFloat(c1.price) < Float.parseFloat(c2.price)){
                return 1;
            } else {
                return -1;
            }
        }
    }

    public void clearData() {
        int size = this.carList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.carList.remove(0);
            }

            adapter.notifyItemRangeRemoved(0, size);
        }
    }
}
