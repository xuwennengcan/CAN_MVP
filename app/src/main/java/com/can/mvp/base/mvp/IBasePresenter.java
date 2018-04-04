package com.can.mvp.base.mvp;

import com.can.mvp.bean.requestBean.BaseRequestBean;

/**
 * Created by can on 2018/3/2.
 * MVP Presenter
 */

public interface IBasePresenter {

    interface BaseHomePresenter{
        void getUser(String userName,String userPassword);
        void onDestroy();
    }

    interface BaseLoginPresenter{
        void login(String username, String password);
        void onDestroy();
    }

    interface BasePresenter{
        void getData(BaseRequestBean baseRequestBean);
        void onDestroy();
    }


}
