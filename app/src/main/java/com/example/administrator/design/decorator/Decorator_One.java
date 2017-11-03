package com.example.administrator.design.decorator;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * $name
 * Created by ${LiuTao} on 2017/9/23/023.
 */

public class Decorator_One extends Decorator {

    public Decorator_One(Humanable humanable) {
        super(humanable);
    }
    public void goHome(){
        Log.e(TAG,"进房子。。");
    }
    public void findMap() {
        Log.e(TAG,"书房找找Map。。。。");
    }
    @Override
    public void walk() {
        super.walk();
        findMap();
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        goHome();
    }
}
