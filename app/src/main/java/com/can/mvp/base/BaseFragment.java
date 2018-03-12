package com.can.mvp.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.can.mvp.base.mvp.BasePresenter;
import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.utils.AnnotationUtils;
import com.can.mvp.utils.FragmentManagerUtil;

/**
 * Created by can on 2018/3/6.
 */

public class BaseFragment<M extends IBaseModel,P extends BasePresenter> extends Fragment implements IBaseView,View.OnClickListener{

    protected P presenter;
    protected M model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentManagerUtil.getInstance().pushFragment(this);
        int contentId = getContentId();
        if (contentId != 0) {
            View view = inflater.inflate(contentId,null);
            AnnotationUtils.initBindView(this,view);
            initView(view);
            bindMVP();
            initData();
            initEvent();
            requestData();
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void bindMVP() {
        model = (M) ModePresenterFactory.getInstance().getMode(getClass(), 0);
        presenter = (P) ModePresenterFactory.getInstance().getPresenter(getClass(), 1);
        presenter.bindView(getBaseViewImpl());
        presenter.bindModel(model);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentManagerUtil.getInstance().removeFragment(this);
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
