package com.spb.kbv.catcallm.fragments;


import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
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
import com.spb.kbv.catcallm.activities.ChatActivity;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

public class MapFragment extends BaseFragment implements View.OnClickListener {

    private GoogleMap map;
    public View drawer;
    private boolean isOpen;
    private AnimatorSet currentAnimation;
    private TextView showInfoButton;
    private TextView info;
    private int translateOffset;
    private ArrayList<UserDetails> companiesList;
    private HashMap <String, UserDetails> extraMarkerInfo;
    private UserDetails markerDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("myLogs", "in onCreateView map fragment");

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        companiesList = new ArrayList<>();
        extraMarkerInfo = new HashMap<>();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("myLogs", "in onViewCreated map fragment");
        showInfoButton = (TextView) view.findViewById(R.id.fragment_map_info_button);
        showInfoButton.setOnClickListener(this);
        info = (TextView) view.findViewById(R.id.fragment_map_company_info);
        info.setOnClickListener(this);
        drawer = view.findViewById(R.id.fragment_map_drawer);
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
                    markerDetails = extraMarkerInfo.get(marker.getId());
                    Toast.makeText(getContext(), "Marker pressed " + markerDetails.getUsername(), Toast.LENGTH_LONG).show();
                    translateOffset = drawer.getHeight() - showInfoButton.getHeight();
                    Log.d("myLogs", drawer.getHeight() + " / " + showInfoButton.getHeight());
                    drawer.setTranslationY(translateOffset);
                    drawer.setVisibility(View.VISIBLE);
                    showInfoButton.setText(markerDetails.getUsername());
                    info.setText(markerDetails.getUsername());
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }
            });

            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    drawer.setVisibility(View.INVISIBLE);
                }
            });

            if (companiesList.size() != 0) {
                for (UserDetails company : companiesList) {
                    iconFactory.setBackground(null);
                    iconFactorySign.setBackground(null);
                    View myView = View.inflate(getContext(), R.layout.marker, null);
                    View text = View.inflate(getContext(), R.layout.marker_text, null);
                    TextView textView = (TextView) text.findViewById(R.id.marker_text);
                    textView.setText(company.getUsername());
            /*ImageView image = (ImageView)myView.findViewById(R.id.marker_image);
            Log.d("myLogs", String.valueOf((float)(image.getWidth())) + " / " + String.valueOf(myView.getWidth()));
            text.setText("Restaurant");
            Log.d("myLogs", String.valueOf((float)(image.getWidth())) + " / " + String.valueOf(myView.getWidth()));
            float anchorOffset =  ((float)(image.getWidth()) * 0.5f) / (float)(myView.getWidth());*/
                    iconFactory.setContentView(myView);
                    iconFactorySign.setContentView(text);
                    addIcon(iconFactory, "Default", new LatLng(company.getLatitude(), company.getLongitude()), iconFactorySign, company);
            /*setupMap();*/
                }
            }
        }
    }

    private void addIcon(IconGenerator iconFactory, String text, LatLng position, IconGenerator iconFactorySign, UserDetails details) {

        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon()))
                .position(position)
                .anchor(0.5f, 1)
                .title("Privet");

        Marker marker1 = map.addMarker(markerOptions);
        extraMarkerInfo.put(marker1.getId(), details);



        MarkerOptions markerOptionsText = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(iconFactorySign.makeIcon()))
                .position(position)
                .anchor(0, 1)
                .alpha(20);

        Marker marker2 = map.addMarker(markerOptionsText);
        extraMarkerInfo.put(marker2.getId(), details);
    }

    @Override
    public void onResume() {
        Log.d("myLogs", "in onResume map fragment");
        super.onResume();
        bus.post(new Contacts.GetCompaniesRequest());
    }

    @Subscribe
    public void onCompaniesListReceived (Contacts.GetCompaniesResponse response) {
        companiesList = response.companies;
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
        if (view.getId() == R.id.fragment_map_info_button) {
            isOpen = !isOpen;

            if (currentAnimation != null) {
                currentAnimation.cancel();
            }

            int currentBackgroudColor = ((ColorDrawable) drawer.getBackground()).getColor();
            int translationY, color;

            if (isOpen) {
                translationY = 0;
                color = Color.parseColor("#EE1998FC");
                showInfoButton.setText("Close");
            } else {
                translationY = drawer.getHeight() - showInfoButton.getHeight();
                color = Color.parseColor("#221998FC");
                showInfoButton.setText(markerDetails.getUsername());
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

        if (view.getId() == R.id.fragment_map_company_info) {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            intent.putExtra(ChatActivity.EXTRA_USER_DETAILS, markerDetails);
            startActivity(intent);
        }
    }
}
