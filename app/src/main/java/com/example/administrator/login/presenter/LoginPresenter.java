package com.example.administrator.login.presenter;

/**
 * 业务逻辑类 登录逻辑和视图逻辑的桥梁
 * Created by ${LiuTao} on 2017/9/27/027.
 */

public interface LoginPresenter {
    void verifyIdentity(String userName , String Password);

    void onDestory();
}
