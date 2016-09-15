package com.phacsin.carchase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by GD on 9/15/2016.
 */
public class FilterPriceResultActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RelativeLayout range_1,range_2,range_3,range_4;
    DBHandler dbhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_price);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Filter by Price");
        toolbar.setNavigationIcon(R.drawable.md_nav_back);
        range_1 = (RelativeLayout) findViewById(R.id.price_range_1);
        range_2 = (RelativeLayout) findViewById(R.id.price_range_2);
        range_3 = (RelativeLayout) findViewById(R.id.price_range_3);
        range_4 = (RelativeLayout) findViewById(R.id.price_range_4);
        dbhandler = new DBHandler(getApplicationContext());
        range_1.setOnClickListener(this);
        range_2.setOnClickListener(this);
        range_3.setOnClickListener(this);
        range_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.price_range_1:
                dbhandler.insertActiveFilter("Price","1 Lakh to 5 Lakhs");
                dbhandler.removeInactiveFilter("Price");
                break;
            case R.id.price_range_2:
                dbhandler.insertActiveFilter("Price","5 Lakhs to 10 Lakhs");
                dbhandler.removeInactiveFilter("Price");
                break;
            case R.id.price_range_3:
                dbhandler.insertActiveFilter("Price","10 Lakhs to 20 Lakhs");
                dbhandler.removeInactiveFilter("Price");
                break;
            case R.id.price_range_4:
                dbhandler.insertActiveFilter("Price","20 Lakhs to 50 Lakhs");
                dbhandler.removeInactiveFilter("Price");
                break;
        }
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
