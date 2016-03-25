package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.fragments.DialogsListFragment;
import com.spb.kbv.catcallm.views.MainNavDrawer;
import com.spb.kbv.catcallm.views.MyFragmentPagerAdapter;

public class MainActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activiti_tabstrip);

        /*setNavDrawer(new MainNavDrawer(this));*/

       /* Fragment dialogsFragment = new DialogsListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, dialogsFragment)
                .commit();*/

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), new CharSequence[]{"One", "Two"}, 2, 1, null));
    }
}
