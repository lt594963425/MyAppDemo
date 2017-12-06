package com.example.administrator.loginMvp.model;

import android.text.TextUtils;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * 具体的登录业务逻辑
 * Created by ${LiuTao} on 2017/9/27/027.
 */

public class LoginInteractorImp implements LoginInteractor{
    @Override
    public void LoginNet(String username, String password, final OnLoginListener listener) {
        if (TextUtils.isEmpty(username)) {
            listener.onUserNameError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
        } else {
            requestNet(username, password, listener);
        }


    }

    private void requestNet(String username, String password, final OnLoginListener listener) {
        OkHttpUtils
                .post()
                .url("http://192.168.6.46/ServletTest/LoginServlet")
                .addParams("account", username)
                .addParams("password", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onNetError();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.getString("code").equals("200")) {
                                listener.onSuccesss();
                            } else {
                                listener.onNameAndPasswordError();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}
