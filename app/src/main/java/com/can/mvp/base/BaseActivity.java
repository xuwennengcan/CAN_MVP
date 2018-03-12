package com.can.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.can.mvp.application.MyApplication;
import com.can.mvp.base.mvp.BasePresenter;
import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.utils.AnnotationUtils;

/**
 * Created by can on 2018/3/2.
 *
 */

public class BaseActivity<M extends IBaseModel,P extends BasePresenter> extends AppCompatActivity implements IBaseView{

    protected P presenter;
    protected M model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        MyApplication.getActivityManager().addActivty(this);
        int contentId = getContentId();
        if (contentId != 0) {
            setContentView(contentId);
            AnnotationUtils.initBindView(this);
            initView(null);
            bindMVP();
            initData();
            requestData();
        }
    }

    private void bindMVP() {
        model = (M) ModePresenterFactory.getInstance().getMode(getClass(), 0);
        presenter = (P) ModePresenterFactory.getInstance().getPresenter(getClass(), 1);
        presenter.bindView(getBaseViewImpl());
        presenter.bindModel(model);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getActivityManager().removeActivity(this);
        if(presenter!=null){
            presenter.unBindModel();
            presenter.unBindView();
        }
    }


    @Override
    public int getContentId() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void onEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {

    }

    @Override
    public IBaseView getBaseViewImpl() {
        return this;
    }
}
