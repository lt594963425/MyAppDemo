package com.example.administrator.ui.fragment2.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.ui.fragment2.View.LoginDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/21/021
 */

public class LoginDialogActivity extends BaseActivity implements LoginDialogFragment.LoginInputListener{

    @BindView(R.id.button_login)
    Button mButtonLogin;
    @BindView(R.id.text_show)
    TextView mTextShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dialog);
        ButterKnife.bind(this);
    }


    private  void showLoginDialog() {
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getFragmentManager(), "loginDialog");

    }

    @Override
    public void onLoginInputComplete(String username, String password) {
        mTextShow.setText("帐号：" + username + ",  密码 :" + password);
    }

    @OnClick(R.id.button_login)
    public void onViewClicked() {
        showLoginDialog();
    }
}
