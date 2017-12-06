package com.example.administrator.ui.fragment2.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/10/23/023
 */

public class XuanZhuanActivity extends BaseActivity {
    @BindView(R.id.verry_name)
    EditText mVerryName;
    @BindView(R.id.verry_pws)
    EditText mVerryPws;
    @BindView(R.id.verry_login)
    Button mVerryLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_zhuan);
        ButterKnife.bind(this);
        bindViewByRxBinding();
        setTitle("登录");
    }

    @OnClick(R.id.verry_login)
    public void onViewClicked() {
        rxJava();
    }
    private void bindViewByRxBinding() {
        Observable<CharSequence> observerName = RxTextView.textChanges(mVerryName);
        Observable<CharSequence> observerPwd = RxTextView.textChanges(mVerryPws);
        Observable.combineLatest(observerName, observerPwd, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence name, CharSequence pwd) {
                return isNameValid(name.toString()) && isPasswordValid(pwd.toString());
            }
        }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(Boolean aBoolean) {
                Toast.makeText(XuanZhuanActivity.this, "boole++++++", Toast.LENGTH_SHORT).show();
                if (aBoolean) {
                    mVerryLogin.setEnabled(true);
                } else {
                    mVerryLogin.setEnabled(false);
                }
            }
        });
    }
    private boolean isNameValid(String email) {
        return email.length() > 5;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    private void rxJava() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    for (int i = 0; i < 100; i++) {

                        if (i==20){
                            int a =2;
                            int b= 0;
                            int c =a/b;
                            subscriber.onNext("你好呀"+c);
                            continue;
                        }
                        subscriber.onNext("你好呀"+i);
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(XuanZhuanActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(XuanZhuanActivity.this, "错误下nxi:"+throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
