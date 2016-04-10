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
import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.views.DialogsRecycleAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogsListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public View progressFrame;
    private TextView emptyList;

    RecyclerView recyclerView;
    DialogsRecycleAdapter adapter;
    private BaseActivity activity;
    private ArrayList<Message> dialogs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        Log.d("myLogs", "onCreateView in DialogsFragment");

        View view = inflater.inflate(R.layout.fragment_dialogs_list, container, false);
        emptyList = (TextView) view.findViewById(R.id.fragment_dialogs_list_emptyList);
        emptyList.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_dialogs_list_listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        activity = (BaseActivity) getActivity();

        progressFrame = view.findViewById(R.id.fragment_dialogs_list_progressFrame);
        progressFrame.setVisibility(View.VISIBLE);

        if (dialogs == null) {
            Log.d("myLogs", " dialogd == null in oncreateview");
            bus.post(new Contacts.LoadCompaniesListWithOpenDialogsRequest());
        } else {
            Log.d("myLogs", " dialogd != null in oncreateview");
            fillDialogList(dialogs);
        }
        return view;
    }


    @Subscribe
    public void onDialogsListReceived(Contacts.LoadCompaniesListWithOpenDialogsResponse response) {
        Log.d("addAd", "Dialogs onReceived " + response.dialogsList.size());
        dialogs = response.dialogsList;
        fillDialogList(dialogs);
    }

    private void fillDialogList(ArrayList<Message> dialogs) {
        Log.d("myLogs", " dialogs =  " + dialogs.size());

        Collections.sort(dialogs);


        if (progressFrame != null)
            progressFrame.setVisibility(View.GONE);
        if (dialogs.size() > 0) {
            emptyList.setVisibility(View.GONE);
            if (adapter == null) {
                Log.d("myLogs", "adapter == null in dialogs   " );
                adapter = new DialogsRecycleAdapter(activity, dialogs);
            } else {
                Log.d("myLogs", "adapter != null in dialogs   " );
                adapter = new DialogsRecycleAdapter(activity, dialogs);
            }

            recyclerView.setAdapter(adapter);

            /*adapter.clear();
            adapter.addAll(response.dialogsList);*/
        }
    }

    //-------------------------------------------------//

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       /* UserDetails details = adapter.getItem(position).getOtherUser();
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(ChatActivity.EXTRA_USER_DETAILS, details);
        startActivity(intent);*/
    }
}
