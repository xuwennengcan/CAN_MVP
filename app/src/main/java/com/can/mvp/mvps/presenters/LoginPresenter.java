package com.can.mvp.mvps.presenters;

import com.can.mvp.mvps.interfaces.LoginInterface;
import com.can.mvp.mvps.models.LoginModel;

/**
 * Created by can on 2018/4/3.
 */

public class LoginPresenter implements LoginInterface.Presenter, LoginInterface.Model.onLoginFinishedListener {
    private LoginInterface.View loginView;
    private LoginModel loginModel;

    public LoginPresenter(LoginInterface.View loginView, LoginModel loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
    }

    @Override public void login(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }
        loginModel.login(username, password, this);
    }

    @Override public void onDestroy() {
        loginView = null;
    }

    @Override public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
