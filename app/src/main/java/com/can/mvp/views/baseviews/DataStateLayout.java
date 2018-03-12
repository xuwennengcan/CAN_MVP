package com.can.mvp.views.baseviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.can.mvp.R;
import com.github.ybq.android.spinkit.style.ThreeBounce;

/**
 * 数据状态布局
 */

public class DataStateLayout extends LinearLayout{

    public static final int STATE_NETWORK_ERROR = 1;//网络错误
    public static final int STATE_NETWORK_LOADING = 2;//加载中
    public static final int STATE_NODATA = 3;//数据为空
    public static final int STATE_HIDE_LAYOUT = 4;//隐藏
    public static final int STATE_NO_LOGIN = 5;//未登录
    private ProgressBar animProgress;//加载进度框
    private boolean clickEnable = true;//是否可点击
    private final Context context;//上下文
    public ImageView img;//默认图片
    private int mErrorState;//状态
    private String strNoDataContent = "";
    private int imgResource = 0;//图片资源
    private TextView tv;//默认文字
    private LinearLayout error_load_fail_lin;//加载后的布局
    private LinearLayout error_loading_lin;//加载中的布局

    private onDataStateClickListener onDataStateClickListener;//监听器

    //设置监听
    public void setOnDataStateClickListener(onDataStateClickListener listener){
        onDataStateClickListener = listener;
    }

    //点击监听
    public interface onDataStateClickListener{
        void onDateStateClickListener(int state);
    }

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

    //初始化
    private void init() {
        View view = View.inflate(this.context, R.layout.layout_data_state, null);
        this.img = view.findViewById(R.id.img_error_layout);
        this.tv = view.findViewById(R.id.tv_error_layout);
        this.error_loading_lin = view.findViewById(R.id.error_loading_lin);
        this.error_load_fail_lin = view.findViewById(R.id.error_load_fail_lin);
        this.animProgress = view.findViewById(R.id.animProgress);
        //第三方进度条动画
        ThreeBounce load_style = new ThreeBounce();
        this.animProgress.setIndeterminateDrawable(load_style);
        this.setBackgroundColor(-1);
        this.error_load_fail_lin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(DataStateLayout.this.clickEnable && DataStateLayout.this.onDataStateClickListener != null) {
                    onDataStateClickListener.onDateStateClickListener(mErrorState);
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
            case STATE_NETWORK_ERROR://网络错误
                this.mErrorState = STATE_NETWORK_ERROR;
                this.error_load_fail_lin.setVisibility(VISIBLE);
                this.error_loading_lin.setVisibility(GONE);
                this.tv.setText(R.string.error_view_network_error_click_to_refresh);
                this.img.setBackgroundResource(R.drawable.img_no_net);
                this.clickEnable = true;
                break;
            case STATE_NETWORK_LOADING://加载中...
                this.mErrorState = STATE_NETWORK_LOADING;
                this.error_load_fail_lin.setVisibility(GONE);
                this.error_loading_lin.setVisibility(VISIBLE);
                this.clickEnable = false;
                break;
            case STATE_NODATA://加载后-没有数据
                this.mErrorState = STATE_NODATA;
                this.error_load_fail_lin.setVisibility(VISIBLE);
                this.error_loading_lin.setVisibility(GONE);
                this.setTvNoDataContent();
                this.clickEnable = true;
                break;
            case STATE_HIDE_LAYOUT://隐藏
                this.setVisibility(GONE);
                break;
            case STATE_NO_LOGIN://用户未登录
                this.mErrorState = STATE_NO_LOGIN;
                this.error_load_fail_lin.setVisibility(VISIBLE);
                this.error_loading_lin.setVisibility(GONE);
                this.tv.setText(R.string.erro_no_login);
                this.clickEnable = false;
        }

        try {
            if(this.imgResource != 0) {
                this.img.setBackgroundResource(this.imgResource);
            } else {
                this.img.setBackgroundResource(R.drawable.img_no_data);
            }
        } catch (Exception varSTATE_NODATA) {
        }

    }

    public void setNoDataContent(String noDataContent) {
        this.strNoDataContent = noDataContent;
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
