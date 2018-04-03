package com.can.mvp.presenter.home;

import com.can.mvp.base.BaseBean;

/**
 * Created by can on 2018/3/2.
 */

public class HomePresenter extends HomeInterface.Presenter {

    @Override
    public void setHomeData(BaseBean bean) {
        view.homeData(bean);
    }



}
