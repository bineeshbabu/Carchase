package com.phacsin.carchase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    List<CarDetails> carList = new ArrayList<>();
    CarAdapter adapter;
    Button removeAll;
    RelativeLayout compareLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.carList);
        removeAll = (Button) findViewById(R.id.remove_all_btn);
        compareLayout = (RelativeLayout) findViewById(R.id.compare_rellayout);
        toolbar.setTitle("New Cars");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        createList();
        adapter = new CarAdapter(carList,this);
        recyclerView.setAdapter(adapter);
        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareLayout.setVisibility(View.GONE);
                clearData();
                createList();
                adapter.notifyItemRangeInserted(0,carList.size());
            }
        });
    }

    private void createList() {
        for(int i=0;i<10;i++)
        {
            CarDetails details = new CarDetails();
            details.name = "BMW " + i;
            carList.add(details);
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
