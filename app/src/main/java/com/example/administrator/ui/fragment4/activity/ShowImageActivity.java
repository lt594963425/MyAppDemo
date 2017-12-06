package com.example.administrator.ui.fragment4.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;

/**
 * $name
 * Created by ${LiuTao} on 2017/10/19/019.
 *
 * @author Administrator
 */

public class ShowImageActivity extends BaseActivity {
    private String path;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ImageView showImage = findViewById(R.id.show_image);
        ViewCompat.setTransitionName(showImage, "head_image");

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
