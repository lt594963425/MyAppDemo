package com.example.administrator.ui.fragment4.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by 刘涛 on 2017/6/30 0030.
 */

public class OKHttpActivity extends BaseActivity implements View.OnClickListener {
    public static final String URL_GET = "http://apis.juhe.cn/mobile/get?phone=13812345678&key=daf8fa858c330b22e342c882bcbac622";
    public static final String URL_POST = "http://apis.juhe.cn/mobile/get ";
    public static final String URL_POSTs = "http://192.168.6.46/improve/version_manager/version_update ";

    private Button btnGet;
    private Button btnPost;
    private TextView tvOK;
    private TextView tvError;
    private OkHttpClient client;
private EditText etInput;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String error = (String) msg.obj;
                    tvError.setText(error);

                    break;
                case 1:
                    String result = (String) msg.obj;
                    tvOK.setText(result);

                    break;
            }
        }
    };
    private String TAG = "okhttp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        setTitle("网络请求");
        setBarBack();
        btnGet = (Button) findViewById(R.id.btn_get);
        btnPost = (Button) findViewById(R.id.btn_post);
        tvOK = (TextView) findViewById(R.id.tv_OK);
        tvError = (TextView) findViewById(R.id.tv_error);
        etInput = (EditText) findViewById(R.id.et_input);
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        client = new OkHttpClient();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                //getokhttp();
                OkHttpUtils
                        .post()
                        .url(URL_POSTs)
                        .addParams("version_code", "3")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                               Log.e(TAG,response);
                               tvError.setText(response);
                            }
                        });
                break;
            case R.id.btn_post:
                String phone = etInput.getText().toString().trim();
                RequestBody body = new FormBody.Builder()
                        .add("phone", phone)// 构造请求的参数
                        .add("key", "daf8fa858c330b22e342c882bcbac622")// 构造请求的参数
                        .build();
                RequestBody body2 = new FormBody.Builder()
                        .add("version_code", "3")// 构造请求的参数
                        .build();
                Request post_request = new Request.Builder()
                        .url(URL_POSTs)// 指定请求的地址
                        .post(body2)// 指定请求的方式为POST
                        .build();
                client.newCall(post_request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = e.toString();
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        ResponseBody body = (ResponseBody)response.body();
                        String string = body.string();// 把返回的结果转换为String类型
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = string;
                        handler.sendMessage(msg);
                    }
                });
                break;
            default:
                break;
        }
    }

    private void getokhttp() {
        Log.e(TAG,"___________");
        Request get_request = new Request.Builder()
                .url("http://127.0.0.1/data.json")
                .build();
        // client.newCall(get_request).execute()
        client.newCall(get_request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e(TAG,string);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
