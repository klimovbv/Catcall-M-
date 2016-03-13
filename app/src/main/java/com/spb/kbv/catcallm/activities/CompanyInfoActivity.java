package com.spb.kbv.catcallm.activities;

import android.os.Bundle;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.fragments.CompanyInfoFragment;
import com.spb.kbv.catcallm.services.entities.UserDetails;

public class CompanyInfoActivity extends BaseAuthenticatedActivity{
    public static final String EXTRA_COMPANY_DETAILS = "EXTRA_COMPANY_DETAILS";
    public static final String EXTRA_COMPANY_IS_NEW = "EXTRA_COMPANY_IS_NEW";


    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_company_info_container);

        UserDetails companyDetails = getIntent().getParcelableExtra(EXTRA_COMPANY_DETAILS);
        boolean isNewCompany = getIntent().getBooleanExtra(EXTRA_COMPANY_IS_NEW, false);
        CompanyInfoFragment companyInfoFragment = CompanyInfoFragment.newInstance(companyDetails, isNewCompany);

        getSupportFragmentManager().beginTransaction().add(R.id.activity_company_info_container, companyInfoFragment).commit();
    }
}
