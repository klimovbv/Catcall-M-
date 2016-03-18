package com.spb.kbv.catcallm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.spb.kbv.catcallm.views.CompanyDetailsRecycleAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class CompaniesListFragment extends BaseFragment /*implements AdapterView.OnItemClickListener*/ {

    /*public CompanyDetailsAdapter adapter;*/
    private ArrayList<UserDetails> detailsArray;
    public View progressFrame;
    private CompanyDetailsRecycleAdapter adapter;
    private RecyclerView recyclerView;
    private BaseActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        Log.d("myLogs", "onCreateView in Fragment");

        View view = inflater.inflate(R.layout.fragment_companies_list, container, false);
        /*ListView listView = (ListView) view.findViewById(R.id.fragment_companies_list);
        adapter = new CompanyDetailsAdapter((BaseActivity)getActivity());
        listView.setAdapter(adapter);*/
        /*listView.setOnItemClickListener(this);*/

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_companies_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        activity = (BaseActivity) getActivity();


        progressFrame = view.findViewById(R.id.fragment_companies_progressFrame);
        progressFrame.setVisibility(View.VISIBLE);

        bus.post(new Contacts.GetCompaniesRequest());

        return view;
    }


   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (view.getId() == R.id.list_item_company_details_list_item_menu) {
            Log.d("actLog", "clicked show menu in fragment");
        }
        UserDetails details = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), CompanyInfoActivity.class);
        intent.putExtra(CompanyInfoActivity.EXTRA_COMPANY_DETAILS, details);
        startActivity(intent);
    }*/

    @Subscribe
    public void onCompaniesListReceived(Contacts.GetCompaniesResponse response) {
        Log.d("addAd", " in onReceived " + response.companies.size());
        progressFrame.setVisibility(View.GONE);
        adapter = new CompanyDetailsRecycleAdapter(activity, response.companies);
        recyclerView.setAdapter(adapter);

/*        adapter.clear();
        adapter.addAll(response.companies);*/
    }

    public void showActionMenu(View v) {
        Log.d("actLog", "clicked show menu");
    }

}
