package com.example.administrator.ui.fragment4.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;


/**
 * Created by LiuTao on 2017/7/21 0021.
 */

public class ActivityTextInputLayout extends BaseActivity {

    private TextInputLayout textInputLayout;
    private EditText editText, editTextPwd;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textinputlayout);
        setTitle("T");
        textInputLayout = findViewById(R.id.textinputlayout);
        setTitle("密码输入TextInputLayout");
        setBarBack();
        editText = findViewById(R.id.edittext);
        editTextPwd = findViewById(R.id.edit_pwd);
        mButton = findViewById(R.id.btn_login_tpl);
        textInputLayout.setErrorEnabled(true);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if ("".equals(s + "")) {
                        // 设置错误提示语为null，即不显示错误提示语
                        textInputLayout.setError(null);
                    } else if (s.length() > 6) {
                        // 如果输入长度超过6位，则显示错误提示
                        textInputLayout.setError("密码长度超过上限！");
                    } else {
                        Integer.parseInt(s + "");
                        textInputLayout.setError(null);
                    }
                } catch (Exception e) {
                    // 设置错误提示语为具体的文本
                    textInputLayout.setError("输入内容不是数字！");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bindViewByRxBinding();
    }

    private boolean isNameValid(String email) {
        return email.length() > 5;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void bindViewByRxBinding() {


    }
}
