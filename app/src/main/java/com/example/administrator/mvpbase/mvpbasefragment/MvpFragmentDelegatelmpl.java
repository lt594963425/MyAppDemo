package com.example.administrator.mvpbase.mvpbasefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.administrator.mvpbase.MvpCallback;
import com.example.administrator.mvpbase.MvpPresenter;
import com.example.administrator.mvpbase.MvpView;
import com.example.administrator.mvpbase.ProxyMvpCallback;


/**
 * $activityName
 *  集成MVP设计(父类:抽象部分)
 * @author LiuTao
 * @date 2018/7/9/009
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
 */
public class MvpFragmentDelegatelmpl<V extends MvpView, P extends MvpPresenter<V>>
        implements MvpFragmentDelegate<V,P> {

    private String TAG = "MvpActivity";
    private ProxyMvpCallback<V, P> proxyMvpCallback;
    private MvpCallback<V, P> mvpCallback;

    public MvpFragmentDelegatelmpl(MvpCallback<V, P> callback) {
        this.mvpCallback = callback;
        if (mvpCallback == null) {
            throw new NullPointerException("不能为空");
        }
        this.proxyMvpCallback = new ProxyMvpCallback<>(mvpCallback);
    }


    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //绑定处理
        this.proxyMvpCallback.createPresenter();
        this.proxyMvpCallback.createView();
        this.proxyMvpCallback.attachView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");

    }

    @Override
    public void onStart() {
        Log.e(TAG,"onStart");

    }

    @Override
    public void onPause() {
        Log.e(TAG,"onPause");
    }

    @Override
    public void onResume() {
        Log.e(TAG,"onResume");
    }

    @Override
    public void onStop() {
        Log.e(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG,"onDestroy");
        this.proxyMvpCallback.detachView();
    }

    @Override
    public void onDestroy() {

    }
}
