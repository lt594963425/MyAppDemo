package com.example.administrator.mvpbase.login;


import com.example.administrator.NetApi;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

//M层
public class LoginModel {
    public void login(String username, String password, OkHttpListener okHttpListener) {
        OkGo.<String>post(NetApi.hostApi + NetApi.loginApi)
                .params("account", username)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String s = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getString("code").equals("200")) {
                                okHttpListener.onSuccess(jsonObject);
                            } else {
                                okHttpListener.onFaild(jsonObject);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            okHttpListener.onExceptError(e.toString());
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        okHttpListener.onError(response.body());
                    }
                });
    }

}
