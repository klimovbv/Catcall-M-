package com.spb.kbv.catcallm.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.squareup.otto.Bus;

public class BaseFragment extends Fragment {
    protected CatcallApplication application;
    protected Bus bus;
    /*protected ActionScheduler scheduler;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (CatcallApplication)getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);
        /*scheduler = new ActionScheduler(application);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        /*scheduler.onPause();*/
    }

    @Override
    public void onResume() {
        super.onResume();
        /*scheduler.onResume();*/
    }
}
