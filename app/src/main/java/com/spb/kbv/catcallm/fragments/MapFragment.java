package com.spb.kbv.catcallm.fragments;


import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.spb.kbv.catcallm.R;

public class MapFragment extends BaseFragment implements View.OnClickListener {

    private GoogleMap map;
    public View drawer;
    private boolean isOpen;
    private AnimatorSet currentAnimation;
    private TextView showInfoButton;
    private int translateOffset;

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
        showInfoButton = (TextView) view.findViewById(R.id.fragment_map_info_button);
        drawer = view.findViewById(R.id.fragment_map_drawer);
        drawer.setOnClickListener(this);
        isOpen = false;
    }

    private void setupMapIfNeeded() {
        Log.d("myLogs", "in setupMapIfNeeded map fragment");

        IconGenerator iconFactory = new IconGenerator(getContext());
        IconGenerator iconFactorySign = new IconGenerator(getContext());

        /*if (map == null) {*/
            Log.d("myLogs", "map == null");
            map = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        /*}*/
        if(map != null) {
            map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    Toast.makeText(getContext(), "Marker pressed", Toast.LENGTH_LONG).show();
                    translateOffset = drawer.getHeight() - showInfoButton.getHeight();
                    Log.d("myLogs", drawer.getHeight() + " / " + showInfoButton.getHeight());
                    drawer.setTranslationY(translateOffset);
                    drawer.setVisibility(View.VISIBLE);
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }
            });
            /*setupMap();*/
            iconFactory.setBackground(null);
            iconFactorySign.setBackground(null);
            View myView = View.inflate(getContext(), R.layout.marker, null);
            View text = View.inflate(getContext(), R.layout.marker_text, null);
            TextView textView = (TextView)text.findViewById(R.id.marker_text);
            textView.setText("Restaurant");
            /*ImageView image = (ImageView)myView.findViewById(R.id.marker_image);
            Log.d("myLogs", String.valueOf((float)(image.getWidth())) + " / " + String.valueOf(myView.getWidth()));
            text.setText("Restaurant");
            Log.d("myLogs", String.valueOf((float)(image.getWidth())) + " / " + String.valueOf(myView.getWidth()));
            float anchorOffset =  ((float)(image.getWidth()) * 0.5f) / (float)(myView.getWidth());*/
            iconFactory.setContentView(myView);
            iconFactorySign.setContentView(text);
            addIcon(iconFactory, "Default", new LatLng(-33.8696, 151.2094), iconFactorySign);
            /*setupMap();*/
            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    drawer.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private void addIcon(IconGenerator iconFactory, String text, LatLng position, IconGenerator iconFactorySign) {

        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon()))
                .position(position)
                .anchor(0.5f, 1)
                .title("Privet");

        map.addMarker(markerOptions);

        MarkerOptions markerOptionsText = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(iconFactorySign.makeIcon()))
                .position(position)
                .anchor(0, 1)
                .alpha(20);
        map.addMarker(markerOptionsText);
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
                .position(new LatLng(-33.8696, 151.2094))
                .title("Hello world"));//
    }

    @Override
    public void onClick(View view) {
        isOpen =! isOpen;

        if (currentAnimation != null) {
            currentAnimation.cancel();
        }

        int currentBackgroudColor = ((ColorDrawable)drawer.getBackground()).getColor();
        int translationY, color;

        if (isOpen){
            translationY = 0;
            color = Color.parseColor("#EE1998FC");
            showInfoButton.setText("Close");
        } else {
            translationY = drawer.getHeight() - showInfoButton.getHeight();
            color = Color.parseColor("#221998FC");
            showInfoButton.setText("Show Info");
        }

        ObjectAnimator translateAnimator = ObjectAnimator
                .ofFloat(drawer, "translationY", translationY)
                .setDuration(100);

        ObjectAnimator colorAnimator = ObjectAnimator
                .ofInt(drawer, "backgroundColor", currentBackgroudColor, color)
                .setDuration(100);

        colorAnimator.setEvaluator(new ArgbEvaluator());

        currentAnimation = new AnimatorSet();
        currentAnimation.setDuration(300);
        currentAnimation.play(translateAnimator).with(colorAnimator);
        currentAnimation.start();
    }
}
