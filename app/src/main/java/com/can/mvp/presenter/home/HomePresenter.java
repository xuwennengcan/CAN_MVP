package com.can.mvp.presenter.home;

/**
 * Created by can on 2018/3/2.
 */

public class HomePresenter extends HomeInterface.Presenter {

    @Override
    public void getHomeData() {
        String homeData = model.getHomeData();
        view.homeData(homeData);
    }



}
