package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.views.MyFragmentPagerAdapter;
import com.spb.kbv.catcallm.views.SlidingTabLayout;

public class ScrollingMainActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onCatcallAppCreate (Bundle savedInstanceState) {
        setContentView(R.layout.activity_scrolling_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), new CharSequence[]{"One", "Two"}, 2));

    }
}
