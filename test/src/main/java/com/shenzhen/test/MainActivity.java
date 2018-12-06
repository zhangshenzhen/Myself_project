package com.shenzhen.test;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.class";
    private ViewPager viewpagr;

    int[] mImageIds = {R.mipmap.youhui, R.mipmap.youhui2};
    private int currentItem;
    private int Layouts[] = {R.layout.myself1, R.layout.myself2};
    private List<View> view_list = new ArrayList<>();
     boolean isSliding = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpagr = findViewById(R.id.viewpagr);
        viewpagr.setAdapter(new ViewPaperAdapter(this, mImageIds));
        currentItem = 0;
       // nextItem();
        // viewpagr.setCurrentItem(currentItem);

         ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
         executor.scheduleWithFixedDelay(runtask, 3000, 3000, TimeUnit.MILLISECONDS);
         viewpagr.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                Log.i("TAG","当前页面Selected : "+i);
                currentItem = i; //一定要写
            }
            @Override
            public void onPageScrollStateChanged(int i) {
               // currentItem++;
                Log.i("TAG","当前页面StateChanged : "+i);
            }
        });


        viewpagr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this, "Down", Toast.LENGTH_SHORT).show();
                        isLoop = false;
                        break;
                    case MotionEvent.ACTION_UP:
                         Toast.makeText(MainActivity.this, "Up", Toast.LENGTH_SHORT).show();
                        isLoop = true;

                        break;
                }
                return  isSliding ; //是否可以左右滑动
            }
        });


    }

    Runnable runtask = new Runnable() {
        @Override
        public void run() {
           if(isLoop &&isScoll){
             handler.sendEmptyMessage(1);
           }
        }
    };
    private boolean isScoll = true;
    private boolean isLoop = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

              if (isLoop &&isScoll) {
                 nextItem();
              }

        }

    };


    private void nextItem() {
        currentItem++;
        viewpagr.setCurrentItem(currentItem);
        System.out.println("这是什么额原因 = "+currentItem);
    }


    public class ViewPaperAdapter extends PagerAdapter {
        private Context mContext;
        private int[] mImageIds;
        private List<View> view_list;


        /**
         * @param mainActivity
         * @param integers
         */
        public ViewPaperAdapter(Context context, int[] mImageIds) {
            this.mContext = context;
            this.mImageIds = mImageIds;
            // this.view_list = view_list;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /**
         * 销毁一个Item
         *
         * @param container ViewPager
         * @param position  要销毁item的位置
         * @param object    instantiateItem方法的返回值
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
             container.removeView((View) object);

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
             /* ImageView image = new ImageView(mContext);
            Glide.with(mContext).load(mImageIds[position]).into(image);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            //限制范围
           // image.setPadding(200, 80, 200, 80);
            ((ViewPager) container).addView(image);*/

            int currentposition = (position + 0) % mImageIds.length; //position+1 为了是第一次为零
            Log.d(TAG, "当前位置是 ：" + currentposition);
            //添加自定义布局
             // View v = LayoutInflater.from(mContext).inflate(R.layout.myself, null);
            View v = View.inflate(MainActivity.this,
                    R.layout.myself, null);// 最后一个传了null
            /*RelativeLayout relyout = v.findViewById(R.id.relyout);
            LinearLayout lineonline = v.findViewById(R.id.line_onLine);//显示优惠券的布局
            TextView tv_offline = v.findViewById(R.id.tv_offLine);
            TextView tv_num = v.findViewById(R.id.tv_num);
            */
            Holder holder = new Holder(v);


            holder.relyout.setBackgroundResource(mImageIds[currentposition]); //根据位置变换图片
            if (currentposition == 0) {
                holder.tv_offline.setVisibility(View.GONE);
                holder.lineonline.setVisibility(View.VISIBLE);
                holder.tv_num.setText(position+"");

            } else {
                holder.tv_offline.setVisibility(View.VISIBLE);
                holder.lineonline.setVisibility(View.GONE);
            }
             holder.img_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                     isScoll = !isScoll;
                     isSliding = !isSliding;

                }
            });

           /* ViewGroup parent = (ViewGroup) relyout.getParent();
            //viewpger 两个页面循环滑动会出现崩溃问题 下面是解决方案
            if (parent != null) {
             parent.removeAllViews();
            }*/
                ((ViewPager) container).addView(v);
                return v;
            }

    }

    static class Holder {
        public View view;
        RelativeLayout relyout;
        LinearLayout lineonline;
        TextView tv_offline;
        TextView tv_num;
        ImageView img_go ;
        public Holder(View view) {
          this.view = view;
          relyout = view.findViewById(R.id.relyout);
          lineonline = view.findViewById(R.id.line_onLine);//显示优惠券的布局
           tv_offline = view.findViewById(R.id.tv_offLine);
             tv_num = view.findViewById(R.id.tv_num);
            img_go = view.findViewById(R.id.img_go);
        }
    }


}

