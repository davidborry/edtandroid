package com.example.david.edt.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.david.edt.R;

/**
 * Created by david on 09/10/17.
 */

public class EDTWeekFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_edt_week, container, false);
        return viewGroup;
    }
}
