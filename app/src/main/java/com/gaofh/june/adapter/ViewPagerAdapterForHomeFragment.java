package com.gaofh.june.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapterForHomeFragment extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;
    public ViewPagerAdapterForHomeFragment(FragmentManager fm,List<Fragment> fragments){
        super(fm);
        fragmentList=fragments;
    }
    public ViewPagerAdapterForHomeFragment(@NonNull FragmentManager fm, List<Fragment> fragments,List<String> titleLists) {
        super(fm);
        fragmentList=fragments;
        titleList=titleLists;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList==null?super.getPageTitle(position):titleList.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList==null?null:fragmentList.get(position) ;
    }

    @Override
    public int getCount() {
        return fragmentList==null?0:fragmentList.size();
    }
}
