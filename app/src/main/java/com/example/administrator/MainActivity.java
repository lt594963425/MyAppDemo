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
import com.example.administrator.fragment.Fragment1;
import com.example.administrator.fragment.Fragment2;
import com.example.administrator.fragment.Fragment3;
import com.example.administrator.fragment.Fragment4;
import com.example.administrator.fragmentHelp.MyFragmentAdapter;
import com.example.administrator.utils.ToastUtils;
import com.example.administrator.view.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends BaseActivity implements
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener, Toolbar.OnMenuItemClickListener {
    //private Realm realm;
    private FragmentTabHost mTabHost;
    private Class fragmentArray[] = {Fragment1.class, Fragment2.class, Fragment3.class, Fragment4.class};
    private static int imageViewArray[] = {R.drawable.tab_view_btn, R.drawable.tab_view_btn, R.drawable.tab_view_btn, R.drawable.tab_view_btn};
    private static String textViewArray[] = {"首页", "分类", "天气", "大学"};
    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPager vp;
    private MaterialSearchView mSearchView;
    public ArrayList<String> mDatas =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();//初始化控件
        initPage();//初始化页面
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
    //    控件初始化控件
    private void initView() {

        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        mToolbar.setTitle("有码走天下");
        mToolbar.inflateMenu(R.menu.activity_main_toolbar);
        mToolbar.setOnMenuItemClickListener(this);


        vp = (ViewPager) findViewById(R.id.pager);

        /*实现OnPageChangeListener接口,目的是监听Tab选项卡的变化，然后通知ViewPager适配器切换界面*/
        /*简单来说,是为了让ViewPager滑动的时候能够带着底部菜单联动*/

        vp.addOnPageChangeListener(this);//设置页面切换时的监听器
        vp.setOffscreenPageLimit(4);
        /*实例化FragmentTabHost对象并进行绑定*/
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);//绑定tahost
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);//绑定viewpager

        /*实现setOnTabChangedListener接口,目的是为监听界面切换），然后实现TabHost里面图片文字的选中状态切换*/
        /*简单来说,是为了当点击下面菜单时,上面的ViewPager能滑动到对应的Fragment*/
        mTabHost.setOnTabChangedListener(this);

        int count = imageViewArray.length;
        /*新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment*/
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置标签、图标和文字
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中，并绑定Fragment
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);//设置Tab被选中的时候颜色改变
        }
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
                ToastUtils.showToast("点击了:"+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                ToastUtils.showToast("改变了:"+newText);
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

    /*初始化Fragment*/
    private void initPage() {
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        //绑定Fragment适配器
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        mTabHost.getTabWidget().setDividerDrawable(null);
    }

    private View getTabItemView(int i) {
        //将xml布局转换为view对象
        View view = LayoutInflater.from(this).inflate(R.layout.tab_content, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }


    @Override
    public void onPageScrollStateChanged(int arg0) {

    }//arg0 ==1的时候表示正在滑动，arg0==2的时候表示滑动完毕了，arg0==0的时候表示什么都没做，就是停在那。

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }//表示在前一个页面滑动到后一个页面的时候，在前一个页面滑动前调用的方法

    @Override
    public void onPageSelected(int arg0) {//arg0是表示你当前选中的页面位置Postion，这事件是在你页面跳转完毕的时候调用的。
        TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);//设置View覆盖子类控件而直接获得焦点,防止被顶起
        mTabHost.setCurrentTab(arg0);//根据位置Postion设置当前的Tab
        widget.setDescendantFocusability(oldFocusability);//设置取消分割线

    }

    @Override
    public void onTabChanged(String tabId) {//Tab改变的时候调用
        int position = mTabHost.getCurrentTab();
        vp.setCurrentItem(position);//把选中的Tab的位置赋给适配器，让它控制页面切换
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
