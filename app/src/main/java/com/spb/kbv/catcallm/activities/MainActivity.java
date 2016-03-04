package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.fragments.DialogsListFragment;
import com.spb.kbv.catcallm.views.MainNavDrawer;

public class MainActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        setNavDrawer(new MainNavDrawer(this));

        Fragment dialogsFragment = new DialogsListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, dialogsFragment)
                .commit();
    }
}
