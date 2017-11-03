package com.example.administrator.fragment.fragment4.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.MyApplication;
import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.utils.ACache;
import com.example.administrator.utils.ImageLoader;
import com.example.administrator.utils.ShowUtils;

import static org.jivesoftware.smackx.pubsub.FormType.result;

/**
 * Created by LiuTao on 2017/7/21 0021.
 */

public class ActivityImageLoader extends BaseActivity implements Cloneable{

    private static final String TAG = "ActivityImageLoader";
    //定义布局的控件
    ImageView imageView;
    //定义三级缓存工具类
    ImageLoader loader;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

    //缓存和读取缓存的用法
    public  void statCache(){
//        homemini_data--------->代表唯一标识
//        result---------------->缓存数据
//        60*ACache.TIME_DAY---->缓存时间(60天)
//        缓存时间的其他常量  也可以直接写时间  如  1000*60   代表一分钟
        /**
         * 开始获取数据
         */
        if(MyApplication.getAcache().getAsString("homemini_data")==null) {
           // new MyTask().execute("");
        }else{
            //homeminidata(MyApplication.getAcache().getAsString("homemini_data"));
//            if(checkNetworkState()){
//                new MyTask().execute("");
//            }
        }
      //开始缓存
        MyApplication.getAcache().put("homemini_data", result, 60* ACache.TIME_DAY);
    }



}
