package com.example.administrator.design.builder;

import android.os.Bundle;

import com.example.administrator.base.BaseActivity;

/**
 * $name
 * Created by ${LiuTao} on 2017/9/26/026.
 */

public class BuilderTest extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Builder builder = new Builder();
        builder.produceMailSender(10);
        builder.produceSmsSender(10);
    }
}
