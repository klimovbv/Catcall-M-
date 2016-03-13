package com.spb.kbv.catcallm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.activities.ChatActivity;
import com.spb.kbv.catcallm.activities.CompanyInfoActivity;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.spb.kbv.catcallm.views.CompanyDetailsAdapter;
import com.squareup.otto.Subscribe;

public class CompaniesListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public CompanyDetailsAdapter adapter;
    public View progressFrame;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        Log.d("myLogs", "onCreateView in Fragment");

        View view = inflater.inflate(R.layout.fragment_companies_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.fragment_companies_list);
        adapter = new CompanyDetailsAdapter((BaseActivity)getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        progressFrame = view.findViewById(R.id.fragment_companies_progressFrame);
        progressFrame.setVisibility(View.VISIBLE);

        bus.post(new Contacts.GetCompaniesRequest());

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserDetails details = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), CompanyInfoActivity.class);
        intent.putExtra(CompanyInfoActivity.EXTRA_COMPANY_DETAILS, details);
        startActivity(intent);
    }

    @Subscribe
    public void onCompaniesListReceived(Contacts.GetCompaniesResponse response) {
        progressFrame.setVisibility(View.GONE);
        adapter.clear();
        adapter.addAll(response.companies);
    }
}
