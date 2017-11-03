package com.example.administrator.design.builder;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * 工厂设计模式 发送短信 实现类
 * $name
 * Created by ${LiuTao} on 2017/9/22/022.
 */

public class SmsSender implements Sender {
    @Override
    public void send()
    {
        Log.e(TAG,"发短信");
    }
}
