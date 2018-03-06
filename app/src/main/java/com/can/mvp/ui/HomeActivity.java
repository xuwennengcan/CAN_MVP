package com.can.mvp.ui;

import android.widget.TextView;
import android.widget.Toast;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.model.home.HomeModel;
import com.can.mvp.presenter.home.HomeInterface;
import com.can.mvp.presenter.home.HomePresenter;

/**
 * Created by can on 2018/3/2.
 */

public class HomeActivity extends BaseActivity<HomeModel,HomePresenter> implements HomeInterface.View {

    private TextView tv;

    @Override
    public int getContentId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getHomeData();
    }

    @Override
    public IBaseView getBaseViewImpl() {
        return this;
    }

    @Override
    public void initView() {
        super.initView();
        tv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void homeData(String content) {
        tv.setText("nnnn");
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}
