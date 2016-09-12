package com.phacsin.carchase.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.phacsin.carchase.MainActivity;
import com.phacsin.carchase.R;
import com.phacsin.carchase.tab.VersionDetails;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {
    ExpandableHeightListView listView;
    ImageView back;
    private Button btn_on_road_price,emi_bt,version_bt,spec_details;
    private int[] Image = {R.drawable.p1,R.drawable.p2, R.drawable.p3};

    private String[] Title ={"Mandi","Lissa","Clark"};

    private String[] description = {"Nice car","Beutifull "," awesome "};

    private String[] Ratings ={"5","3.2","4.5"};
    private ArrayList<Beandetail> beans;
    private ListViewBaseAdapterDetail listViewBaseAdapterDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_car_details);
        btn_on_road_price =(Button) findViewById(R.id.checkonroad_price);
        emi_bt =(Button) findViewById(R.id.emi_button);
        version_bt =(Button) findViewById(R.id.version_view_button_details);
        spec_details = (Button)findViewById(R.id.speci_detail_button);


        back = (ImageView)findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
        spec_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DetailActivity.this,ViewPagerSpecs.class);
                startActivity(it);
            }
        });

        // checking on road price using selected places

        btn_on_road_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(DetailActivity.this)
                        .customView(R.layout.details_on_road_price, false)
                        .show();
            }
        });

        // emi details of the car selected

        emi_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(DetailActivity.this)
                        .customView(R.layout.details_emi_prices, false)
                        .show();
            }
        });


        // related version of the car selected


        version_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DetailActivity.this,VersionDetails.class);
                startActivity(it);
            }
        });


        ExpandableHeightListView listview = (ExpandableHeightListView) findViewById(R.id.listview);
        beans= new ArrayList<Beandetail>();

        for (int i= 0; i< Title.length; i++) {

            Beandetail beanclass = new Beandetail(Image[i],Title[i],description[i],Ratings[i]);
            beans.add(beanclass);

        }
        listViewBaseAdapterDetail = new ListViewBaseAdapterDetail(DetailActivity.this, beans);

        listview.setAdapter(listViewBaseAdapterDetail);

    }
}
