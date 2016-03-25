package com.spb.kbv.catcallm.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.ChatActivity;
import com.spb.kbv.catcallm.data.MessagesContract;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.picasso.Picasso;

public class CompanyProfileFragment extends Fragment {
    private static final String EXTRA_DETAILS = "EXTRA_DETAILS";
    private static final String EXTRA_COMPANY_IS_NEW = "EXTRA_COMPANY_IS_NEW";
    private UserDetails companyDetails;

    public static CompanyProfileFragment newInstance (UserDetails companyDetails, boolean companyIsNew){
        CompanyProfileFragment companyInfoFragment = new CompanyProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_DETAILS, companyDetails);
        args.putBoolean(EXTRA_COMPANY_IS_NEW, companyIsNew);
        companyInfoFragment.setArguments(args);
        return companyInfoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(EXTRA_DETAILS)) {
            companyDetails = getArguments().getParcelable(EXTRA_DETAILS);
        } else {
            throw new IllegalArgumentException("Must be created through newInstance(...)");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_profile, container, false);

        TextView companyName = (TextView) view.findViewById(R.id.fragment_company_profile_name);
        TextView companyAddress = (TextView) view.findViewById(R.id.fragment_company_profile_address);


        companyName.setText(companyDetails.getUsername());
        companyAddress.setText(companyDetails.getAddress());


        /*TextView startDialogButton = (TextView) view.findViewById(R.id.fragment_company_info_start_dialog_button);
        startDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments().getBoolean(EXTRA_COMPANY_IS_NEW)) {
                    addCompanyToContacts(companyDetails);
                }

                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra(ChatActivity.EXTRA_USER_DETAILS, companyDetails);
                startActivity(intent);
            }
        });




        */
        return view;
    }

    /*private void addCompanyToContacts(UserDetails company) {
        ContentValues fakeCompaniesValues = new ContentValues();

        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_NAME, company.getUsername());
        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_ADDRESS, company.getAddress());
        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_LATITUDE, company.getLatitude());
        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_LONGITUDE, company.getLongitude());
        fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_AVATAR, company.getAvatarUrl());

        CatcallApplication application = (CatcallApplication) getActivity().getApplication();

        Uri insertedUri = application.getContentResolver().insert(
                MessagesContract.CompaniesEntry.CONTENT_URI,
                fakeCompaniesValues
        );

        company.setId(Long.valueOf(insertedUri.getLastPathSegment()));

        *//*Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(ChatActivity.EXTRA_USER_DETAILS, company);
        startActivity(intent);*//*
    }*/
}
