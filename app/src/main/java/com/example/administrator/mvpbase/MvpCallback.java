package com.example.administrator.mvpbase;

/**
 * $activityName
 * 第一重代理->目标接口->抽象解绑和绑定(MvpCallback)
 *
 * @author LiuTao
 * @date 2018/7/9/009
 */

public interface MvpCallback<V extends MvpView, P extends MvpPresenter<V>> {

    //创建Presenter
    P createPresenter();

    //创建View
    V createView();

    void setPresenter(P presenter);

    P getPresneter();

    void setMvpView(V view);

    V getMvpView();
}
