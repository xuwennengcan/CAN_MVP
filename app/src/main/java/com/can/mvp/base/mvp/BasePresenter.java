package com.can.mvp.base.mvp;

/**
 * Created by can on 2018/3/2.
 */

public class BasePresenter<V,M> implements IBasePresenter {
    protected V view;
    protected M model;

    public void bindView(V baseViewImpl) {
        view = baseViewImpl;
    }

    public void bindModel(M model) {
        this.model = model;
    }

    public void unBindModel() {
        model = null;
    }

    public void unBindView() {
        view = null;
    }
}
