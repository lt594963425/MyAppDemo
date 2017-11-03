package com.example.administrator.design.adapter.InterfaceAdapter;

/**
 * $小明好吃懒做 喜欢睡觉和吃东西，玩耍
 * Created by ${LiuTao} on 2017/9/23/023.
 */

public class XiaoMingAdapter extends AllPerson {
    @Override
    public void sleep() {
        System.out.println("爱睡觉");
    }
    @Override
    public void eat() {
        System.out.println("爱吃");
    }
    @Override
    public void play() {
        System.out.println("爱玩");
    }
}
