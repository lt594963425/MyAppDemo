package com.example.administrator.design.decorator;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * $name
 * Created by ${LiuTao} on 2017/9/23/023.
 */

public class Decorator_Two extends Decorator{
    public Decorator_Two(Humanable humanable) {
        super(humanable);
    }
    public void goClothespress() {
        Log.e(TAG,"去衣柜找找看。。。。。");
    }

    public void findPlaceOnMap() {
        Log.e(TAG,"在Map上找找。。。。。。。");
    }

    @Override
    public void walk() {
        super.walk();
        findPlaceOnMap();
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        goClothespress();
    }
}
