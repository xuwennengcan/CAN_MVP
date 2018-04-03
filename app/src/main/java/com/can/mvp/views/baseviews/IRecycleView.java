package com.can.mvp.views.baseviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.utils.NetWorkUtil;

/**
 * Created by can on 2018/3/9.
 * 自定义下拉刷新-上拉加载更多
 */

public class IRecycleView extends SwipeRefreshLayout{


    protected final String TAG = "mycp";
    private NestedScrollView scrollView;
    public RecyclerView recyclerView;
    private TextView textView;
    private ProgressBar progressBar;
    private LinearLayout llLoadMoreView;
    private LinearLayout llHeader;
    private LinearLayout llFooter;
    private int scrollDistance;
    private boolean isLoadMore = false;
    private boolean isCanLoadMore = true;
    private IRecycleView.OnIRecycleListener refreshListener;
    public DataStateLayout view_loading;

    public IRecycleView(Context context) {
        super(context);
        this.init(context);
    }

    public IRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_irecycleview, this);
        this.scrollView = (NestedScrollView)this.findViewById(R.id.scrollView);
        this.recyclerView = (RecyclerView)this.findViewById(R.id.recycler);
        this.textView = (TextView)this.findViewById(R.id.textView);
        this.progressBar = (ProgressBar)this.findViewById(R.id.progressBar);
        this.llLoadMoreView = (LinearLayout)this.findViewById(R.id.ll_loadmore);
        this.llHeader = (LinearLayout)this.findViewById(R.id.ll_header);
        this.llFooter = (LinearLayout)this.findViewById(R.id.ll_footer);
        this.view_loading = (DataStateLayout) this.findViewById(R.id.view_loading);
        this.initRecyclerView();
        this.initSwipeRefreshLayout();
        this.loadMore(false);
    }

    public void addHeaderView(View view) {
        if(this.llHeader != null && view != null) {
            this.llHeader.addView(view);
        }
    }

    public void removeHeaderView(View view) {
        if(this.llHeader != null && view != null) {
            this.llHeader.removeView(view);
        }

    }

    public void removeHeaderView(int index) {
        if(this.llHeader != null && index >= 0 && index < this.llHeader.getChildCount()) {
            this.llHeader.removeViewAt(index);
        }

    }

    public void removeAllHeaderView() {
        if(this.llHeader != null) {
            this.llHeader.removeAllViews();
        }
    }

    public boolean getHeadView(int index){
        if(this.llHeader!=null&& index >= 0 && index < this.llHeader.getChildCount()){
            return true;
        }
        return false;
    }


    public void addFooterView(View view) {
        if(this.llFooter != null && view != null) {
            this.llFooter.addView(view);
        }
    }


    public boolean getFooterView(int index){
        if(this.llFooter!=null&& index >= 0 && index < this.llFooter.getChildCount()){
            return true;
        }
        return false;
    }


    public void removeFooterView(View view) {
        if(this.llFooter != null && view != null) {
            this.llFooter.removeView(view);
        }
    }

    public void removeFooterView(int index) {
        if(this.llFooter != null && index >= 0 && index < this.llFooter.getChildCount()) {
            this.llFooter.removeViewAt(index);
        }

    }

    public void removeAllFooterView() {
        if(this.llFooter != null) {
            this.llFooter.removeAllViews();
        }
    }


    public void refreshComlete() {
        if(NetWorkUtil.isNetWork(getContext())){
            if(recyclerView.getChildCount()==0){
                setState(DataStateLayout.STATE_NODATA);
            }else {
                setState(DataStateLayout.STATE_HIDE_LAYOUT);
            }
            llHeader.setVisibility(VISIBLE);
        }else{
            if(recyclerView.getChildCount()==0) {
                setState(DataStateLayout.STATE_NETWORK_ERROR);
                llHeader.setVisibility(GONE);
            }else {
                setState(DataStateLayout.STATE_HIDE_LAYOUT);
            }
        }
        if(this.isRefreshing()) {
            this.setRefreshing(false);
        } else {
            this.loadMore(false);
        }
    }

    public void loadMore(boolean loading) {
        if(loading) {
            this.progressBar.setVisibility(VISIBLE);
            this.textView.setVisibility(VISIBLE);
            this.textView.setText("加载中...");
            this.isCanLoadMore = true;
            this.isLoadMore = true;
        } else {
            this.isLoadMore = false;
        }
    }

    public void loadMoreNoData() {
        this.loadMoreNoData("");
    }

    public void loadMoreNoData(String text) {
        this.progressBar.setVisibility(GONE);
        this.textView.setText(TextUtils.isEmpty(text)?"已经全部加载完":text);
        this.isCanLoadMore = false;
        requestLayout();
    }

    public void setIsCanLoadMore(){
        this.progressBar.setVisibility(VISIBLE);
        this.textView.setVisibility(VISIBLE);
        this.textView.setText("加载中...");
        this.isCanLoadMore = true;
        this.isLoadMore = false;
    }


    public void setCanLoadMore(boolean flag){
        if(!isEdit)
            setIsCanLoadMore();
        if(flag)
            this.llLoadMoreView.setVisibility(VISIBLE);
        else
            this.llLoadMoreView.setVisibility(GONE);
    }

    private void initSwipeRefreshLayout() {
        this.setEnabled(false);
        super.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if(IRecycleView.this.refreshListener != null) {
                    IRecycleView.this.refreshListener.onRefresh();
                }
            }
        });
        this.setColorSchemeResources(new int[]{R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark});
    }

    private int getColorAccent() {
        int accentColor = 16750592;
        int[] attrsArray = new int[]{R.attr.colorAccent};
        TypedArray typedArray = this.getContext().obtainStyledAttributes(attrsArray);

        try {
            accentColor = typedArray.getColor(0, accentColor);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        typedArray.recycle();
        return accentColor;
    }

    public void setIsEdit(boolean flag){
        isEdit = flag;

    }

    private boolean isEdit = false ; //是否在编辑中
    private void initRecyclerView() {
        this.recyclerView.setNestedScrollingEnabled(false);
        this.scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(isEdit)
                    return;
                Log.d(IRecycleView.this.TAG, "scrollY = " + scrollY + ",oldScrollY = " + oldScrollY);
                IRecycleView.this.scrollDistance = scrollY;
                int maxScrollAmount = v.getChildAt(0).getMeasuredHeight() - v.getHeight();
                Log.d(IRecycleView.this.TAG, "scrollDistance = " + IRecycleView.this.scrollDistance + ",maxScrollAmount = " + maxScrollAmount);
                if(IRecycleView.this.scrollDistance >= maxScrollAmount  && !IRecycleView.this.isRefreshing() && !IRecycleView.this.isLoadMore && IRecycleView.this.isCanLoadMore) {
                    Log.d(IRecycleView.this.TAG, "加载中...");
                    if(IRecycleView.this.refreshListener != null) {
                        IRecycleView.this.loadMore(true);
                        IRecycleView.this.refreshListener.onLoadMore();
                    }
                }else if(!isCanLoadMore){
                    textView.setText("已经全部加载完");
                    textView.setVisibility(VISIBLE);
                }
            }
        });
        view_loading.setOnDataStateClickListener(new DataStateLayout.onDataStateClickListener() {
            @Override
            public void onDateStateClickListener(int state) {
                if(state==DataStateLayout.STATE_NO_LOGIN){//未登录
                    // TODO: 2018/3/12 去登录
                }else if(state==DataStateLayout.STATE_NETWORK_ERROR){//网络错误
                    setState(DataStateLayout.STATE_NETWORK_LOADING);
                    if(refreshListener != null) {
                        refreshListener.onRefresh();
                    }
                }else if(state==DataStateLayout.STATE_NODATA){//数据为空
                    setState(DataStateLayout.STATE_NETWORK_LOADING);
                    if(refreshListener != null) {
                        refreshListener.onRefresh();
                    }
                }
            }
        });
    }

    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
        if(this.refreshListener != null && refreshing) {
            this.refreshListener.onRefresh();
        }
    }

    /**
     * 设置数据和网络及登录状态
     * @param state
     */
    public void setState(int state){
        view_loading.setState(state);
        if(state==DataStateLayout.STATE_NO_LOGIN||state==DataStateLayout.STATE_NETWORK_LOADING)
            this.setEnabled(false);
        else
            setEnabled(true);
    }

    public void autoRefresh() {
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                IRecycleView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                IRecycleView.this.setRefreshing(true);
            }
        });
    }

    public void setOnIRecycleListener(IRecycleView.OnIRecycleListener listener) {
        this.refreshListener = listener;
    }

    public RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public RecyclerView verticalLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        manager.setOrientation(1);
        this.getRecyclerView().setLayoutManager(manager);
        return this.getRecyclerView();
    }

    public RecyclerView horizontalLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        manager.setOrientation(0);
        this.getRecyclerView().setLayoutManager(manager);
        return this.getRecyclerView();
    }

    public RecyclerView gridLayoutManager(int spanCount) {
        GridLayoutManager manager = new GridLayoutManager(this.getContext(), spanCount);
        this.getRecyclerView().setLayoutManager(manager);
        return this.getRecyclerView();
    }

    public RecyclerView verticalStaggeredLayoutManager(int spanCount) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spanCount, 1);
        this.getRecyclerView().setLayoutManager(manager);
        return this.getRecyclerView();
    }

    public RecyclerView horizontalStaggeredLayoutManager(int spanCount) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spanCount, 0);
        this.getRecyclerView().setLayoutManager(manager);
        return this.getRecyclerView();
    }

    public RecyclerView setAdapter(RecyclerView.Adapter adapter) {
        this.getRecyclerView().setAdapter(adapter);
        return this.getRecyclerView();
    }

    public NestedScrollView getScrollView() {
        return this.scrollView;
    }

    public interface OnIRecycleListener {
        void onRefresh();

        void onLoadMore();
    }
}
