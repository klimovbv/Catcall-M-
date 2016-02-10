package com.spb.kbv.catcallm.activities;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.fragments.CompaniesListFragment;
import com.spb.kbv.catcallm.fragments.MapFragment;
import com.spb.kbv.catcallm.views.MainNavDrawer;

public class SelectCompanyActivity extends BaseAuthenticatedActivity implements AdapterView.OnItemSelectedListener {

    private ObjectAnimator currentAnimation;
    private ArrayAdapter<SpinnerItem> adapter;


    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_company);
        setNavDrawer(new MainNavDrawer(this));

        adapter = new ArrayAdapter(this, R.layout.list_item_toolbar_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        adapter.add(new SpinnerItem(
                "Companies List",
                Color.parseColor("#00BCD4"),
                CompaniesListFragment.class));
        adapter.add(new SpinnerItem(
                "Companies Map",
                Color.parseColor("#00BCD4"),
                MapFragment.class));

        Spinner spinner = (Spinner) findViewById(R.id.activity_select_company_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        getSupportActionBar().setTitle(null);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        SpinnerItem item = adapter.getItem(position);
        if (item == null)
            return;
        /*if (currentAnimation != null)
            currentAnimation.end();

        int currentColor = ((ColorDrawable) toolbar.getBackground()).getColor();

        currentAnimation = ObjectAnimator.ofObject(
                toolbar, "backgroundColor", new ArgbEvaluator(), currentColor, item.getColor())
                .setDuration(250);

        currentAnimation.start();*/



        if (item.getTitle().equals("Companiews List")) {
            Fragment fragment;
            try {
                fragment = (Fragment) item.getFragment().newInstance();
            } catch (Exception e) {
                Log.e("myLogs", "Could not instantiate fragment " + item.getFragment().getName(), e);
                return;
            }

            getSupportFragmentManager().beginTransaction()
                    /*.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)*/
                    .replace(R.id.activity_select_company_container, fragment)
                    .commit();
        } else {
            Fragment fragment;
            try {
                fragment = (Fragment) item.getFragment().newInstance();
            } catch (Exception e) {
                Log.e("myLogs", "Could not instantiate fragment " + item.getFragment().getName(), e);
                return;
            }



            getSupportFragmentManager().beginTransaction()
                    /*.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)*/
                    .replace(R.id.activity_select_company_container, fragment)
                    .commit();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private class SpinnerItem {
        private final String title;
        private final int color;
        private Class fragment;

        private SpinnerItem(String title, int color, Class fragment) {
            this.title = title;
            this.color = color;
            this.fragment = fragment;
        }

        public String getTitle() {
            return title;
        }

        public int getColor() {
            return color;
        }

        public Class getFragment() {
            return fragment;
        }

        @Override
        public String toString() {
            return getTitle();
        }
    }
}
