package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.views.NavDrawer;
import com.squareup.otto.Bus;

public class BaseActivity extends /*ActionBarActivity*/ AppCompatActivity {
    private boolean isRegisteredWithBus;

    protected CatcallApplication application;
    protected Toolbar toolbar;
    protected boolean isTablet;
    protected Bus bus;
    protected NavDrawer navDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (CatcallApplication)getApplication();
        bus = application.getBus();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        isTablet = (metrics.widthPixels / metrics.density) >= 600;
        bus.register(this);
        isRegisteredWithBus = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisteredWithBus) {
            bus.unregister(this);
            isRegisteredWithBus = false;
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (isRegisteredWithBus) {
            bus.unregister(this);
            isRegisteredWithBus = false;
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    protected void setNavDrawer(NavDrawer navDrawer){
        this.navDrawer = navDrawer;
        this.navDrawer.create();

        overridePendingTransition(0, 0);
        View rootView = findViewById(android.R.id.content);
        rootView.setAlpha(0);
        rootView.animate().alpha(1).setDuration(450).start();
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public CatcallApplication getCatcallApplication() {
        return application;
    }
}
