package com.example.administrator.ui.fragment2.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.ui.fragment2.View.ButtomDialogFragment;
import com.example.administrator.ui.fragment2.View.LoginDialogFragment;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/21/021
 */

public class LoginDialogActivity extends BaseActivity implements LoginDialogFragment.LoginInputListener {

    @BindView(R.id.button_login)
    Button mButtonLogin;
    @BindView(R.id.text_show)
    TextView mTextShow;
    @BindView(R.id.button_login2)
    Button mButtonLogin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dialog);
        setTitle("LoginDialogFrament");
        ButterKnife.bind(this);
        RxView.clicks(mButtonLogin).subscribe(new Consumer<Object>() {

            @Override
            public void accept(Object o) throws Exception {
                showLoginDialog();
            }
        });
        RxView.clicks(mButtonLogin2).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                showLoginDialog2();
            }
        });
    }

    private void showLoginDialog2() {
       ButtomDialogFragment buttomDialogFragment =  new ButtomDialogFragment();
        buttomDialogFragment.show(this.getFragmentManager(), "loginDialog2");
    }


    private void showLoginDialog() {
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.show(getFragmentManager(), "loginDialog");

    }

    @Override
    public void onLoginInputComplete(String username, String password) {
        mTextShow.setText("帐号：" + username + ",  密码 :" + password);
    }

}
