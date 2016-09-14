package com.phacsin.carchase.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phacsin.carchase.R;

import java.util.ArrayList;

/**
 * Created by Bineesh P Babu on 12-09-2016.
 */
public class VersionPetrol extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.version_details, container, false);

        return view;

    }
}