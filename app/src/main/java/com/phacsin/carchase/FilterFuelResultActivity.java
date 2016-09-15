package com.phacsin.carchase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by GD on 9/15/2016.
 */
public class FilterFuelResultActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RelativeLayout fuel_1,fuel_2,fuel_3;
    DBHandler dbhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_fuel);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Filter by Price");
        toolbar.setNavigationIcon(R.drawable.md_nav_back);
        fuel_1 = (RelativeLayout) findViewById(R.id.fuel_1);
        fuel_2 = (RelativeLayout) findViewById(R.id.fuel_2);
        fuel_3 = (RelativeLayout) findViewById(R.id.fuel_3);
        dbhandler = new DBHandler(getApplicationContext());
        fuel_1.setOnClickListener(this);
        fuel_2.setOnClickListener(this);
        fuel_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fuel_1:
                dbhandler.insertActiveFilter("Fuel","Petrol");
                dbhandler.removeInactiveFilter("Fuel");
                break;
            case R.id.fuel_2:
                dbhandler.insertActiveFilter("Fuel","Diesel");
                dbhandler.removeInactiveFilter("Fuel");
                break;
            case R.id.fuel_3:
                dbhandler.insertActiveFilter("Fuel","Hybrid");
                dbhandler.removeInactiveFilter("Fuel");
                break;
        }
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}