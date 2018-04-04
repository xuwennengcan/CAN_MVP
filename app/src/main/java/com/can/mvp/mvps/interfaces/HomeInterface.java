package com.can.mvp.mvps.interfaces;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBasePresenter;
import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.bean.User;

/**
 * Created by can on 2018/3/2.
 * 首页
 */

public interface HomeInterface {

    interface View extends IBaseView{
        void setUser(User user);
        void error();
    }

    interface Model extends IBaseModel{
        void getUser(String userName,String userPassword,onGetUserFinishedListener listener);
    }

    interface Presenter extends IBasePresenter{
        void getUser(String userName,String userPassword);
        void onDestroy();
    }

}

