package com.can.mvp.mvps.interfaces;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBasePresenter;
import com.can.mvp.base.mvp.IBaseView;

/**
 * Created by can on 2018/4/3.
 */

public interface LoginInterface {

    interface View extends IBaseView{
        void showProgress();
        void hideProgress();
        void setUsernameError();
        void setPasswordError();
        void navigateToHome();
    }

    interface Model extends IBaseModel{

        void login(String username, String password, onLoginFinishedListener listener);

    }

    interface Presenter extends IBasePresenter{
        void login(String username, String password);
        void onDestroy();
    }

}
