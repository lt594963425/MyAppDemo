package com.example.administrator.ui.fragment4.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.utils.ToastUtils;
import com.example.administrator.view.XToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * RecyclerView recyclerView
 */
public class ActivityTwo extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    @BindView(R.id.toolbar)
    XToolbar mToolbar;
    @BindView(R.id.rvw)
    RecyclerView mRvw;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.left)
    LinearLayout mLeft;
    @BindView(R.id.right)
    LinearLayout mRight;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);
        //Toolbar和drawerLayout的联合使用 ActionBarDrawerToggle()

        //设置导航图标
        mToolbar.setLogo(R.drawable.ic_dialog_email);
        mToolbar.setTitle("刘涛");
        mToolbar.setSubtitle("刘媛");
        mToolbar.inflateMenu(R.menu.menu);

        mToolbar.setOnMenuItemClickListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerlayout, mToolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();//同步
        mDrawerlayout.addDrawerListener(toggle);
        mDrawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mRvw = (RecyclerView) findViewById(R.id.rvw);
     /*   GridLayoutManager gm = new GridLayoutManager(this,3);
        gm.setOrientation(GridLayoutManager.VERTICAL);*/

        mRvw.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRvw.setAdapter(new MyRecyclerView());

        final SwipeRefreshLayout mRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mRefresh.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mRefresh.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ActivityTwo.this, "正在努力加载", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //隐藏进度条
                        mRefresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }


    class MyRecyclerView extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_rv, parent, false);

            return new MyViewHolder(view);
        }

        //绑定数据
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder mh = (MyViewHolder) holder;
            mh.setDate(position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvs;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvs = (TextView) itemView.findViewById(R.id.tv);
            ViewGroup.LayoutParams lp = tvs.getLayoutParams();
            lp.height = (int) (200 + Math.random() * 400);
            tvs.setLayoutParams(lp);
        }

        public void setDate(final int position) {

            tvs.setText("我是第" + position + "位置");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityTwo.this, "点击" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.jy:
                //普通模式m
                mDrawerlayout.openDrawer(mRight);
                mDrawerlayout.openDrawer(mRight);
                break;
            case R.id.id_map_model_common:
                //普通模式m
                mDrawerlayout.openDrawer(mRight);

                break;
            case R.id.id_map_model_following:
                //跟随模式
                mDrawerlayout.openDrawer(mLeft);

                break;
            case R.id.id_map_model_compass:
                //罗盘模式

                ToastUtils.showToast("罗盘模式");
                break;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
