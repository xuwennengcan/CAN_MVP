package com.can.mvp.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.can.mvp.R;
import com.can.mvp.base.BaseFragment;
import com.can.mvp.model.home.HomeModel;
import com.can.mvp.presenter.home.HomeInterface;
import com.can.mvp.presenter.home.HomePresenter;
import com.can.mvp.views.BindView;
import com.can.mvp.views.baseviews.DataStateLayout;
import com.can.mvp.views.baseviews.IRecycleView;

/**
 * Created by can on 2018/3/6.
 */

public class HomeFragment extends BaseFragment<HomeModel,HomePresenter> implements HomeInterface.View, IRecycleView.OnIRecycleListener {

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @BindView(id = R.id.rv_fragment)
    private IRecycleView rv;
    @BindView(id = R.id.btn_loading,click = true)
    private Button btn_loading;
    @BindView(id = R.id.btn_network_error,click = true)
    private Button btn_no_network;
    @BindView(id = R.id.btn_no_data,click = true)
    private Button btn_no_data;
    @BindView(id = R.id.btn_no_login,click = true)
    private Button btn_no_login;
    @BindView(id = R.id.btn_hide,click = true)
    private Button btn_hide;

    @Override
    public void initView(View view) {
        super.initView(view);
        rv.setOnIRecycleListener(this);
    }

    @Override
    public void setClick(View view) {
        super.setClick(view);
        rv.setEnabled(true);
        switch (view.getId()){
            case R.id.btn_no_data:
                rv.setState(DataStateLayout.STATE_NODATA);
                break;
            case R.id.btn_loading:
                rv.setState(DataStateLayout.STATE_NETWORK_LOADING);
                break;
            case R.id.btn_hide:
                rv.setState(DataStateLayout.STATE_HIDE_LAYOUT);
                break;
            case R.id.btn_no_login:
                rv.setState(DataStateLayout.STATE_NO_LOGIN);
                break;
            case R.id.btn_network_error:
                rv.setState(DataStateLayout.STATE_NETWORK_ERROR);
                break;
        }
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


    @Override
    public void onRefresh() {
      //  rv.refreshComlete();
        handler.sendEmptyMessageDelayed(1,2000);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            rv.refreshComlete();
            return false;
        }
    });

    @Override
    public void onLoadMore() {

    }
}
