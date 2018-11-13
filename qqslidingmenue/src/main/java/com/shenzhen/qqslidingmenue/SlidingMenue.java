package com.shenzhen.qqslidingmenue;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.view.ViewGroup;

/**
 * Created by shenzhen on 2016/11/2.
 */

public class SlidingMenue extends ViewGroup {
    private static final String TAG = "SlidingMenue.class";
    //菜单宽
    private int  menuWidth;
    private  View menu;
    private  View main;
    private int down;
    private int currentX;
    private   int destx;
    /** 这个类专门用于模拟滚动的数值 */
    private Scroller  scroller;
    public SlidingMenue(Context context , AttributeSet arttrs){
        super(context ,arttrs);
        scroller = new Scroller(context);
    }

    //@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//测量容器自己的宽高;

         menu = getChildAt(0);//获取菜单容器
         main = getChildAt(1);//获取界面容器

        //测量菜单
         menuWidth = menu.getLayoutParams().width;//获取菜单的宽
         menu.measure(menuWidth, heightMeasureSpec);

        //测量主界面;
        main.measure(widthMeasureSpec,heightMeasureSpec);
   }

    // @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int menuleft = -menuWidth ;
        int menuTop = 0;
        int menuRight = 0 ;
        int menuBottom = b - t ; //高等于低减定
        menu.layout(menuleft,menuTop,menuRight,menuBottom);

        //对主界面进行排版
        int mainleft = 0 ;
        int mainTop = 0;
        int mainRight = r - l ;//主界面的right坐标容器;
        int mainBottom = b - t ; //高等于低减定
        main.layout(mainleft,mainTop,mainRight,mainBottom);

     }

    /**
     * 让界面滚动的位置.水平为x 竖直为零;
     * @param x
     */
      public  void  scrollTo(int x){//
          //系统规定 正数左移 负数右移,包装后 取反.正右 负数左;
          super.scrollTo(-x,0);
      }//
             //当前移动的位置
      public int getMyScrollX(){
         return  - super.getScrollX();
      }
    //@Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                down = (int) event.getX();//按下位置
                Log.d(TAG,"diatance onDown= "+ destx);
                break;
            case MotionEvent.ACTION_MOVE:
                //手指水平移动的距离
             int fingerMoveDistence = (int) (event.getX() -down);
                destx = currentX  + fingerMoveDistence;  //界面的移动目标位置;

                //限定移动的范围
                if(destx < 0){
                    destx = 0;
                } else if(destx > menuWidth){
                    destx = menuWidth;
                }
                Log.d(TAG,"diatance onTouchEvent= "+ destx);
                scrollTo(destx);
                break;
            case MotionEvent.ACTION_UP:
                  currentX = getMyScrollX();//记录松开时的当前位置
                   //设置松开自动归位
                  if( getMyScrollX() < (menuWidth/2)){
                    // scrollTo(0);
                      startScroll(0);  //缓慢归位
                    //  currentX = 0;
                  }else {
                     // scrollTo(menuWidth);
                      startScroll(menuWidth);//缓慢归位
                     // currentX = menuWidth;
                  }

                Log.d(TAG,"diatance onUp= "+ destx);
                break;
        }
        return true;
    }
      int count ;
    private int downY;
    private int downX;

    /**
     * 以动画的方式滚动到指定的位置
     *
     * @param destX 要滑动到哪里（目标位置）
     */
    private void startScroll(int destX) {
        currentX = destX;				// 界面当前的位置
        int startX = getMyScrollX();	// 指定从哪里开始滑动
        int distatnceX = destX - startX;// 要滑动的距离
        int duration = 800;

      scroller.startScroll(startX, 0, distatnceX, 0, duration);
        invalidate();
        // 刷新界面，内部会调用ViewGroup的draw方法，draw方法调用dispatchDraw方法-->
        // drawChild-->子View的draw方法-->computeScroll()
    }

    //@Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {	// 如果数值没有模拟完，则返回true
            int currX = scroller.getCurrX();	// 模拟出来的滑动值
            scrollTo(currX);
            invalidate();
            count++;
        }
        // System.out.println("count = " + count);
    }
    //点击调用的方法
      public  void toggle(){
          if(getMyScrollX()>0){//状态为出来.需要隐藏'
               startScroll(0);
          }else{
              startScroll(menuWidth);
          }
      }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceX = Math.abs((int) (ev.getX() - downX));
                int distanceY = Math.abs((int) (ev.getY() - downY));

                Log.d(TAG,"diatance onIntercept = "+ distanceX);
                if (distanceX > distanceY) {
                    // 如果水平移动距离比垂直移动距离大，则认为是水平移动，把事件拦截，不让ScrollView使用
                    return true;	// true代表拦截事件
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);

    }
}
