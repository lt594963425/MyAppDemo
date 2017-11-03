package com.example.administrator.design.decorator;

import android.os.Bundle;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;

/**
 * $装饰者设计模式
 * Created by ${LiuTao} on 2017/9/23/023.
 */

public class DecortorTestActivity extends BaseActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decortor);
        /**
         * 装饰器模式的应用场景：

         1、需要扩展一个类的功能。

         2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）

         缺点：产生过多相似的对象，不易排错！
         */
        Humanable humanable = new Person();
        Decorator decorator = new Decorator_Three(new Decorator_Two(new Decorator_One(humanable)));
        decorator.wearClothes();
        decorator.walk();
    }
}
