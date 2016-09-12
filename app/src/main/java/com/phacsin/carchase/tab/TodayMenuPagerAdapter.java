package com.phacsin.carchase.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.phacsin.carchase.details.VersionDiesel;
import com.phacsin.carchase.details.VersionElectric;
import com.phacsin.carchase.details.VersionPetrol;


/**
 * Created by one on 31/3/16.
 */
public class TodayMenuPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public TodayMenuPagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0 :

                VersionPetrol petrol_fragment = new VersionPetrol();
                return petrol_fragment;

            case 1 :

                VersionDiesel diesel_fragment = new VersionDiesel();
                return diesel_fragment;

            case 2 :

                VersionElectric electric_fragment = new VersionElectric();
                return electric_fragment;

        }

    return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
