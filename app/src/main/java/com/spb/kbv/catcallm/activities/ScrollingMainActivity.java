package com.spb.kbv.catcallm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.fragments.SearchPopupFragment;
import com.spb.kbv.catcallm.views.MyFragmentPagerAdapter;

public class ScrollingMainActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onCatcallAppCreate (Bundle savedInstanceState) {
        setContentView(R.layout.activity_scrolling_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                SearchPopupFragment searchPopupFragment = new SearchPopupFragment();
                searchPopupFragment.show(getSupportFragmentManager(), "SearchFrag");
            }
        });

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), new CharSequence[]{"ДИАЛОГИ", "АКЦИИ", "ИЗБРАННОЕ"}, 3, 1));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_fragment_issue_list_tab);
        tabLayout.setupWithViewPager(pager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            startActivity(new Intent(this, ScrollingSearchActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
