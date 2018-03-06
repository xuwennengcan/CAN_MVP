package com.can.mvp.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.fragment.HomeFragment;
import com.can.mvp.model.home.HomeModel;
import com.can.mvp.presenter.home.HomeInterface;
import com.can.mvp.presenter.home.HomePresenter;

/**
 * Created by can on 2018/3/2.
 */

public class HomeActivity extends BaseActivity<HomeModel,HomePresenter> implements HomeInterface.View {

    private TextView tv;
    private LinearLayout ll;

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
    public void initView(View view) {
        super.initView(view);
        tv = (TextView) findViewById(R.id.tv);
        ll = (LinearLayout) findViewById(R.id.ll_activity);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_activity, HomeFragment.getInstance());
        transaction.commit();
    }


    @Override
    public void homeData(String content) {
        tv.setText("nnnn");
    }
}
