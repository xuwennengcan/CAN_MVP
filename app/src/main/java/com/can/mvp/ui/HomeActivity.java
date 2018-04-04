package com.can.mvp.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.bean.User;
import com.can.mvp.fragment.HomeFragment;
import com.can.mvp.mvps.interfaces.HomeInterface;
import com.can.mvp.mvps.models.HomeModel;
import com.can.mvp.mvps.presenters.HomePresenter;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/3/2.
 */

public class HomeActivity extends BaseActivity implements HomeInterface.View {

    @BindView(id = R.id.tv)
    private TextView tv;
    private LinearLayout ll;

    private HomePresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData() {
        super.initData();

        User user = new User();
        user.setUserAge("18");
        user.setUserId("123456");
        user.setUserPhone("13611414180");
        user.setUserName("多啦B梦");
        user.setUserSex("未知");

        presenter = new HomePresenter(this,new HomeModel());

        presenter.getUser(user.getUserName(),user.getUserId());
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        ll = (LinearLayout) findViewById(R.id.ll_activity);
        changeFragment(R.id.ll_activity,HomeFragment.getInstance());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setUser(User user) {
        tv.setText(user.getUserName());
    }

    @Override
    public void error() {

    }
}
