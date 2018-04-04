package com.can.mvp.mvps.presenters;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.bean.User;
import com.can.mvp.mvps.interfaces.HomeInterface;
import com.can.mvp.mvps.models.HomeModel;

/**
 * Created by can on 2018/4/4.
 */

public class HomePresenter implements HomeInterface.Presenter, IBaseModel.onGetUserFinishedListener {

    private HomeInterface.View view;
    private HomeModel model;

    public HomePresenter(HomeInterface.View view,HomeModel model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void getUser(String userName, String userPassword) {
        if(model!=null)
            model.getUser(userName,userPassword,this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onError() {
        if(view!=null)
            view.error();
    }

    @Override
    public void onSuccess(User user) {
        if(view!=null&&user!=null)
            view.setUser(user);
    }
}
