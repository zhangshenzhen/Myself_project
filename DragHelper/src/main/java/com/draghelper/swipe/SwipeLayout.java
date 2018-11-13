package com.draghelper.swipe;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.draghelper.listener.OnScrollListener;


public class SwipeLayout extends LinearLayout {
    private ViewDragHelper dragHelper;
    private View backView;//侧滑菜单
    private View frontView;//内容区域
    private int height;//自定义控件布局高
    private int width;//自定义控件布局宽
    private int range;//侧滑菜单可滑动范围
    private Context context;
    private boolean isDrag;

    public SwipeLayout(Context context){
        this(context, null);
        this.context = context;
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View view, int i) {
             //tryCaptureView：如果返回true表示捕获相关View，
             // 你可以根据第一个参数child决定捕获哪个View。
                return true;
            }

            @Override //竖直方向
            public int clampViewPositionVertical(View child, int top, int dy) {
            /*
            * •clampViewPositionVertical：计算child垂直方向的位置，top表示y轴坐标（相对于ViewGroup），
            * 默认返回0（如果不复写该方法）。
            * 这里，你可以控制垂直方向可移动的范围。*/
            if (top>getHeight()-child.getMeasuredHeight()){
                top = getMeasuredHeight()-child.getMeasuredHeight();
            }else if(top<0) {
                top = 0;
             }

                isDrag = true;  isDrag = true;
                return top;
            }


            @Override //水平方向
            public int clampViewPositionHorizontal(View child, int left, int dx){
             /*•clampViewPositionHorizontal：与clampViewPositionVertical类似，
              * 只不过是控制水平方向的位置。
             * */
             if(left >getWidth() - child.getMeasuredWidth()){//拖拽的view 的左边界 往右移动的最大外置
                 left = getMeasuredWidth() -child.getMeasuredWidth();
             }else if(left<0){
                left = 0;
             }
               isDrag = true;//作为拖动了View的判断条件
                return left;
            }

            @Override   //松手后根据侧滑位移确定菜单打开与否
            public void onViewReleased(View releasedChild, float xvel, float yvel){

                if(isDrag){
                 onScrollListener.onMoveScrollListener(SwipeLayout.this);
                  isDrag = false;
                }
            }

        });


    }

    public void move(Context context){
        Toast.makeText(context,"移动了View",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return dragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }


    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    //测试自定义的监听器;
    private OnScrollListener onScrollListener;
}
