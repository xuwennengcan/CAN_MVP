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
import com.can.mvp.views.baseviews.DataStateLayout;
import com.can.mvp.views.baseviews.IRecycleView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by can on 2018/3/6.
 */

public class HomeFragment extends BaseFragment<HomeModel,HomePresenter> implements HomeInterface.View, View.OnClickListener, IRecycleView.OnIRecycleListener {

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getContentId() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onComplete();
            }
        });
        return R.layout.fragment_home;
    }

    private IRecycleView rv;
    private Button btn_loading,btn_no_network,btn_no_data,btn_no_login,btn_hide;

    @Override
    public void initView(View view) {
        super.initView(view);
        rv = view.findViewById(R.id.rv_fragment);
        btn_loading = view.findViewById(R.id.btn_loading);
        btn_no_network = view.findViewById(R.id.btn_network_error);
        btn_no_data = view.findViewById(R.id.btn_no_data);
        btn_no_login = view.findViewById(R.id.btn_no_login);
        btn_hide = view.findViewById(R.id.btn_hide);
        btn_loading.setOnClickListener(this);
        btn_no_network.setOnClickListener(this);
        btn_no_data.setOnClickListener(this);
        btn_no_login.setOnClickListener(this);
        btn_hide.setOnClickListener(this);
        rv.setOnIRecycleListener(this);
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
    public void onClick(View view) {
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
