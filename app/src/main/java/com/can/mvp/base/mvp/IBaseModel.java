package com.can.mvp.base.mvp;

import com.can.mvp.bean.responseBean.User;

/**
 * Created by can on 2018/3/2.
 * MVP Model
 */

public interface IBaseModel {

    interface onLoginFinishedListener{
        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }

    interface onGetUserFinishedListener{
        void onError();
        void onSuccess(User user);
    }

    interface onGetDataFinishedListener{
        void onError(String error);
        void onSuccess(String result);
    }


}
