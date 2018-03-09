package com.can.mvp.views.baseviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.utils.NetWorkUtil;
import com.github.ybq.android.spinkit.style.ThreeBounce;

/**
 * 数据状态布局
 */

public class DataStateLayout extends LinearLayout implements View.OnClickListener{

    public static final int STATE_HIDE_LAYOUT = 4;
    public static final int STATE_NETWORK_ERROR = 1;
    public static final int STATE_NETWORK_LOADING = 2;
    public static final int STATE_NODATA = 3;
    public static final int STATE_NODATA_ENABLE_CLICK = 5;
    public static final int STATE_NO_LOGIN = 6;
    private ProgressBar animProgress;
    private boolean clickEnable = true;
    private final Context context;
    public ImageView img;
    private View.OnClickListener listener;
    private int mErrorState;
    private RelativeLayout mLayout;
    private RelativeLayout error_nodata_layout;
    private String strNoDataContent = "";
    private int imgResource = 0;
    private TextView tv;
    private LinearLayout error_load_fail_lin;
    private LinearLayout error_loading_lin;

    public DataStateLayout(Context context) {
        super(context);
        this.context = context;
        this.init();
    }

    public DataStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.init();
    }

    private void init() {
        View view = View.inflate(this.context, R.layout.layout_data_state, null);
        this.img = view.findViewById(R.id.img_error_layout);
        this.tv = view.findViewById(R.id.tv_error_layout);
        this.error_loading_lin = view.findViewById(R.id.error_loading_lin);
        this.error_load_fail_lin = view.findViewById(R.id.error_load_fail_lin);
        this.error_nodata_layout = view.findViewById(R.id.error_nodata_layout);
        this.mLayout = view.findViewById(R.id.pageerrLayout);
        this.animProgress = view.findViewById(R.id.animProgress);
        ThreeBounce load_style = new ThreeBounce();
        this.animProgress.setIndeterminateDrawable(load_style);
        this.setBackgroundColor(-1);
        this.setOnClickListener(this);
        this.img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(DataStateLayout.this.clickEnable && DataStateLayout.this.listener != null) {
                    DataStateLayout.this.listener.onClick(v);
                }

            }
        });
        this.addView(view);
        this.changeErrorLayoutBgMode(this.context);
    }

    public void changeErrorLayoutBgMode(Context context1) {
    }

    public void dismiss() {
        this.mErrorState = STATE_HIDE_LAYOUT;
        this.setVisibility(GONE);
    }

    public int getErrorState() {
        return this.mErrorState;
    }

    public boolean isLoadError() {
        return this.mErrorState == STATE_NETWORK_ERROR;
    }

    public boolean isLoading() {
        return this.mErrorState == STATE_NETWORK_LOADING;
    }

    public void onClick(View v) {
        if(this.clickEnable && this.listener != null) {
            this.listener.onClick(v);
        }

    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.onSkinChanged();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onSkinChanged() {
    }

    public void setDayNight(boolean flag) {
    }

    public void setErrorMessage(String msg) {
        this.tv.setText(msg);
    }

    public void setErrorType(int i) {
        this.setVisibility(VISIBLE);
        switch(i) {
            case STATE_NETWORK_ERROR:
                this.mErrorState = STATE_NETWORK_ERROR;
                this.error_load_fail_lin.setVisibility(VISIBLE);
                this.error_loading_lin.setVisibility(GONE);
                if(NetWorkUtil.isNetWork(this.context)) {
                    this.tv.setText(R.string.error_view_load_error_click_to_refresh);
                    this.img.setBackgroundResource(R.drawable.img_pagefailed_bg);
                } else {
                    this.tv.setText(R.string.error_view_network_error_click_to_refresh);
                    this.img.setBackgroundResource(R.drawable.img_no_net);
                }

                this.img.setVisibility(VISIBLE);
                this.animProgress.setVisibility(GONE);
                this.clickEnable = true;
                break;
            case STATE_NETWORK_LOADING:
                this.error_load_fail_lin.setVisibility(GONE);
                this.error_loading_lin.setVisibility(VISIBLE);
                this.mErrorState = STATE_NETWORK_LOADING;
                this.animProgress.setVisibility(VISIBLE);
                this.img.setVisibility(GONE);
                this.tv.setText(R.string.error_view_loading);
                this.clickEnable = false;
                break;
            case STATE_NODATA:
                this.error_load_fail_lin.setVisibility(VISIBLE);
                this.error_loading_lin.setVisibility(GONE);
                this.mErrorState = STATE_NODATA;
                this.img.setVisibility(VISIBLE);
                this.animProgress.setVisibility(GONE);
                this.setTvNoDataContent();
                this.clickEnable = true;
                break;
            case STATE_HIDE_LAYOUT:
                this.setVisibility(GONE);
                break;
            case STATE_NODATA_ENABLE_CLICK:
                this.error_load_fail_lin.setVisibility(VISIBLE);
                this.error_loading_lin.setVisibility(GONE);
                this.mErrorState = STATE_NODATA_ENABLE_CLICK;
                this.img.setVisibility(VISIBLE);
                this.animProgress.setVisibility(GONE);
                this.setTvNoDataContent();
                this.clickEnable = true;
        }

        try {
            if(this.imgResource != 0) {
                this.img.setBackgroundResource(this.imgResource);
            } else {
                this.img.setBackgroundResource(R.drawable.img_no_data);
            }
        } catch (Exception varSTATE_NODATA) {
            ;
        }

    }

    public void setNoDataContent(String noDataContent) {
        this.strNoDataContent = noDataContent;
    }

    public void setOnLayoutClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setTvNoDataContent() {
        if(!this.strNoDataContent.equals("")) {
            this.tv.setText(this.strNoDataContent);
        } else {
            this.tv.setText(R.string.error_view_no_data);
        }

    }

    public void setNoDataImg(int imId) {
        this.imgResource = imId;
    }

    public void setVisibility(int visibility) {
        if(visibility == GONE) {
            this.mErrorState = STATE_HIDE_LAYOUT;
        }

        super.setVisibility(visibility);
    }

}
