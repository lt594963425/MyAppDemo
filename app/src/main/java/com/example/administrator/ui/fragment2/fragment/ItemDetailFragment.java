package com.example.administrator.ui.fragment2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseFragment;
import com.example.administrator.ui.fragment2.bean.Item;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/21/021
 */

public class ItemDetailFragment extends BaseFragment {
    private TextView tvDetail;



    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Unregister
        EventBus.getDefault().unregister(this);
    }

    /** List点击时会发送些事件，接收到事件后更新详情 */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Item item)
    {
        if (item != null) {
            tvDetail.setText(item.content);
        }
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View rootView = inflater.inflate(R.layout.fragment_item_detail,
                container, false);
        tvDetail = (TextView) rootView.findViewById(R.id.item_detail);
        return rootView;
    }

    @Override
    protected void initData() {

    }
}
