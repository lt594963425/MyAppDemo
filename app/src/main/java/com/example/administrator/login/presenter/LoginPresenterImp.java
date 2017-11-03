package com.example.administrator.login.presenter;

import com.example.administrator.login.model.LoginInteractor;
import com.example.administrator.login.model.LoginInteractorImp;
import com.example.administrator.login.view.LoginView;

/**
 * $name
 * Created by ${LiuTao} on 2017/9/27/027.
 */

public class LoginPresenterImp implements LoginPresenter, LoginInteractor.OnLoginListener {
    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImp();
    }

    @Override
    public void verifyIdentity(String userName, String Password) {
        if (loginView != null)
            loginView.showProgressbar();
        loginInteractor.LoginNet(userName, Password, this);

    }

    @Override
    public void onDestory() {
        loginView = null;
    }


    @Override
    public void onUserNameError() {
        if (loginView != null) {
            loginView.hindProgressbar();
            loginView.setUserNameError();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.hindProgressbar();
            loginView.setPasswordError();
        }
    }

    @Override
    public void onNameAndPasswordError() {
        if (loginView != null) {
            loginView.hindProgressbar();
            loginView.setNameAndPWDError();
        }
    }

    @Override
    public void onSuccesss() {
        if (loginView != null) {
            loginView.hindProgressbar();
            loginView.goToNextView();
        }

    }

    @Override
    public void onNetError() {
        if (loginView != null) {
            loginView.hindProgressbar();
            loginView.showNetError();
        }
    }
}
