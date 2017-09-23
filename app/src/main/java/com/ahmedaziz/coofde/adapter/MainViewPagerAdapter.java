package com.ahmedaziz.coofde.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ahmedaziz.coofde.fragment.CategoryFragment;
import com.ahmedaziz.coofde.fragment.StoresFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Aziz on 9/22/2017.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    public MainViewPagerAdapter(FragmentManager fragmentManager){super(fragmentManager);}
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment , String title){
        fragmentList.add(fragment);
        titleList.add(title);
    }
}
