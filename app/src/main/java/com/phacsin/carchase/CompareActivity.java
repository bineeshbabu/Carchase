package com.phacsin.carchase;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

/**
 * Created by GD on 9/15/2016.
 */
public class CompareActivity extends AppCompatActivity {

    TextView car_1,car_2,price_1,price_2,displacement_1,displacement_2,torque_1,torque_2,power_1,power_2;
    TextView drivetrain_1,drivetrain_2,capacity_1,capacity_2,speed_1,speed_2,transmission_1,transmission_2;
    String id1,id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        car_1  = (TextView) findViewById(R.id.car_1);
        car_2  = (TextView) findViewById(R.id.car_2);
        price_1  = (TextView) findViewById(R.id.price_1);
        price_2  = (TextView) findViewById(R.id.price_2);
        displacement_1  = (TextView) findViewById(R.id.displacement_1);
        displacement_2  = (TextView) findViewById(R.id.displacement_2);
        torque_1  = (TextView) findViewById(R.id.torque_1);
        torque_2  = (TextView) findViewById(R.id.torque_2);
        power_1  = (TextView) findViewById(R.id.power_1);
        power_2  = (TextView) findViewById(R.id.power_2);
        drivetrain_1  = (TextView) findViewById(R.id.drivetrain_1);
        drivetrain_2  = (TextView) findViewById(R.id.drivetrain_2);
        capacity_1  = (TextView) findViewById(R.id.capacity_1);
        capacity_2  = (TextView) findViewById(R.id.capacity_2);
        speed_1  = (TextView) findViewById(R.id.top_speed_1);
        speed_2  = (TextView) findViewById(R.id.top_speed_2);
        transmission_1  = (TextView) findViewById(R.id.transmission_1);
        transmission_2  = (TextView) findViewById(R.id.transmission_2);

        car_1.setText(getIntent().getStringExtra("car_1_name"));
        car_2.setText(getIntent().getStringExtra("car_2_name"));
        price_1.setText(getIntent().getStringExtra("car_1_price"));
        price_2.setText(getIntent().getStringExtra("car_2_price"));

        id1=getIntent().getStringExtra("car_1_id");
        id2=getIntent().getStringExtra("car_2_id");

        createList();
    }

    private void createList() {
        String URL = "http://phacsin.com/cars/car_compare.php?id1="+id1+"&id2="+id2;
        JsonArrayRequest strReq = new JsonArrayRequest(Request.Method.GET,
                URL,null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("response", response.toString());
                    JSONObject json1 = response.getJSONObject(0);
                    JSONObject json2 = response.getJSONObject(1);
                    displacement_1.setText(json1.getString("displacement"));
                    displacement_2.setText(json2.getString("displacement"));
                    power_1.setText(json1.getString("power"));
                    power_2.setText(json2.getString("power"));
                    torque_1.setText(json1.getString("torque"));
                    torque_2.setText(json2.getString("torque"));
                    capacity_1.setText(json1.getString("tank_capacity"));
                    capacity_2.setText(json2.getString("tank_capacity"));
                    speed_1.setText(json1.getString("top_speed"));
                    speed_2.setText(json2.getString("top_speed"));
                    drivetrain_1.setText(json1.getString("drivetrain"));
                    drivetrain_2.setText(json2.getString("drivetrain"));
                    transmission_1.setText(json1.getString("transmission"));
                    transmission_2.setText(json2.getString("transmission"));
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
}
