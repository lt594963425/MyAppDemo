package com.example.administrator.mvpbase;

/**
 * $activityName
 *
 * @author LiuTao
 * @date 2018/8/11/011
 */


public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V view;

    public V getView() {
        return view;
    }

    @Override
    public void attachView(V view){
        this.view = view;
    }

    @Override
    public void detachView(){
        this.view = null;
    }


}
