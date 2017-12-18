package com.example.administrator.ui.fragment2.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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
        //rxJava();
    }

    private void bindViewByRxBinding() {
        Observable<CharSequence> observerName = RxTextView.textChanges(mVerryName);
        Observable<CharSequence> observerPwd = RxTextView.textChanges(mVerryPws);
        Observable.combineLatest(observerName, observerPwd, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence name, CharSequence pwd) throws Exception {
                return isNameValid(name.toString()) && isPasswordValid(pwd.toString());
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Toast.makeText(XuanZhuanActivity.this, "boole++++++", Toast.LENGTH_SHORT).show();
                if (aBoolean) {
                    mVerryLogin.setEnabled(true);
                } else {
                    mVerryLogin.setEnabled(false);
                }
            }
        });
    }
//        Observable.combineLatest(observerName, observerPwd, new Func2<CharSequence, CharSequence, Boolean>() {
//            @Override
//            public Boolean call(CharSequence name, CharSequence pwd) {
//                return isNameValid(name.toString()) && isPasswordValid(pwd.toString());
//            }
//        }).subscribe(new Subscriber<Boolean>() {
//            @Override
//            public void onCompleted() {
//
//            }
//            @Override
//            public void onError(Throwable e) {
//
//            }
//            @Override
//            public void onNext(Boolean aBoolean) {
//                Toast.makeText(XuanZhuanActivity.this, "boole++++++", Toast.LENGTH_SHORT).show();
//                if (aBoolean) {
//                    mVerryLogin.setEnabled(true);
//                } else {
//                    mVerryLogin.setEnabled(false);
//                }
//            }
//        });

    private boolean isNameValid(String email) {
        return email.length() > 5;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    private void rxJava() {
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                    for (int i = 0; i < 100; i++) {

                        if (i == 20) {
                            int a = 2;
                            int b = 0;
                            int c = a / b;
                            e.onNext("你好呀" + c);
                            continue;
                        }
                        e.onNext("你好呀" + i);
                        Thread.sleep(2000);
                    }
             
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(XuanZhuanActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(XuanZhuanActivity.this, "错误下nxi:" + throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}

  