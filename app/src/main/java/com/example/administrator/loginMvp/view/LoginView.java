package com.example.administrator.loginMvp.view;

/**
 * 视图层
 * Created by ${LiuTao} on 2017/9/27/027.
 */

public interface LoginView {
    /**
     * 1,显示progressbar
     * 2,隐藏progressbar
     * 3,用户名不能为空
     * 4,密码不能为空
     * 5,用户名或者密码错误
     */
    void showProgressbar();

    void hindProgressbar();

    void setUserNameError();

    void setPasswordError();

    void setNameAndPWDError();

    void showNetError();

    void goToNextView();
}
