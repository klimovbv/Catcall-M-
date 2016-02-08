package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.squareup.otto.Bus;

public class BaseActivity extends ActionBarActivity {
    private boolean isRegisteredWithBus;

    protected CatcallApplication application;
    protected Toolbar toolbar;
    protected boolean isTablet;
    protected Bus bus;

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

    public Toolbar getToolbar() {
        return toolbar;
    }

    public CatcallApplication getCatcallApplication() {
        return application;
    }
}
