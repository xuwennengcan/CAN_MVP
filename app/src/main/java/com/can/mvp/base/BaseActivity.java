package com.can.mvp.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

public class BaseActivity<M extends IBaseModel,P extends BasePresenter> extends AppCompatActivity implements IBaseView,View.OnClickListener{

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
            initEvent();
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
    public void initEvent() {

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

    @Override
    public void setClick(View view) {
    }

    @Override
    public void onClick(View view) {
        setClick(view);
    }

    /**
     * 更换Fragment
     * @param id
     * @param fragment
     */
    public void changeFragment(int id,Fragment fragment){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }


}
