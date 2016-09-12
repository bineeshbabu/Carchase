package com.phacsin.carchase.details;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.phacsin.carchase.R;

/**
 * Created by Bineesh P Babu on 12-09-2016.
 */
public class ViewPagerSpecs extends AppCompatActivity {
    ViewPagerSpecFragement comfort,safety,capacity,performance,others;
    Bundle args[] = new Bundle[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_specs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menuToolbar);
        toolbar.setTitle("Specifications");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.arrows);
        //upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        for(int i=0;i<5;i++)
            args[i] = new Bundle(i);
        args[0].putString("category","talks");
        performance = new ViewPagerSpecFragement();
        performance.setArguments(args[0]);

        args[1].putString("category","workshop");
        comfort = new ViewPagerSpecFragement();
        comfort.setArguments(args[1]);

        args[2].putString("category","panel");
        safety = new ViewPagerSpecFragement();
        safety.setArguments(args[2]);

        args[3].putString("category","competition");
        capacity = new ViewPagerSpecFragement();
        capacity.setArguments(args[3]);

        args[4].putString("category","competition");
        others = new ViewPagerSpecFragement();
        others.setArguments(args[4]);


        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }


    public  class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 5;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
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
                    return performance;
                case 1:
                    return comfort;
                case 2:
                    return safety;
                case 3:
                    return capacity;
                case 4:
                    return others;
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
