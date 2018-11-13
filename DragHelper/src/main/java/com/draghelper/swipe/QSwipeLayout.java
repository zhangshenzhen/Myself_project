package com.draghelper.swipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.draghelper.listener.OnQQDragListener;
import com.draghelper.onSwipeListener;

public class QSwipeLayout extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    private View contentView;
    private View menuview;
    private int dragDistance;
    private int screenWidth;
    private Context context;
    private int dx;
    private int newLeft;
    private int x;
    private int disX;
    private Boolean currentIsOpen = false;
    private int dx1;
    private int releaseBeforgetRight;
    private int y;
    private int disY;
    QSwipeLayout q;
    private onSwipeListener onSwipeListener;

    public void setOnSwipeListener(com.draghelper.onSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }



    public QSwipeLayout(Context context) {
        this(context, null);
    }

    public QSwipeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public QSwipeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        menuview = getChildAt(1);
    }

    /*
     * 初始化DragHelpwe,和屏幕
     * */
    private void init() {
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        /**
         * 获取屏幕宽度
         */WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec); /**
         * 必须获得menuview的宽度。因为滑动结束后，menuview必须距离右边为dragDistance个像素长度才能显示完全，
         * 这里的距离计算，是以menuview左上角的为起点开始计算的。
         */
        dragDistance = menuview.getMeasuredWidth();
    }


    public class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            Log.d("liang", "onViewDragStateChanged: " + state);
        }

        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            return view == contentView || view == menuview;
        }
        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            /**
             * dx,dy描述的是偏移量
             * 向左边滑动后，contentView随着滑动事件向左边移动。但是menuview是显示不出来的（初始状态menuview是在
             * 屏幕之外的）。
             * 所以要在滑动的同时，给menuview设置偏移量使其跟随contentView滑动并且刷新界面。
             *
             */
            if (changedView == contentView) {
                menuview.offsetLeftAndRight(dx);
            } else {
                contentView.offsetLeftAndRight(dx);
            }
            invalidate();

            releaseBeforgetRight = changedView.getRight();//改变后的
            Log.d("liang", left + "releaseBeforgetRight ...dxdx " + releaseBeforgetRight);
        }


        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            int minleftBound = -dragDistance;
            //使其不能向右滑动
            newLeft = Math.min(Math.max(minleftBound, left), 0);
            //  Log.d("liang", "clampViewPositionHorizontalnewLeft: "+ newLeft);

            Log.d("liang", "contentView.getRight()screenWidth: " + screenWidth);
            dx1 = dx1 + dx;
            return newLeft;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
           // Log.d("liang", "getViewVerticalDragRange" + child.getRight());
            return 0;
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {

            onQQDragListener.onMoveDragListener(QSwipeLayout.this);//注册自定义的监听
            Log.d("liang", "onMoveDragListener" + child.getRight());

            return super.getViewHorizontalDragRange(child);
        }

        @Override   //松手后根据侧滑位移确定菜单打开与否
        public void onViewReleased(View releasedChild, float xvel, float yvel) { /**
         * xvel，yvel表示速度
         */
            super.onViewReleased(releasedChild, xvel, yvel);

            if (releaseBeforgetRight > (screenWidth / 2)) {//当前是关闭状态
                if (dx1 > 0) {  //向右（保持关闭）
                    openOrClose(0);
                } else if (dx1 < 0) {//向左
                    if (disX < -100) { //打开
                        openOrClose(-dragDistance);
                    } else {
                        openOrClose(0);
                    }
                }

            } else {//打开状态
                if (dx1 < 0) {  //往左滑保持打开
                    openOrClose(-dragDistance);
                } else if (dx1 > 0) { //往右滑根据移动距离判断
                    if (disX > 100) { //关闭
                        openOrClose(0);
                    } else {
                        openOrClose(-dragDistance);
                    }
                }
            }
            dx1 = 0;//置零
            disX = 0;
            q = QSwipeLayout.this;
           /*if(contentView.getRight()<(screenWidth-80)){
             viewDragHelper.smoothSlideViewTo(contentView, -dragDistance, 0);
             ViewCompat.postInvalidateOnAnimation(QSwipeLayout.this);
             Log.d("liang", ">>>: "+yvel+"速度："+xvel);
            }else{
             viewDragHelper.smoothSlideViewTo(contentView, 0, 0);
             ViewCompat.postInvalidateOnAnimation(QSwipeLayout.this);
          }*/
        }

        /**/
    }
    public void openOrClose(int d) {
     viewDragHelper.smoothSlideViewTo(contentView, d, 0);
     ViewCompat.postInvalidateOnAnimation(QSwipeLayout.this);
    }

    public void dragView(Context context){
        Toast.makeText(context,"拖拽了View 条目",Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (viewDragHelper.shouldInterceptTouchEvent(ev)) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
               // onSwipeListener.onSwipOtherItem(true,this);
                disX = (int) (event.getX() - x);
                disY = (int) (event.getY() - y);
                Log.d("liang", "滑动了onTouchEvent" + disX);
                break;

        }
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }


    }

    public void setOnQQDragListener(OnQQDragListener onQQDragListener) {
            this.onQQDragListener = onQQDragListener;
        }

    private OnQQDragListener onQQDragListener;

}

