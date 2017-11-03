package com.example.administrator.design.adapter;

import android.os.Bundle;

import com.example.administrator.design.adapter.ClassAdapter.ClassAdapter;
import com.example.administrator.design.adapter.InterfaceAdapter.XiaoHuaAdapter;
import com.example.administrator.design.adapter.InterfaceAdapter.XiaoMingAdapter;
import com.example.administrator.design.adapter.TargetAdapter.SurPerson;
import com.example.administrator.design.adapter.TargetAdapter.TargetAdapter;
import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;

/**
 * 适配器模式
 * Created by ${LiuTao} on 2017/9/23/023.
 */

public class AdapterTestActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adater);
        init();
    }

    public void init() {
        //类的适配器模式
        ClassAdapter personAdapter = new ClassAdapter();
        personAdapter.eat();
        personAdapter.work();
        personAdapter.play();
        personAdapter.sleep();
        //对象适配器模式
        SurPerson surPerson = new SurPerson();
        TargetAdapter targetAdapter2 = new TargetAdapter(surPerson);
        targetAdapter2.sleep();
        targetAdapter2.play();
        targetAdapter2.work();
        targetAdapter2.eat();
        /**
         * v 接口适配器模式
         * 接口的适配器是这样的：有时我们写的一个接口中有多个抽象方法，当我们写该接口的实现类时，
         * 必须实现该接口的所有方法，这明显有时比较浪费，因为并不是所有的方法都是我们需要的，
         * 有时只需要某一些，此处为了解决这个问题，我们引入了接口的适配器模式，借助于一个抽象类，
         * 该抽象类实现了该接口，实现了所有的方法，而我们不和原始的接口打交道，
         * 只和该抽象类取得联系，所以我们写一个类，继承该抽象类，重写我们需要的方法就行
         */
        XiaoMingAdapter xiaoMing = new XiaoMingAdapter();
        xiaoMing.eat();
        xiaoMing.sleep();
        xiaoMing.play();
        XiaoHuaAdapter xiaoHua = new XiaoHuaAdapter();
        xiaoHua.study();
        xiaoHua.work();
    }
}
