package com.phacsin.carchase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

/**
 * Created by GD on 9/14/2016.
 */
public class FilterResultActivity extends AppCompatActivity {

    private RecyclerView activeRecyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_result);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        activeRecyclerView = (RecyclerView) findViewById(R.id.activeList);
    }
}
