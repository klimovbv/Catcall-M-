package com.spb.kbv.catcallm.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spb.kbv.catcallm.R;

public class MapFragment extends BaseFragment {

    private GoogleMap map;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("myLogs", "in onCreateView map fragment");

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("myLogs", "in onViewCreated map fragment");
    }

    private void setupMapIfNeeded() {
        Log.d("myLogs", "in setupMapIfNeeded map fragment");
        if (map == null) {
            Log.d("myLogs", "map == null");
            map = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
        if(map != null) {
            setupMap();
        }
    }

    @Override
    public void onResume() {
        Log.d("myLogs", "in onResume map fragment");
        super.onResume();
        setupMapIfNeeded();
    }

    private void setupMap() {
        Log.d("myLogs", "in setupMap fragment");
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Hello world"));//
    }
}
