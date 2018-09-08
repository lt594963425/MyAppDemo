package com.example.administrator.mvpbase.mvpbasefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.base.BaseFragment;
import com.example.administrator.mvpbase.MvpCallback;
import com.example.administrator.mvpbase.MvpPresenter;
import com.example.administrator.mvpbase.MvpView;


/**
 * $activityName
 * 集成MVP设计(父类:抽象部分)
 *
 * @author LiuTao
 * @date 2018/7/9/009
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
 */
public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends BaseFragment
        implements MvpCallback<V, P> {
    private P presneter;
    private V view;

    //第二重代理:持有目标对象引用
    //代理对象持有目标对象引用
    private MvpFragmentDelegatelmpl<V, P> delegateImpl;

    public MvpFragmentDelegatelmpl<V, P> getDelegateImpl() {
        if (delegateImpl == null) {
            this.delegateImpl = new MvpFragmentDelegatelmpl(this);
        }
        return delegateImpl;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegateImpl().onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDelegateImpl().onCreateView(inflater, container, savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
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
    public void onStart() {
        super.onStart();
        getDelegateImpl().onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
        getDelegateImpl().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getDelegateImpl().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        getDelegateImpl().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getDelegateImpl().onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getDelegateImpl().onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getDelegateImpl().onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDelegateImpl().onActivityCreated(savedInstanceState);
    }


}
