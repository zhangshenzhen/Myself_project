package com.shenzhen.qqsliding;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by shenzhen on 2016/11/3.
 */

public class SlideMenu extends FrameLayout {

    //菜单
    private View menuView;
    //主界面
    private View mainView;
    private ViewDragHelper viewDragHelper;
    private int dradrange;
    private int mainWidth;
    private int menuWith;
    //浮点计算器
   private FloatEvaluator floatEvaluator;
   private ArgbEvaluator argbEvaluator;

    /**声明定义状态常亮
     */
    public  enum DragState{
        Open , Close
    }

    /**当前默认状态
     */
    private  DragState mState = DragState.Close;

    public SlideMenu(Context context) {//构造初始化
        this(context, null);
    }

    public SlideMenu(Context context, AttributeSet attrs) {////构造初始化
        this(context, attrs, 0);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化
    public void init() {
        viewDragHelper = ViewDragHelper.create(this, callback);
        floatEvaluator = new FloatEvaluator();
        argbEvaluator = new ArgbEvaluator();
    }

    /**
     * 初始化menu和main
     * 该方法在 viewgroup将之类 view全部添加进来之后执行,带式在onmeasure之前执行;
     * 一半用来初始化view子控件,
     */
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
    }

    /**
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     * @Override 当omMeasure 方法测量完后执行的方法
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mainWidth = mainView.getMeasuredWidth();
        menuWith = menuView.getMeasuredWidth();
        //移动范围初始化
        dradrange = (int) (getMeasuredWidth() * 0.6f);//0.6f比例
    }

    //@Overrid让viewDragHelper 帮助判断是否应该拦截;
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = viewDragHelper.shouldInterceptTouchEvent(ev);
        return result;
    }

    // viewDragHelper 帮助处理触摸事件
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    //匿名内部类
    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        /**   //尝试捕获view的触摸事件
         * @param child  当前触摸的view
         * @param pointerId  触摸点
         * @return 判断是否需要捕获
         */
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mainView || child == menuView;
        }

        /**@Override
         * 当被捕获的时调用
         * @param// capturedChild被捕获的子view
         * @param activePointerId
         */
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        // @Override//获取水平拖拽范围 然并卵只是判断方向条件之一
        public int getViewHorizontalDragRange(View child) {
            return 100;
        }

        /**
         * 水平方向
         * @param child
         * @param left
         * @param dx
         * @return
         */
        //  @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == mainView) {//限制主界面的滑动
                left = clampLeft(left);
            }/*else if (child ==menuView){
              left =0;
               }*/
            return left;
        }

        /** @Override
         * 当移动的时候调用;
         * @param changedView 移动的view
         * @param //left移动后的left
         * @param //top移动后的top
         * @param dx//变化量
         * @param dy//
         */
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            //当 menu移动的时候让主界面进行伴随移动; 让menu保持不动
            if (changedView == menuView) {
                //让menu保持不动,就是移动后在复原位置
                menuView.layout(0, 0, menuWith, menuView.getBottom());
                int newleft = mainView.getLeft() + dx;//dx向左为负数 右为正;
                newleft = clampLeft(newleft);//调用方法
                mainView.layout(newleft, 0, newleft + mainWidth, mainView.getBottom());
            }


            // 2 增加伴随动画
              float fraction = mainView.getLeft() * 1f / dradrange;// 变化百分百;
               executeAnimation(fraction);//调用

            //3回调监听器
            if (fraction ==1f && listener!= null && mState != DragState.Open){
                 listener.onOpen();
                mState = DragState.Open;
            } else if (fraction == 0 && listener != null && mState != DragState.Close){
                listener.OnClose();
                mState = DragState.Close;
            }else if (listener != null){//直接回调;
                listener.OnDraging(fraction);
            }
        }

        //执行伴随动画 缩放
        private void executeAnimation(float fraction) {
            //fraction 从0->1  图形效果为1->0.8
            // floatEvaluator.evaluate(fraction,1f,0.8f);
            mainView.setScaleX(floatEvaluator.evaluate(fraction, 1f, 0.8f));
            mainView.setScaleY(floatEvaluator.evaluate(fraction, 1f, 0.8f));
            //菜单缩放
            menuView.setScaleX(floatEvaluator.evaluate(fraction, 0.35f, 1f));
            menuView.setScaleY(floatEvaluator.evaluate(fraction, 0.35f, 1f));
            //菜单缩放后会以中心点缩放 索引平移
            menuView.setTranslationX(floatEvaluator.evaluate(fraction, -menuWith / 2, 0));

            //给slindemenu背景亮度增加动画效果;
            if (getBackground() != null) {
                //颜色亮度渐变
               /* int color = (int) argbEvaluator.evaluate(fraction ,Color.BLACK , Color.TRANSPARENT);
                 getBackground().setColorFilter(color , PorterDuff.Mode.SRC_OVER);*/
                getBackground().setColorFilter((Integer) argbEvaluator.evaluate
                        (fraction, Color.BLACK, Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);
            }
        }

        /** @Override
         *
         * @param releasedChild//松开
         * @param xvel//水平速度
         * @param yvel
         */
        //自动归位
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //判断松开时的位置是是否大于范围的一半
            if (mainView.getLeft() > (dradrange / 2)) {
                //右边
                viewDragHelper.smoothSlideViewTo(mainView, dradrange, 0);
                //刷新操作;
                ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
            } else {//左边
                viewDragHelper.smoothSlideViewTo(mainView, 0, 0);
                //刷新操作;
                ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
            }
        }
    };


    //@Override计算滚动
    public void computeScroll() {
        super.computeScroll();
        //判断动画是否结束'
        if (viewDragHelper.continueSettling(true)) {
            //刷新
            ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
        }
    }

    /**
     * 限定范围
     *
     * @param left
     * @return
     */
    private int clampLeft(int left) {
        if (left > dradrange) {
            left = dradrange;
        } else if (left < 0) {
            left = 0;
        }
        return left;
    }

    //成员变量
    private OnSlideListener listener;

    public void setOnSlideListener(OnSlideListener listener) {
        this.listener = listener;
    }

    /**
     * 定义图形动画的接口;
     */
    public interface OnSlideListener {
        void onOpen();

        void OnClose();

        void OnDraging(float fraction);
    }


}
