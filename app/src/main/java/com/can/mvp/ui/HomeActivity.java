package com.can.mvp.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.base.BaseBean;
import com.can.mvp.bean.User;
import com.can.mvp.fragment.HomeFragment;
import com.can.mvp.presenter.home.HomeInterface;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/3/2.
 */

public class HomeActivity extends BaseActivity implements HomeInterface.View {

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

        User user = new User();
        user.setUserAge("18");
        user.setUserId("123456");
        user.setUserPhone("13611414180");
        user.setUserName("多啦B梦");
        user.setUserSex("未知");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        ll = (LinearLayout) findViewById(R.id.ll_activity);
        changeFragment(R.id.ll_activity,HomeFragment.getInstance());
    }


    @Override
    public void homeData(BaseBean bean) {
        User user = (User) bean;
        tv.setText(user.getUserName()+"");
    }
}
