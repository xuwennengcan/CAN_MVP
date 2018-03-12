package com.can.mvp.base.mvp;

import android.view.View;

/**
 * Created by can on 2018/3/2.
 * MVP View
 */

public interface IBaseView {

    /**
     * 返回布局
     * @return
     */
     int getContentId();

    /**
     * 初始化控件
     */
     void initView(View view);

    /**
     * 设置事件
     */
     void onEvent();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 请求网络数据
     */
     void requestData();

    /**
     * 返回view的操作接口
     * @return
     */
     IBaseView getBaseViewImpl();

    /**
     * 点击事件
     */
    void setClick(View view);
}
