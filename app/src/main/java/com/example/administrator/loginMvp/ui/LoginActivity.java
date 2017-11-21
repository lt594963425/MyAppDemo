package com.example.administrator.loginMvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.administrator.MainActivity;
import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.loginMvp.presenter.LoginPresenter;
import com.example.administrator.loginMvp.presenter.LoginPresenterImp;
import com.example.administrator.loginMvp.view.LoginView;
import com.example.administrator.utils.ToastUtils;


/**
 * ui层
 * Created by ${LiuTao} on 2017/9/27/027.
 */

public class LoginActivity extends BaseActivity implements LoginView {
    private EditText username;
    private EditText pwd;
    private ProgressBar progress;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setBarBack();
        setTitle("登录MVP");
        pwd = (EditText) findViewById(R.id.pwd);
        username = (EditText) findViewById(R.id.username);
        progress = (ProgressBar) findViewById(R.id.login_progress);
        loginPresenter = new LoginPresenterImp(this);
    }
    public void Login(View view) {
        loginPresenter.verifyIdentity(username.getText().toString(),pwd.getText().toString());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestory();
    }

    @Override
    public void showProgressbar() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hindProgressbar() {
        progress.setVisibility(View.GONE);

    }


    @Override
    public void setUserNameError() {
        username.setError("用户名不能为空",getDrawable(R.drawable.small_dot));
}

    @Override
    public void setPasswordError() {
        pwd.setError("密码不能为空");
    }

    @Override
    public void setNameAndPWDError() {
        ToastUtils.showToast("用户名或者密码错误");
    }

    @Override
    public void showNetError() {
        ToastUtils.showToast("登录超时，请稍再试");
    }

    @Override
    public void goToNextView() {
        ToastUtils.showToast("登录成功");
        openActivity(MainActivity.class);

    }


}
