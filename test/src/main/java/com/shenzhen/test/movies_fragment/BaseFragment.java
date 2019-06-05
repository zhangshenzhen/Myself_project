package com.shenzhen.test.movies_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by 崔龙 on 2017/11/24.
 * <p>
 * tabFragment基类
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected View mRootView;

    public static final int MIN_CLICK_DELAY_TIME = 1000;
    long lastClickTime = 0;
    protected Activity mActivity; // 给子类用的
     public   View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }


    @Override
    public void onClick(View v) {
       onClickEvent(v);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRootView = getView();
        beforeInitView();
        initView();
        afterInitView();
        bindListener();
    }


    /**
     * 在实例化布局之前处理的逻辑
     */
    public abstract void beforeInitView();

    /**
     * 实例化布局文件/组件
     */
    public abstract void initView();

    /**
     * 在实例化之后处理的逻辑
     */
    public abstract void afterInitView();

    /**
     * 绑定监听事件
     */
    public abstract void bindListener();

    /**
     * 点击事件
     **/
    public abstract void onClickEvent(View v);

    /**
     * 利用反射获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
