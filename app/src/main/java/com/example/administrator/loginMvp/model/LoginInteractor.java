package com.example.administrator.loginMvp.model;

/**
 * model类 登录逻辑
 * Created by ${LiuTao} on 2017/9/27/027.
 */

public interface LoginInteractor {
      interface OnLoginListener {
          void onUserNameError();
          void onPasswordError();
          void onNameAndPasswordError();
          void onSuccesss();
          void onNetError();
     }
     void LoginNet(String username, String password, OnLoginListener listener);
}
