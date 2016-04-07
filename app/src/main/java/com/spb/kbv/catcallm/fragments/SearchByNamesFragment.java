package com.spb.kbv.catcallm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.activities.BaseAuthenticatedActivity;
import com.spb.kbv.catcallm.activities.CompanyInfoActivity;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.spb.kbv.catcallm.views.CompanyDetailsAdapter;
import com.spb.kbv.catcallm.views.MainNavDrawer;
import com.squareup.otto.Subscribe;

public class SearchByNamesFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private CompanyDetailsAdapter adapter;
    private View progressFrame;
    private Handler handler;
    private SearchView searchView;
    private String lastQuery;
    private UserDetails selectedCompany;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        adapter = new CompanyDetailsAdapter((BaseActivity)getActivity());
        ListView listView = (ListView) view.findViewById(R.id.activity_search_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        progressFrame = view.findViewById(R.id.activity_search_progressFrame);
        progressFrame.setVisibility(View.GONE);

        return view;
    }


    @Subscribe
    public void onCompaniesSearched(Contacts.SearchCompanyResponse response){
        progressFrame.setVisibility(View.GONE);

        adapter.clear();
        adapter.addAll(response.companies);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
        /*final UserDetails company = adapter.getItem(position);
        Intent intent = new Intent((BaseActivity)getActivity(), CompanyInfoActivity.class);
        intent.putExtra(CompanyInfoActivity.EXTRA_COMPANY_DETAILS, company);
        intent.putExtra(CompanyInfoActivity.EXTRA_COMPANY_IS_NEW, true);
        startActivity(intent);*/

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
