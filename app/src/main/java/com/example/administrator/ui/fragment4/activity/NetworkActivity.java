package com.example.administrator.ui.fragment4.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;
import com.example.administrator.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author  zkk
 * 简书:    http://www.jianshu.com/u/61f41588151d
 * github:  https://github.com/panacena
 */
public class NetworkActivity extends AppCompatActivity {

    LottieAnimationView animation_view_network;
    private OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        setTitle("N");
        animation_view_network=(LottieAnimationView)findViewById(R.id.animation_view_network);
        loadUrl("http://192.168.6.46/data.json");
    }

    private void loadUrl(String url) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .build();
        } catch (IllegalArgumentException e) {
            return;
        }


        if (client == null) {
            client = new OkHttpClient();
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                }

                try {
                    JSONObject json = new JSONObject(response.body().string());
                    LottieComposition
                            .fromJson(getResources(), json, new LottieComposition.OnCompositionLoadedListener() {
                                @Override
                                public void onCompositionLoaded(LottieComposition composition) {
                                    setComposition(composition);
                                }
                            });
                } catch (JSONException e) {
                }
            }
        });
    }

    private  void setComposition(LottieComposition composition){
        animation_view_network.setProgress(0);
        animation_view_network.loop(true);
        animation_view_network.setComposition(composition);
        animation_view_network.playAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        animation_view_network.cancelAnimation();
    }
}
