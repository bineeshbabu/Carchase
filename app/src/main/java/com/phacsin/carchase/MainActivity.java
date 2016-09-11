package com.phacsin.carchase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.carList);
        toolbar.setTitle("New Cars");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        CarAdapter adapter = new CarAdapter(createList(),getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    private List<CarDetails> createList() {
        List<CarDetails> carList = new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            CarDetails details = new CarDetails();
            details.name = "BMW " + i;
            carList.add(details);
        }
        return carList;
    }
}
