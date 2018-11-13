package com.shenzhen.qqslidingmenue;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class Sliding extends ViewGroup {


    public Sliding(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
     //获取子View
     //测量View
     //设置View

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //设置左右上下
    }
}
