package com.spb.kbv.catcallm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.views.DialogsRecycleAdapter;
import com.squareup.otto.Subscribe;

public class DialogsListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public View progressFrame;
    private TextView emptyList;

    RecyclerView recyclerView;
    DialogsRecycleAdapter adapter;
    private BaseActivity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        Log.d("myLogs", "onCreateView in Fragment");

        View view = inflater.inflate(R.layout.fragment_dialogs_list, container, false);
        emptyList = (TextView) view.findViewById(R.id.fragment_dialogs_list_emptyList);
        emptyList.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_dialogs_list_listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        activity = (BaseActivity) getActivity();

        progressFrame = view.findViewById(R.id.fragment_dialogs_list_progressFrame);
        progressFrame.setVisibility(View.VISIBLE);

        bus.post(new Contacts.LoadCompaniesListWithOpenDialogsRequest());
        return view;
    }


    @Subscribe
    public void onDialogsListReceived(Contacts.LoadCompaniesListWithOpenDialogsResponse response) {
        progressFrame.setVisibility(View.GONE);
        Log.d("addAd", "Dialogs onReceived " + response.dialogsList.size());

        if (response.dialogsList.size() > 0) {
            emptyList.setVisibility(View.GONE);
            adapter = new DialogsRecycleAdapter(activity, response.dialogsList);
            recyclerView.setAdapter(adapter);

            /*adapter.clear();
            adapter.addAll(response.dialogsList);*/
        }
    }

    //-------------------------------------------------//

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*UserDetails details = adapter.getItem(position).getOtherUser();
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(ChatActivity.EXTRA_USER_DETAILS, details);
        startActivity(intent);*/
    }
}
