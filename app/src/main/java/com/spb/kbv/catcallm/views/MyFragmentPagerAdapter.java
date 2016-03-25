package com.spb.kbv.catcallm.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spb.kbv.catcallm.fragments.CompaniesListFragment;
import com.spb.kbv.catcallm.fragments.CompanyInfoFragment;
import com.spb.kbv.catcallm.fragments.CompanyNewsFragment;
import com.spb.kbv.catcallm.fragments.CompanyProfileFragment;
import com.spb.kbv.catcallm.fragments.DialogsListFragment;
import com.spb.kbv.catcallm.fragments.MapFragment;
import com.spb.kbv.catcallm.fragments.PageFragment;
import com.spb.kbv.catcallm.fragments.SearchByNamesFragment;
import com.spb.kbv.catcallm.fragments.StockListFragment;
import com.spb.kbv.catcallm.services.entities.UserDetails;


public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence mTitles[];
    int numbOfTabs;
    int type;
    UserDetails companyDetails;

    public MyFragmentPagerAdapter(FragmentManager fm, CharSequence mTitles[], int numbOfTabs, int typeOfFragments, UserDetails companyDetails) {
        super(fm);
        this.mTitles = mTitles;
        this.numbOfTabs = numbOfTabs;
        type = typeOfFragments;
        this.companyDetails = companyDetails;
    }

    @Override
    public Fragment getItem(int position) {
        if (type == 1) {
            if (position == 0) {
                return new DialogsListFragment();
            } else if (position == 1) {
                return new StockListFragment();
            } else if (position == 2) {
                return new CompaniesListFragment();
            }
        }

        if (type == 2) {
            if (position == 0) {
                return new SearchByNamesFragment();
            } else if (position == 1) {
                return new MapFragment();
            } else if (position == 2) {
                return new SearchByNamesFragment();
            }
        }

        if (type == 3) {
            if (position == 0) {
                return CompanyProfileFragment.newInstance(companyDetails, true);
            } else if (position == 1) {
                return new StockListFragment();
            } else if (position == 2) {
                return new CompanyNewsFragment();
            }
        }
        return null;


    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
