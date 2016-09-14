package com.phacsin.carchase.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.phacsin.carchase.CarDetails;
import com.phacsin.carchase.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bineesh P Babu on 12-09-2016.
 */
public class PerformanceFragment extends Fragment {

    TextView power,torque,drivetrain,top_speed,displacement,transmission;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.spec_performance, container, false);
        power = (TextView) view.findViewById(R.id.power);
        torque = (TextView) view.findViewById(R.id.torque);
        top_speed = (TextView) view.findViewById(R.id.top_speed);
        displacement = (TextView) view.findViewById(R.id.displacement);
        drivetrain = (TextView) view.findViewById(R.id.drivetrain);
        transmission = (TextView) view.findViewById(R.id.transmission);
        Bundle bundle = getArguments();
        try {
            JSONObject jsonObject = new JSONObject(bundle.getString("json"));
            power.setText(jsonObject.getString("power"));
            torque.setText(jsonObject.getString("torque"));
            top_speed.setText(jsonObject.getString("top_speed"));
            displacement.setText(jsonObject.getString("displacement"));
            drivetrain.setText(jsonObject.getString("drivetrain"));
            transmission.setText(jsonObject.getString("transmission"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }


}