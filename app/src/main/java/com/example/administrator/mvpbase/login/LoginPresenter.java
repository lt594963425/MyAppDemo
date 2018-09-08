package com.example.administrator.mvpbase.login;

import com.example.administrator.mvpbase.MvpBasePresenter;

import org.json.JSONObject;


//P层
//特点：持有UI层和数据层引用
public class LoginPresenter extends MvpBasePresenter<LoginView> {

    //持有M层引用
    private LoginModel loginModel;

    //构造方法绑定UI层
    public LoginPresenter() {
        this.loginModel = new LoginModel();
    }

    public void login(String username, String password) {
        this.loginModel.login(username, password, new OkHttpListener() {
            @Override
            public void onSuccess(JSONObject jsonSuccess) {
                getView().onLoginSuccess(jsonSuccess);
            }

            @Override
            public void onFaild(JSONObject jsonFaild) {
                getView().onLoginFaild(jsonFaild);
            }

            @Override
            public void onError(String jsonError) {
                getView().onLoginError(jsonError);
            }

            @Override
            public void onExceptError(String jsonError) {

            }
        });

    }

}
