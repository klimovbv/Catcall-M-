package com.spb.kbv.catcallm.activities;

import android.os.Bundle;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.views.MainNavDrawer;

public class MainActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        setNavDrawer(new MainNavDrawer(this));

        bus.post(new Contacts.LoadCompaniesListWithOpenDialogsRequest());


    }
}
