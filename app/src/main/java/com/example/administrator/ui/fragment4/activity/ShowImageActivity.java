package com.example.administrator.ui.fragment4.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.R;

/**
 * $name
 * Created by ${LiuTao} on 2017/10/19/019.
 * @author Administrator
 */

public class ShowImageActivity extends AppCompatActivity {
   private  String path;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ImageView showImage = (ImageView) findViewById(R.id.show_image);
        ViewCompat.setTransitionName(showImage, "head_image");

    }
}
