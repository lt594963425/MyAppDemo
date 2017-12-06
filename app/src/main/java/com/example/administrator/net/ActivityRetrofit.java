package com.example.administrator.net;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * ActivityRetrofit
 * Created by 刘涛 on 2017/6/30 0030.
 */

public class ActivityRetrofit extends BaseActivity implements View.OnClickListener {
    private static final String BASE_URL = "http://apis.juhe.cn/";//http://wthrcdn.etouch.cn/weather_mini?city=
    private static final String TAG = "";
    //private static final String BASE_Weather = "http://wthrcdn.etouch.cn/weather_mini";
    //String path = "http://wthrcdn.etouch.cn/weather_mini?city="+ URLEncoder.encode(cityname,"utf-8");
    private Button button;
    private TextView tvOk;
    private TextView tvError;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        et = (EditText) findViewById(R.id.et_reinput);
        button = (Button) findViewById(R.id.button);
        tvOk = (TextView) findViewById(R.id.tv_ok);
        tvError = (TextView) findViewById(R.id.tv_error);
        button.setOnClickListener(this);
        setBarBack();

    }

    @Override
    public void onClick(View v) {
        Map map = null;

        String phone = et.getText().toString().trim();
        map = new HashMap<>();
        map.put("version_code", "3");

        RetrofitClient.getRetrofit(this)
                .create(Api.class)
                .version_up(map)
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String str = response.body().string();
                            Log.e(TAG, str);
                            tvOk.setText(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }


}
