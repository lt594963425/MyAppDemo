package com.example.administrator.design.builder;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * $name
 * Created by ${LiuTao} on 2017/9/22/022.
 */

public class MailSender implements Sender {
    @Override
    public void send() {
        Log.e(TAG,"发送邮件");
    }
}
