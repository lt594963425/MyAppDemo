package com.example.administrator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.administrator.utils.NetWorkUtils;

/**
 * 监听网络状态
 * Created by LiuTao on 2017/7/28 0028.
 */

public class NetWorkStatusReceiver extends BroadcastReceiver {

    public NetChange evevt = MainActivity.evevt;

    public interface NetChange {
        void onNetChange(int netMobile);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetWorkUtils.getNetType(context);

            Log.e("网络状态:", netWorkState + "");
            evevt.onNetChange(netWorkState);
        }
    }
}