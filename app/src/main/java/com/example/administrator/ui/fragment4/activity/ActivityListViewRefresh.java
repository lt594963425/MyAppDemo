package com.example.administrator.ui.fragment4.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.view.RefreshListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 刘涛 on 2017/6/29 0029.
 */

public class ActivityListViewRefresh extends BaseActivity {
    private RefreshListView mlistView;
    private myAdapter adapter;
    private List<String> mlistdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrefresh);
        setTitle("listview刷新");
        setBarBack();
        mlistView = (RefreshListView) findViewById(R.id.lv_list);
        mlistdata = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mlistdata.add("我是listView的第" + i + "条数据");
        }
        adapter = new myAdapter();
        mlistView.setAdapter(adapter);
        mlistView.setOnefreshlistener(new RefreshListView.onRefreshListener() {
            @Override
            public void onPullDownRefresh() {
                Toast.makeText(ActivityListViewRefresh.this, "开始下拉刷新了", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mlistdata.add(0, "我是下拉刷新出来的数据..");
                        adapter.notifyDataSetChanged();
                        // 把头布局隐藏掉
                        mlistView.onRefreshFinish();
                    }
                }, 3000);
            }

            @Override
            public void onLoadingMore() {
                Toast.makeText(ActivityListViewRefresh.this, "开始上拉刷新了", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mlistdata.add(mlistdata.size(), "我是加载更多出来的数据..");
                        adapter.notifyDataSetChanged();
                        mlistView.onRefreshFinish();
                    }
                }, 3000);
            }
        });
    }

    class myAdapter extends BaseAdapter {
        /**
         * How many items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            return mlistdata.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list_tv, parent, false);
            TextView tv = (TextView) view.findViewById(R.id.text_list);
            tv.setText(mlistdata.get(position));
            return tv;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
