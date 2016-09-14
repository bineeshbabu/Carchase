package com.phacsin.carchase.details;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.phacsin.carchase.R;
import com.phacsin.carchase.fragments.PerformanceFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bineesh P Babu on 12-09-2016.
 */
public class ViewPagerSpecs extends AppCompatActivity {
    PerformanceFragment performance;
    Bundle args;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private SmartTabLayout viewPagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_specs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Specifications");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.arrows);
        //upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        getDetails();
    }

    private void getDetails() {
        String URL = "http://phacsin.com/cars/getspecs.php?id="+getIntent().getStringExtra("id");
        Log.d("url",URL);
        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.GET,
                URL,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                    adapter = new MyPagerAdapter(getSupportFragmentManager(),response);
                    viewPager.setAdapter(adapter);
                    viewPagerTab.setViewPager(viewPager);
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
                                getDetails();
                            }
                        }).show();
            }

        });

// Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(strReq);
    }


    public  class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 5;
        JSONObject response;
        public MyPagerAdapter(FragmentManager fragmentManager,JSONObject response) {
            super(fragmentManager);
            this.response=response;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    args = new Bundle();
                    args.putString("json",response.toString());
                    performance = new PerformanceFragment();
                    performance.setArguments(args);
                    return performance;
                case 1:
                    args = new Bundle();
                    args.putString("json",response.toString());
                    performance = new PerformanceFragment();
                    performance.setArguments(args);
                    return performance;
                case 2:
                    args = new Bundle();
                    args.putString("json",response.toString());
                    performance = new PerformanceFragment();
                    performance.setArguments(args);
                    return performance;
                case 3:
                    args = new Bundle();
                    args.putString("json",response.toString());
                    performance = new PerformanceFragment();
                    performance.setArguments(args);
                    return performance;
                case 4:
                    args = new Bundle();
                    args.putString("json",response.toString());
                    performance = new PerformanceFragment();
                    performance.setArguments(args);
                    return performance;
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Perfromance";
                case 1:
                    return "Comfort";
                case 2:
                    return "Safety";
                case 3:
                    return "Capacity";
                case 4:
                    return "Others";
                default:
                    return null;
            }
        }

    }

}
