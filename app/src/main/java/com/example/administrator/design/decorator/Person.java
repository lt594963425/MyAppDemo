package com.example.administrator.design.decorator;

import com.example.administrator.utils.LogUtils;

/**
 * $ 被装饰类，
 * Created by ${LiuTao} on 2017/9/23/023.
 */
public class Person implements Humanable{

    @Override
    public void walk() {
        LogUtils.e("去哪儿呢。。。。。。。");
    }

    @Override
    public void wearClothes() {
        LogUtils.e("穿什么呢。。。。。。。");
    }
}

