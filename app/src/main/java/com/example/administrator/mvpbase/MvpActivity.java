package com.example.administrator.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.base.BaseActivity;

/**
 * $activityName
 *第一重代理->目标对象->实现目标接口(MvpCallback)---->Activity抽象类
 *第二重代理->代理对象->MvpActivity
 * @author LiuTao
 * @date 2018/7/9/009
 */


public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends BaseActivity implements MvpCallback<V, P> {

    private P presneter;
    private V view;

    //第二重代理:持有目标对象引用
    //代理对象持有目标对象引用
    private MvpActivityDelegateImpl<V, P> delegateImpl;

    public MvpActivityDelegateImpl<V, P> getDelegateImpl() {
        if (delegateImpl == null){
            this.delegateImpl = new MvpActivityDelegateImpl(this);
        }
        return delegateImpl;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegateImpl().onCreate(savedInstanceState);

    }

    @Override
    public P createPresenter() {
        return presneter;
    }

    @Override
    public V createView() {
        return view;
    }

    @Override
    public P getPresneter() {
        return presneter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presneter = presenter;
    }

    @Override
    public void setMvpView(V view) {
        this.view = view;
    }

    @Override
    public V getMvpView() {
        return this.view;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDelegateImpl().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDelegateImpl().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDelegateImpl().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getDelegateImpl().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegateImpl().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegateImpl().onDestroy();
    }

}
