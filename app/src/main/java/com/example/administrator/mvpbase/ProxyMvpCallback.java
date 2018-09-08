package com.example.administrator.mvpbase;

/**
 * $activityName
 * 第一重代理->代理对象->ProxyMvpCallback(实现目标接口)
 * 直接封装MVP实现
 *
 * @author LiuTao
 * @date 2018/7/9/009
 */


public class ProxyMvpCallback<V extends MvpView, P extends MvpPresenter<V>> implements MvpCallback<V, P> {

    //持有目标对象引用->Activity
    //mvpCallback->本质就是Actiivty
    private MvpCallback<V, P> mvpCallback;

    public ProxyMvpCallback(MvpCallback<V, P> mvpCallback) {
        this.mvpCallback = mvpCallback;
    }

    @Override
    public P createPresenter() {
        P presenter = mvpCallback.getPresneter();
        if (presenter == null) {
            presenter = mvpCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("Presenter is not null!");
        }

        // 绑定
        mvpCallback.setPresenter(presenter);
        return getPresneter();
    }

    @Override
    public V createView() {
        V view = mvpCallback.getMvpView();
        if (view == null) {
            view = mvpCallback.createView();
        }
        if (view == null) {
            throw new NullPointerException("Presenter is not null!");
        }

        // 绑定
        mvpCallback.setMvpView(view);
        return getMvpView();
    }

    @Override
    public void setMvpView(V view) {
        this.mvpCallback.setMvpView(view);
    }

    /**
     * 获取presenter
     *
     * @return
     */
    @Override
    public P getPresneter() {
        P presenter = mvpCallback.getPresneter();
        if (presenter == null) {
            // 抛异常
            throw new NullPointerException("Presenter is not null!");
        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mvpCallback.setPresenter(presenter);
    }

    @Override
    public V getMvpView() {
        return mvpCallback.getMvpView();
    }

    public void attachView() {
        getPresneter().attachView(getMvpView());
    }

    public void detachView() {
        getPresneter().detachView();
    }

}
