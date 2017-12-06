package com.example.administrator.net;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求类
 * Created by caoyu on 2017/11/25/025.
 */

public class RetrofitClient {

    public final static int CONNECT_TIMEOUT = 5;
    public final static int READ_TIMEOUT = 5;
    public final static int WRITE_TIMEOUT = 5;

    private static Retrofit retrofit;
    private static RetrofitClient mInstance;

    public static Retrofit getRetrofit(Context context) {
        if (mInstance == null) {
            synchronized (Retrofit.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient(context);
                }
            }
        }
        return retrofit;
    }

    public RetrofitClient(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.URL_METHOD)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}