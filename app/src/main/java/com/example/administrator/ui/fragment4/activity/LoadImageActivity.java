package com.example.administrator.ui.fragment4.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.utils.LogUtils;

import java.io.File;

/**
 * $name
 * Created by ${LiuTao} on 2017/10/20/020.
 */

public class LoadImageActivity extends BaseActivity {
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        setTitle("LoadImage");
        ImageView showImage =  findViewById(R.id.image_loader);
        ViewCompat.setTransitionName(showImage, "load_image");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            LogUtils.e("SD卡就绪状态");
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/youma/";
        } else {
            LogUtils.e("SD卡没有就绪状态");
            //保存在内部存储器中
            path = this.getCacheDir().getAbsolutePath() + "/youma/";
        }
        File file = new File(path,"youma.jpg");
        displayImage(String.valueOf(file),showImage);
        //Glide.with(this).load(file).crossFade().into(showImage);
    }
}
