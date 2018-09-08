package com.example.administrator.mvpbase;

import android.os.Bundle;
import android.util.Log;

/**
 * $activityName
 *第二重代理->目标对象->针对的是Activity生命周期
 * @author LiuTao
 * @date 2018/7/9/009
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘

 */

public class MvpActivityDelegateImpl<V extends MvpView, P extends MvpPresenter<V>> implements MvpActivityDelegate<V, P> {
    private String TAG = "MvpActivity";
    private ProxyMvpCallback<V, P> proxyMvpCallback;
    private MvpCallback<V, P> mvpCallback;

    public MvpActivityDelegateImpl(MvpCallback<V, P> callback) {
        this.mvpCallback = callback;
        if (mvpCallback == null) {
            throw new NullPointerException("不能够为空");
        }
        this.proxyMvpCallback = new ProxyMvpCallback<>(mvpCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
        //绑定处理
        this.proxyMvpCallback.createPresenter();
        this.proxyMvpCallback.createView();
        this.proxyMvpCallback.attachView();
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
    public void onRestart() {
        Log.e(TAG,"onRestart");
    }

    @Override
    public void onStop() {
        Log.e(TAG,"onStop");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        this.proxyMvpCallback.detachView();
    }

}
