package com.example.administrator.mvpbase.login;


import com.example.administrator.mvpbase.MvpView;

import org.json.JSONObject;

/**
 * 作者: Dream on 2017/9/4 21:23
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public interface LoginView extends MvpView {

    void onLoginSuccess(JSONObject result);
    void onLoginFaild(JSONObject result);
    void onLoginError(String result);
    void onLoginOnexceptError(String result);

}
