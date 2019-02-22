package com.shenzhen.scrollview_inner_slide.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScollChildViewViewpager extends ViewPager {
    public NoScollChildViewViewpager(Context context) {
        this(context,null);
    }

    public NoScollChildViewViewpager(Context context, AttributeSet attrs) {
       super(context, attrs);
    }

    @Override //拦截子View移动
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //分发ViewPager
    /*@Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }*/
}
