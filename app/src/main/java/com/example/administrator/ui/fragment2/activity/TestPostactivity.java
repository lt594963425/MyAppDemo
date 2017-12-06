package com.example.administrator.ui.fragment2.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * Created
 * by ${liuTao} on 2017/9/2/002.
 * @author Administrator
 */

public class TestPostactivity extends BaseActivity {

    private static final String TAG = "TestPostactivity";
    private EditText post_url1;
    private Button btn1;
    private TextView text_show1;

    private EditText post_url2;
    private Button btn2;
    private TextView text_show2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_post);
        setTitle("post请求");
        init();
    }

    private void init() {
        findViewById(R.id.post_btn1);
        post_url1 = (EditText) findViewById(R.id.post_url1);
        findViewById(R.id.post_btn2);
        post_url2 = (EditText) findViewById(R.id.post_url2);
        text_show1 = (TextView) findViewById(R.id.text_show1);

    }

    //PostAgin
    public void Post(View v) {
        String url = post_url1.getText().toString();
        net(url);
    }

    public void PostAgin(View view) {
        String url = post_url2.getText().toString();
        net(url);
    }

    private void net(String url) {
        OkHttpUtils.post().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG,response);
                text_show1.setText(response);
            }
        });

    }


}
