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
public class FilterStyleResultActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RelativeLayout style_1,style_2,style_3,style_4,style_5;
    DBHandler dbhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_style);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Filter by Price");
        toolbar.setNavigationIcon(R.drawable.md_nav_back);
        style_1 = (RelativeLayout) findViewById(R.id.style_1);
        style_2 = (RelativeLayout) findViewById(R.id.style_2);
        style_3 = (RelativeLayout) findViewById(R.id.style_3);
        style_4 = (RelativeLayout) findViewById(R.id.style_4);
        style_5 = (RelativeLayout) findViewById(R.id.style_5);

        dbhandler = new DBHandler(getApplicationContext());
        style_1.setOnClickListener(this);
        style_2.setOnClickListener(this);
        style_3.setOnClickListener(this);
        style_4.setOnClickListener(this);
        style_5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.style_1:
                dbhandler.insertActiveFilter("Style","Hatchback");
                dbhandler.removeInactiveFilter("Style");
                break;
            case R.id.style_2:
                dbhandler.insertActiveFilter("Style","Sedan");
                dbhandler.removeInactiveFilter("Style");
                break;
            case R.id.style_3:
                dbhandler.insertActiveFilter("Style","Coupe");
                dbhandler.removeInactiveFilter("Style");
                break;
            case R.id.style_4:
                dbhandler.insertActiveFilter("Style","SUV");
                dbhandler.removeInactiveFilter("Style");
                break;
            case R.id.style_5:
                dbhandler.insertActiveFilter("Style","MUV");
                dbhandler.removeInactiveFilter("Style");
                break;
        }
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}