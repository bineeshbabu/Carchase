package com.phacsin.carchase.details;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phacsin.carchase.R;

import java.util.List;

/**
 * Created by Bineesh P Babu on 12-09-2016.
 */
public class ViewPagerSpecFragement extends android.support.v4.app.Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.detail_spec_fragment, container, false);
        return view;
    }
}