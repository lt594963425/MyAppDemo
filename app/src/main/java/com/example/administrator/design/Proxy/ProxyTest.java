package com.example.administrator.design.Proxy;

import android.os.Bundle;

import com.example.administrator.base.BaseActivity;

/**
 * $name
 * Created by ${LiuTao} on 2017/9/26/026.
 */

public class ProxyTest extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sourceable source = new Proxy();
        source.method();
    }
}
