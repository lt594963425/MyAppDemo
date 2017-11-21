package com.example.administrator.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * $name
 *ViewPager适配器
 * @author ${LiuTao}
 * @date 2017/11/16/016
 */

public class FixPagerAdapter  extends FragmentStatePagerAdapter {


    private String[] mTitles;
    List<Fragment> mFragments=null;

    public void setTitles(String[] titles) {
        this.mTitles = titles;
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    public FixPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment=null;
        try {
            fragment= (Fragment) super.instantiateItem(container,position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
