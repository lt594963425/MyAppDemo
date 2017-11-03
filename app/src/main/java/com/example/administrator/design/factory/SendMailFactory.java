package com.example.administrator.design.factory;

/**
 * 具体工厂实现类
 * $name
 * Created by ${LiuTao} on 2017/9/22/022.
 */

public  class SendMailFactory implements Provider {
    @Override
    public  void produce(SendListener listener) {
        //创建发送邮箱的类
        new MailSender().send(listener);
    }
}
