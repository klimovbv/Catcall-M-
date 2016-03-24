package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.fragments.SearchPopupFragment;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.views.MyFragmentPagerAdapter;

public class ScrollingSearchActivity extends BaseAuthenticatedActivity {

    private String lastQuery;
    private View progressFrame;
    private SearchView searchView;
    private Handler handler;

    private Runnable searchRunnable = new Runnable() {
        @Override
        public void run() {
            lastQuery = searchView.getQuery().toString();
            /*progressFrame.setVisibility(View.VISIBLE);*/
            bus.post(new Contacts.SearchCompanyRequest(lastQuery));
        }
    };

    @Override
    protected void onCatcallAppCreate (Bundle savedInstanceState) {
        setContentView(R.layout.activity_scrolling_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        searchView = new SearchView(this);
        handler = new Handler();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(searchView);

        searchView.setIconified(false);
        searchView.setQueryHint("Search for companies...");
        searchView.setLayoutParams(new Toolbar.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT,
                Toolbar.LayoutParams.MATCH_PARENT
        ));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() < 4)
                    return true;
                handler.removeCallbacks(searchRunnable);
                handler.postDelayed(searchRunnable, 750);
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setResult(RESULT_CANCELED);
                finish();
                return true;
            }
        });

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), new CharSequence[]{"ПО АЛФАВИТУ", "РЯДОМ", "КАТЕГОРИИ"}, 3, 2));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_fragment_issue_list_tab);
        tabLayout.setupWithViewPager(pager);

    }


}
