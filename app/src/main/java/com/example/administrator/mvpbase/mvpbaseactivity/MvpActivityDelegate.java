package com.example.administrator.mvpbase.mvpbaseactivity;

import android.os.Bundle;

import com.example.administrator.mvpbase.MvpPresenter;
import com.example.administrator.mvpbase.MvpView;


/**
 * $activityName
 * 第二重代理->目标接口->针对Activity生命周期进行代理
 * @author LiuTao
 * @date 2018/7/9/009
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘
 */


public interface MvpActivityDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onPause();

    public void onResume();

    public void onRestart();

    public void onStop();

    public void onDestroy();

}
