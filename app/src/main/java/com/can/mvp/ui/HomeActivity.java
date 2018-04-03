package com.can.mvp.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.fragment.HomeFragment;
import com.can.mvp.model.home.HomeModel;
import com.can.mvp.presenter.home.HomeInterface;
import com.can.mvp.presenter.home.HomePresenter;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/3/2.
 */

public class HomeActivity extends BaseActivity<HomeModel,HomePresenter> implements HomeInterface.View {

    @BindView(id = R.id.tv)
    private TextView tv;
    private LinearLayout ll;

    @Override
    public int getLayoutId() {
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
        ll = (LinearLayout) findViewById(R.id.ll_activity);
        changeFragment(R.id.ll_activity,HomeFragment.getInstance());
    }


    @Override
    public void homeData(String content) {
        tv.setText(content+"");
    }
}
