package com.shenzhen.scrollview_inner_slide.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    private boolean isforbidenSlide;

    public CustomSwipeRefreshLayout(@NonNull Context context) {
        this(context,null);
    }

    public CustomSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return forbidenSlide;
    }
    private boolean forbidenSlide;

    /*正在刷新时禁止滑动*/
    public void  forbidenSlide(boolean isforbidenSlide){
        forbidenSlide =  isforbidenSlide;
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }*/


}
