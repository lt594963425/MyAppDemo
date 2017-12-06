package com.example.administrator.ui.fragment4.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.utils.ImageLoader;
import com.example.administrator.utils.ShowUtils;

/**
 * Created by LiuTao on 2017/7/21 0021.
 */

public class ActivityImageLoader extends BaseActivity implements Cloneable{
    private static final String TAG = "ActivityImageLoader";
    ImageView imageView;
    ImageLoader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowUtils.DEBUG = false;
        setContentView(R.layout.activity_load_image);
        setTitle("加载图片");
        setBarBack();
        long time = System.currentTimeMillis();
        String fileName = String.valueOf(time);
        imageView = (ImageView) findViewById(R.id.main_iv);
        loader = new ImageLoader(this, "youma");//创建文件夹
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityImageLoader.this, LoadImageActivity.class),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(ActivityImageLoader.this, v, "load_image").toBundle());
                overridePendingTransition(0, 0);
            }
        });
    }
    public void start(View v){
        String url = "http://p3.so.qhmsg.com/bdr/326__/t018da60b972e086a1d.jpg";
        loader.loadImage(url, new ImageLoader.ImageLoadListener() {
            @Override
            public void loadImage(Bitmap bmp) {
               // Bitmap bitmap = Bitmap.createScaledBitmap(bmp,250,450,true);
                imageView.setImageBitmap(bmp);
            }
        });
    }


}
