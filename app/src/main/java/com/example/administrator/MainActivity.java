package com.example.administrator;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.administrator.base.BaseActivity;
import com.example.administrator.ui.adapter.FixPagerAdapter;
import com.example.administrator.ui.factory.FragmentFactory;
import com.example.administrator.utils.ToastUtils;
import com.example.administrator.view.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author LiuTao
 */
public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener, Toolbar.OnMenuItemClickListener {
    //private Realm realm;
    private FragmentTabHost mTabHost;
    private static int tabImages[] = {
            R.drawable.tab_view_btn,
            R.drawable.tab_view_btn,
            R.drawable.tab_view_btn,
            R.drawable.tab_view_btn};
    private static String titles[] = {"首页", "分类", "天气", "大学"};
    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    private MaterialSearchView mSearchView;
    public ArrayList<String> mDatas = null;
    private FixPagerAdapter mFixPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initData();
        initViewPagerFargment();//初始化控件
        initSearchView();
    }

    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDatas.add(i, String.valueOf(i));
        }
    }

    private void initViewPagerFargment() {
        mViewPager = findViewById(R.id.pager);
        mTabHost = findViewById(android.R.id.tabhost);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                TabWidget widget = mTabHost.getTabWidget();
                int oldFocusability = widget.getDescendantFocusability();
                widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);//设置View覆盖子类控件而直接获得焦点,防止被顶起
                mTabHost.setCurrentTab(position);//根据位置Postion设置当前的Tab
                //widget.setDescendantFocusability(oldFocusability);//设置取消分割线
            }
        });//设置页面切换时的监听器
        mViewPager.setOffscreenPageLimit(4);
        mFixPagerAdapter = new FixPagerAdapter(getSupportFragmentManager());
        mFragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            mFragments.add(FragmentFactory.createFragment(i));
        }
        mFixPagerAdapter.setFragments(mFragments);
        mFixPagerAdapter.setTitles(titles);
        mViewPager.setAdapter(mFixPagerAdapter);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);//绑定viewpager
        mTabHost.setOnTabChangedListener(this);
        for (int i = 0; i < tabImages.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(titles[i])
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, mFragments.get(i).getClass(), null);
            mTabHost.setTag(i);
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);//设置Tab被选中的时候颜色改变
        }
        mTabHost.getTabWidget().setDividerDrawable(null);
    }

    private View getTabItemView(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_content, null);
        ImageView imageView = view.findViewById(R.id.tab_imageview);
        TextView mTextView = view.findViewById(R.id.tab_textview);
        imageView.setImageResource(tabImages[i]);
        mTextView.setText(titles[i]);
        return view;
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        mToolbar.setTitle("有码走天下");
        mToolbar.inflateMenu(R.menu.activity_main_toolbar);
        mToolbar.setOnMenuItemClickListener(this);
    }


    private void initSearchView() {
        mSearchView = findViewById(R.id.search_view);
        mSearchView.setVoiceSearch(false);
        mSearchView.setHint("搜索");
        mSearchView.setCursorDrawable(R.drawable.bg_cameral_album);
        mSearchView.setSuggestions(mDatas.toArray(new String[mDatas.size()]));
      /*  //设置搜索结果提示
        List<ContentDTO> contentList = mRealm.where(ContentDTO.class).findAll();
        String[] contentNames = new String[contentList.size()];
        for (int i = 0; i < contentList.size(); i++) {
            contentNames[i] = contentList.get(i).getName();
        }*/
        mSearchView.setEllipsize(true);
        //监听搜索结果点击事件
        mSearchView.setOnSuggestionClickListener(new MaterialSearchView.OnSuggestionClickListener() {
            @Override
            public void onSuggestionClick(final String name) {
                //延时以展示水波纹效果
                mSearchView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSearchView.closeSearch();
                        ToastUtils.showToast(name);
                    }

                }, 200);
            }
        });
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                ToastUtils.showToast("点击了:" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                ToastUtils.showToast("改变了:" + newText);
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                ToastUtils.showToast("弹出");
            }

            @Override
            public void onSearchViewClosed() {
                ToastUtils.showToast("关闭");
                //Do some magic
            }
        });
    }

    @Override
    public void onTabChanged(String tabId) {//Tab改变的时候调用
        int position = mTabHost.getCurrentTab();
        mViewPager.setCurrentItem(position);//把选中的Tab的位置赋给适配器，让它控制页面切换
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_toolbar, menu);


        ToastUtils.showToast("点击");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                mSearchView.setMenuItem(item);
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
