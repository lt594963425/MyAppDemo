package com.example.administrator.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.R;
import com.example.administrator.base.BaseFragment;
import com.example.administrator.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * Fragment3
 * Created by liu_tao on 16/5/23.
 */
public class Fragment3 extends BaseFragment{
    //String url = "http://www.huhst.edu.cn:8001/";
    private TabLayout mTabLayout;
    public ViewPager mViewPager;
    private Toolbar toolbar;
    private List<Pair<String, Fragmenta>> items;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("我是悬浮按钮");
            }
        });
        initToolBar(toolbar,false,"");
        return view;
    }

    @Override
    protected void initData() {
        items = new ArrayList<>();
        items.add(new Pair<>("OkGo", new Fragmenta()));
        items.add(new Pair<>("okhttp", new Fragmenta()));
        items.add(new Pair<>("OkRx2", new Fragmenta()));
        items.add(new Pair<>("OkRx", new Fragmenta()));
        items.add(new Pair<>("OkDownload", new Fragmenta()));
        items.add(new Pair<>("OkUpload", new Fragmenta()));
        mViewPager.setAdapter(new MainAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tab.addOnTabSelectedListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
              mViewPager.setCurrentItem(position);
            }
        });
    }

    private class MainAdapter extends FragmentPagerAdapter {

        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position).second;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position).first;
        }
    }

    /** 初始化 Toolbar */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
    }

}
