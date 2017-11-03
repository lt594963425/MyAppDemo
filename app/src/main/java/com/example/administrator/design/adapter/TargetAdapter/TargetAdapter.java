package com.example.administrator.design.adapter.TargetAdapter;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * $ 类的适配器模式
 * Created by ${LiuTao} on 2017/9/23/023.
 */

public class TargetAdapter extends SurPerson implements ComPerson {
    private SurPerson surPerson;

    public TargetAdapter(SurPerson surPerson) {
        this.surPerson = surPerson;
    }

    @Override
    public void eat() {
        Log.e(TAG,"爱吃");

    }

    @Override
    public void work() {

        Log.e(TAG,"爱工作");
    }

    @Override
    public void play() {
        surPerson.play();
    }
    public  void sleep(){
        surPerson.sleep();
    }

}
