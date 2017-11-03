package com.example.administrator.design.decorator;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * $name
 * Created by ${LiuTao} on 2017/9/23/023.
 */

public class Decorator_Three extends Decorator {
    public Decorator_Three(Humanable humanable) {
        super(humanable);
    }
    public void findClothes() {

        Log.e(TAG,"找到一件D&G。。。");
    }

    public void findTheTarget() {
        Log.e(TAG,"在Map上找到神秘花园和城堡。。。。。");
    }

    @Override
    public void walk() {
        super.walk();
        findTheTarget();
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        findClothes();
    }
}
