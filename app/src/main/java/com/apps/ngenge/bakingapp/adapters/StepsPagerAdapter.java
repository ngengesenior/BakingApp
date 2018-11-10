package com.apps.ngenge.bakingapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

public class StepsPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;
    public StepsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public void addragment(Fragment fragment, String title, int position)
    {
        fragments.add(position,fragment);
        titles.add(position,title);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
