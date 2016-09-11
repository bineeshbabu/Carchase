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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    List<CarDetails> carList = new ArrayList<>();
    CarAdapter adapter;
    Button removeAll;
    RelativeLayout compareLayout;
    private Button sort_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.carList);
        removeAll = (Button) findViewById(R.id.remove_all_btn);
        sort_btn = (Button) findViewById(R.id.sort_btn);
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
                        .items("Price - Highest","Price - Lowest" ,"Distance - Nearest")
                        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                                return true;
                            }
                        })
                        .widgetColor(getResources().getColor(R.color.Blue))
                        .positiveColor(getResources().getColor(R.color.Blue))
                        .positiveText("Choose")
                        .show();
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
