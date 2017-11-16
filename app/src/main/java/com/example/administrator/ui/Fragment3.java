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
public class Fragment3 extends BaseFragment implements  TabLayout.OnTabSelectedListener {
    //String url = "http://www.huhst.edu.cn:8001/";
    private TabLayout tab;
    public ViewPager mViewPager;
    private Toolbar toolbar;
    private List<Pair<String, Fragmenta>> items;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, null);
        tab = (TabLayout) view.findViewById(R.id.tab);
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
        items = new ArrayList<Pair<String, Fragmenta>>();

        items.add(new Pair<>("OkGo", new Fragmenta()));
        items.add(new Pair<>("okhttp", new Fragmenta()));
        items.add(new Pair<>("OkRx2", new Fragmenta()));
        items.add(new Pair<>("OkRx", new Fragmenta()));
        items.add(new Pair<>("OkDownload", new Fragmenta()));
        items.add(new Pair<>("OkUpload", new Fragmenta()));
        mViewPager.setAdapter(new MainAdapter(getChildFragmentManager()));
        tab.setupWithViewPager(mViewPager);
        tab.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
       int position = tab.getPosition();
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }


    /*  //webView = (WebView) view.findViewById(R.id.webView);
      //WebSettings settings = webView.getSettings();
      //initWebview();*/
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

//    private void initWebview() {
//        mDialog = WeiboDialogUtils.createLoadingDialog(getActivity(),"加载中");
//        //设计进度条
//        //progressBar = ProgressDialog.show(getActivity(), null, "正在进入网页，请稍后…");
//        WebSettings settings = webView.getSettings();
//        webView.loadUrl(url);
//        settings.setJavaScriptEnabled(true);
//        // webView.loadUrl("http://baidu.com");
//        // alertDialog = new AlertDialog.Builder(getActivity()).create();
//
//        webView.setWebViewClient(new MyWebViewClient());
//
//    }
//
//
//    class MyWebViewClient extends android.webkit.WebViewClient {
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            if (mDialog .isShowing()) {
//                closeDialog(mDialog);
//            }
////            if (progressBar.isShowing()) {
////                progressBar.dismiss();
////            }
//        }
//
//
//    }
}
