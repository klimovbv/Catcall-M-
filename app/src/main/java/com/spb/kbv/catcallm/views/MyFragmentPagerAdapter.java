package com.spb.kbv.catcallm.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spb.kbv.catcallm.fragments.CompaniesListFragment;
import com.spb.kbv.catcallm.fragments.CompanyInfoFragment;
import com.spb.kbv.catcallm.fragments.DialogsListFragment;
import com.spb.kbv.catcallm.fragments.MapFragment;
import com.spb.kbv.catcallm.fragments.PageFragment;


public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence mTitles[];
    int numbOfTabs;

    public MyFragmentPagerAdapter(FragmentManager fm, CharSequence mTitles[], int numbOfTabs) {
        super(fm);
        this.mTitles = mTitles;
        this.numbOfTabs = numbOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DialogsListFragment();
        } else  if (position == 1) {
            return new CompaniesListFragment();
        } /*else if (position == 2) {
            return new MapFragment();
        }*/
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
