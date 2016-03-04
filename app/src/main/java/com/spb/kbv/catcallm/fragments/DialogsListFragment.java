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
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.activities.ChatActivity;
import com.spb.kbv.catcallm.services.Contacts;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.spb.kbv.catcallm.views.DialogsAdapter;
import com.squareup.otto.Subscribe;

public class DialogsListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public DialogsAdapter adapter;
    public View progressFrame;
    private TextView emptyList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        Log.d("myLogs", "onCreateView in Fragment");

        View view = inflater.inflate(R.layout.fragment_dialogs_list, container, false);
        emptyList = (TextView) view.findViewById(R.id.fragment_dialogs_list_emptyList);
        emptyList.setVisibility(View.VISIBLE);
        ListView listView = (ListView) view.findViewById(R.id.fragment_dialogs_list_listView);


        adapter = new DialogsAdapter((BaseActivity)getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        progressFrame = view.findViewById(R.id.fragment_dialogs_list_progressFrame);
        progressFrame.setVisibility(View.VISIBLE);

        bus.post(new Contacts.LoadCompaniesListWithOpenDialogsRequest());
        return view;
    }


    @Subscribe
    public void onDialogsListReceived(Contacts.LoadCompaniesListWithOpenDialogsResponse response) {
        progressFrame.setVisibility(View.GONE);

        if (response.dialogsList.size() > 0) {
            emptyList.setVisibility(View.GONE);
            adapter.clear();
            adapter.addAll(response.dialogsList);
        }
    }

    //-------------------------------------------------//

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserDetails details = adapter.getItem(position).getOtherUser();
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(ChatActivity.EXTRA_USER_DETAILS, details);
        startActivity(intent);
    }
}
