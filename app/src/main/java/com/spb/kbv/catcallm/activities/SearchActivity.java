package com.spb.kbv.catcallm.activities;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.data.MessagesContract;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.spb.kbv.catcallm.views.CompanyDetailsAdapter;
import com.spb.kbv.catcallm.views.MainNavDrawer;
import com.squareup.otto.Subscribe;

public class SearchActivity extends BaseAuthenticatedActivity implements AdapterView.OnItemClickListener {
    private CompanyDetailsAdapter adapter;
    private View progressFrame;
    private Handler handler;
    private SearchView searchView;
    private String lastQuery;
    private UserDetails selectedCompany;

    private Runnable searchRunnable = new Runnable() {
        @Override
        public void run() {
            lastQuery = searchView.getQuery().toString();
            progressFrame.setVisibility(View.VISIBLE);
            bus.post(new Contacts.SearchCompanyRequest(lastQuery));
        }
    };

    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        setNavDrawer(new MainNavDrawer(this));

        adapter = new CompanyDetailsAdapter(this);
        ListView listView = (ListView) findViewById(R.id.activity_search_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        progressFrame = findViewById(R.id.activity_search_progressFrame);
        progressFrame.setVisibility(View.GONE);

        handler = new Handler();
        searchView = new SearchView(this);

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
    }

    @Subscribe
    public void onCompaniesSearched(Contacts.SearchCompanyResponse response){
        progressFrame.setVisibility(View.GONE);

        adapter.clear();
        adapter.addAll(response.companies);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
        final UserDetails company = adapter.getItem(position);
        Intent intent = new Intent(this, CompanyInfoActivity.class);
        intent.putExtra(CompanyInfoActivity.EXTRA_COMPANY_DETAILS, company);
        intent.putExtra(CompanyInfoActivity.EXTRA_COMPANY_IS_NEW, true);
        startActivity(intent);

       /* AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Start new dialog with " + company.getUsername())
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addCompanyToContacts(company);



                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();*/
    }

    /*private void addCompanyToContacts(UserDetails company) {
        ContentValues fakeCompaniesValues = new ContentValues();

        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_NAME, company.getUsername());
        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_ADDRESS, company.getAddress());
        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_LATITUDE, company.getLatitude());
        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_LONGITUDE, company.getLongitude());
        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_AVATAR, company.getAvatarUrl());

        Uri insertedUri = application.getContentResolver().insert(
                MessagesContract.CompaniesEntry.CONTENT_URI,
                fakeCompaniesValues
        );

        company.setId(Long.valueOf(insertedUri.getLastPathSegment()));

        Intent intent = new Intent(SearchActivity.this, ChatActivity.class);
        intent.putExtra(ChatActivity.EXTRA_USER_DETAILS, company);
        startActivity(intent);


    }*/
}
