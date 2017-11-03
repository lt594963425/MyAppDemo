package com.example.administrator.design.factory;

/**
 * 工厂设计模式 发送短信 实现类
 * $name
 * Created by ${LiuTao} on 2017/9/22/022.
 */

public class SmsSender implements Sender {
    @Override
    public void send(SendListener listener) {
        listener.onSendEvent(P.FINISH,"发短信");
    }
}
