package com.spb.kbv.catcallm.fragments;

import android.os.Bundle;
import android.os.Parcelable;
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
import java.util.List;

public class CompaniesListFragment extends BaseFragment /*implements AdapterView.OnItemClickListener*/ {

    /*public CompanyDetailsAdapter adapter;*/
    private ArrayList<UserDetails> detailsArray;
    public View progressFrame;
    private CompanyDetailsRecycleAdapter adapter;
    private RecyclerView recyclerView;
    private BaseActivity activity;
    private boolean isCreated;
    private List<UserDetails> companies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("myLogs", "onCreate");
        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        Log.d("myLogs", "onCreateView in CompanyListFragment");

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

        if (companies == null) {
            Log.d("myLogs", " companies == null in oncreateview");
            bus.post(new Contacts.GetCompaniesRequest());
        }
        else {
            Log.d("myLogs", " companies != null in oncreateview");
            fillList(companies);
        }
        return view;
    }

    private void fillList(List<UserDetails> companies) {
        Log.d("myLogs", " in filllist " + companies.size());
        if (progressFrame != null)
            progressFrame.setVisibility(View.GONE);
        if (adapter == null) {
            Log.d("myLogs", " in filllist adapter == null");
            adapter = new CompanyDetailsRecycleAdapter(activity, companies);
        } else {
            Log.d("myLogs", " in filllist adapter != null");
        }
        recyclerView.setAdapter(adapter);
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
        /*if (isCreated) {*/
        Log.d("myLogs", "isCreated");
        companies = response.companies;
        fillList(companies);




        /*} else {
            Log.d("myLogs", "in compListF is not created yet");
            Contacts.GetCompaniesResponse newResponse = new Contacts.GetCompaniesResponse();
            newResponse.companies = response.companies;
            bus.post(newResponse);
        }*/

/*        adapter.clear();
        adapter.addAll(response.companies);*/
    }

    public void showActionMenu(View v) {
        Log.d("actLog", "clicked show menu");
    }

}
