package com.can.mvp.presenter.home;

import com.can.mvp.base.mvp.BasePresenter;
import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBaseView;

/**
 * Created by can on 2018/3/2.
 * 首页
 */

public interface HomeInterface {

    interface View extends IBaseView{
        void homeData(String content);
    }

    interface Model extends IBaseModel{
        String getHomeData();
    }

     abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getHomeData();
    }

}

