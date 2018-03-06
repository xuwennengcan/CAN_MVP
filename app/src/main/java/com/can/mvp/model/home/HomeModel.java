package com.can.mvp.model.home;

import com.can.mvp.presenter.home.HomeInterface;

/**
 * Created by can on 2018/3/2.
 */

public class HomeModel implements HomeInterface.Model {
    @Override
    public String getHomeData() {
        return "home Data";

    }
}
