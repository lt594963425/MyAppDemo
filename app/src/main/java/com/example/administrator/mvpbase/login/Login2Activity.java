package com.example.administrator.mvpbase.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.MainActivity;
import com.example.administrator.R;
import com.example.administrator.mvpbase.MvpActivity;
import com.example.administrator.utils.ToastUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/7/007
 */

public class Login2Activity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.goto_main)
    TextView mGotoMain;

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public LoginView createView() {
        return this;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);

    }


    @Override
    public void onLoginSuccess(JSONObject result) {
        mProgress.setVisibility(View.GONE);
        ToastUtils.showToast("登录成功");
        startActivity(new Intent(Login2Activity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFaild(JSONObject result) {
        mProgress.setVisibility(View.GONE);
        ToastUtils.showToast("登录失败-" + result.toString());
    }

    @Override
    public void onLoginError(String result) {
        mProgress.setVisibility(View.GONE);
        ToastUtils.showToast("登录失败-" + result);
    }

    @Override
    public void onLoginOnexceptError(String result) {
        mProgress.setVisibility(View.GONE);
        ToastUtils.showToast("登录失败_" + result);
    }

    @OnClick({R.id.goto_main, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goto_main:
                startActivity(new Intent(Login2Activity.this, MainActivity.class));
                finish();
                break;
            case R.id.button:
                mProgress.setVisibility(View.VISIBLE);
                getPresneter().login(mUsername.getText().toString(), mPassword.getText().toString());
                break;
        }
    }


}
