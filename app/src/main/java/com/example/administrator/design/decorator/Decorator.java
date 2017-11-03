package com.example.administrator.design.decorator;

/**
 * $装饰类
 * Created by ${LiuTao} on 2017/9/23/023.
 */

public class Decorator implements Humanable {
    private Humanable humanable;

    public Decorator(Humanable humanable) {
        this.humanable = humanable;
    }
    @Override
    public void walk() {
        humanable.walk();
    }

    @Override
    public void wearClothes() {
        humanable.wearClothes();
    }
}
