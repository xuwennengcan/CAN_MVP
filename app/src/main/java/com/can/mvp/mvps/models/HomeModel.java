package com.can.mvp.mvps.models;

import com.can.mvp.bean.User;
import com.can.mvp.mvps.interfaces.HomeInterface;
import com.can.mvp.utils.StringUtils;

/**
 * Created by can on 2018/3/2.
 */

public class HomeModel implements HomeInterface.Model {

    @Override
    public void getUser(String userName, String userPassword, onGetUserFinishedListener listener) {
        if(StringUtils.isEmpty(userName)){
            listener.onError();
            return;
        }
        if(StringUtils.isEmpty(userPassword)){
            listener.onError();
            return;
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserId(userPassword);
        listener.onSuccess(user);
    }
}
