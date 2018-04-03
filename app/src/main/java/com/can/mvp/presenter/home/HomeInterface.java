package com.can.mvp.presenter.home;

import com.can.mvp.base.BaseBean;
import com.can.mvp.base.mvp.BasePresenter;
import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.bean.User;

/**
 * Created by can on 2018/3/2.
 * 首页
 */

public interface HomeInterface {



    interface View extends IBaseView{
        void homeData(BaseBean bean);
    }

    interface Model extends IBaseModel{
        User getHomeData();
    }

     abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void setHomeData(BaseBean bean);
    }

}

