package com.example.administrator.design.factory;

/**
 * 工厂设计模式 接口
 * 发送的消息类，需要实现该接口
 * $name
 * Created by ${LiuTao} on 2017/9/22/022.
 */
public interface Sender {
     void send(SendListener listener);
}
