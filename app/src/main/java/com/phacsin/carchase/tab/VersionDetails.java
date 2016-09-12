package com.phacsin.carchase.tab;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.phacsin.carchase.R;



public class VersionDetails extends AppCompatActivity {
    private ViewPager versionpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version_main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menuToolbar);
        toolbar.setTitle("Related Versions");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.arrows);

        //upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        versionpage = (ViewPager)findViewById(R.id.versionpager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.versiontablayout);

        tabLayout.addTab(tabLayout.newTab().setText("Petrol"));
        tabLayout.addTab(tabLayout.newTab().setText("Diesel"));
        tabLayout.addTab(tabLayout.newTab().setText("Electric"));


        TodayMenuPagerAdapter myPagerAdapter = new TodayMenuPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        versionpage.setAdapter(myPagerAdapter);
        versionpage.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                versionpage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
    }


}
