package com.can.mvp.fragment;

import android.widget.Toast;

import com.can.mvp.R;
import com.can.mvp.base.BaseFragment;
import com.can.mvp.model.home.HomeModel;
import com.can.mvp.presenter.home.HomeInterface;
import com.can.mvp.presenter.home.HomePresenter;

/**
 * Created by can on 2018/3/6.
 */

public class HomeFragment extends BaseFragment<HomeModel,HomePresenter> implements HomeInterface.View{

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getHomeData();
    }

    @Override
    public void homeData(String content) {
        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
    }
}
