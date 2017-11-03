package com.example.administrator.design.factory;

/**
 * 具体工厂实现类
 * $name
 * Created by ${LiuTao} on 2017/9/22/022.
 */

public class SendSMSFactory implements Provider {
    @Override
    public void produce(SendListener listener) {
        new SmsSender().send(listener);
    }
}
