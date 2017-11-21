package com.example.administrator;

import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.administrator.base.BaseActivity;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/19/019
 */

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setBackground(initStateListDrawable());
    }

    private StateListDrawable initStateListDrawable() {
        //初始化一个空对象
        StateListDrawable stalistDrawable = new StateListDrawable();
        //获取对应的属性值 Android框架自带的属性 attr
        int pressed = android.R.attr.state_pressed;
        int focused = android.R.attr.state_focused;

//        stalistDrawable.addState(new int []{-pressed}, getResources().getDrawable(R.drawable.title_button_back));
//        stalistDrawable.addState(new int []{pressed}, getResources().getDrawable(R.drawable.title_button_back_h));
//        stalistDrawable.addState(new int []{-focused }, getResources().getDrawable(R.drawable.title_button_back));
//        //没有任何状态时显示的图片，我们给它设置我空集合
//        stalistDrawable.addState(new int []{}, getResources().getDrawable(R.drawable.title_button_back));
        return stalistDrawable;
    }
}
