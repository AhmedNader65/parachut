package com.mrerror.parachut.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdaptar extends FragmentPagerAdapter {
    List<Fragment> fragmentList;

    public PagerAdaptar(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addNewFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }
}
