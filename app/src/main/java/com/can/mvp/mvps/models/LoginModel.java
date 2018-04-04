package com.can.mvp.mvps.models;

import com.can.mvp.mvps.interfaces.LoginInterface;
import com.can.mvp.utils.StringUtils;

/**
 * Created by can on 2018/4/3.
 */

public class LoginModel implements LoginInterface.Model {


    @Override
    public void login(final String username, final String password, final onLoginFinishedListener listener) {
        if(StringUtils.isEmpty(username)){
            listener.onUsernameError();
            return;
        }
        if(StringUtils.isEmpty(password)){
            listener.onPasswordError();
            return;
        }
        listener.onSuccess();
    }
}
