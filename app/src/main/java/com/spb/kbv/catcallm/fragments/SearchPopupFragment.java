package com.spb.kbv.catcallm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.spb.kbv.catcallm.R;

public class SearchPopupFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_search, container, false);
        SearchView searchView = (SearchView) view.findViewById(R.id.fragment_dialog_search_searchView);
        searchView.setIconified(false);
        searchView.setQueryHint("Search company...");

        Button cancelButton = (Button) view.findViewById(R.id.fragment_dialog_search_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
