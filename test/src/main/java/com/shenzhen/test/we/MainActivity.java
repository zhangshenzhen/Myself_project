package com.shenzhen.test.we;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.TimeUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.test.R;

import java.sql.Time;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

	private int[] imageResIds = {
			R.drawable.a,
			R.drawable.b,
			R.drawable.c,
			R.drawable.d,
			R.drawable.e,
	};

	private String[] descs = {
			"巩俐不低俗，我就不能低俗",
			"扑树又回来啦！再唱经典老歌引万人大合唱",
			"揭秘北京电影如何升级",
			"乐视网TV版大派送",
			"热血屌丝的反杀",
	};

	private LinearLayout ll_dots;

	private TextView tv_desc;
	
	/** 显示下一页 */
	private static final int SHOW_NEXT_PAGE = 0;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_NEXT_PAGE:
				int currentItem = viewPager.getCurrentItem();	// 获取ViewPager当前显示的是哪一页
				viewPager.setCurrentItem(currentItem + 1);
				break;
			}
		}
	};
	private boolean isLoop;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPager.setOnPageChangeListener(listener);
        viewPager.setAdapter(new BannerAdapter(imageResIds));
        initDot();
        changeDotAndDesc(0);	// 初始化默认选择第0页
        viewPager.setCurrentItem(viewPager.getAdapter().getCount() / 2);	// 滑到一半的地方
        handler.sendEmptyMessageDelayed(SHOW_NEXT_PAGE, 3000);	// 3秒钟后显示下一页

		//通过监听onTouch事件，设置一个标签isLoop;手指按下时isLoop = false,手指抬起后isLoop = true;
		viewPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				switch (motionEvent.getAction()) {
					case MotionEvent.ACTION_DOWN:
						Toast.makeText(MainActivity.this, "Down", Toast.LENGTH_SHORT).show();
						isLoop = true;
						break;
					case MotionEvent.ACTION_UP:
						Toast.makeText(MainActivity.this, "Up", Toast.LENGTH_SHORT).show();
						isLoop = false;
						showNextPage();//抬起会后需要重新开始
						break;
				}
				return false;
			}
		});

		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
		executor.scheduleWithFixedDelay(queryTask, 300, 3000, TimeUnit.MILLISECONDS);

    }
	 Runnable queryTask = new Runnable() {
		 @Override
		 public void run() {
			if (!isLoop){
			 handler.sendEmptyMessage(SHOW_NEXT_PAGE);
			}
		 }
	 };

	/** 显示下一页 */
	public void showNextPage() {



	}
	
	OnPageChangeListener listener = new OnPageChangeListener() {
		
		/** 当某一页被选择的时候 */
		@Override
		public void onPageSelected(int position) {
			changeDotAndDesc(position);
		}
		
		/** 当页面滑动的时候 */
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
		
		/** 当页面滑动状态发生改变的时候 */
		@Override
		public void onPageScrollStateChanged(int state) { }
	};

	private ViewPager viewPager;

	
	/**
	 * 改变指定位置的描述和点
	 * @param position
	 */
	public void changeDotAndDesc(int position) {
		position = position % ll_dots.getChildCount();
		tv_desc.setText(descs[position]);
		for (int i = 0; i < ll_dots.getChildCount(); i++) {
			// 把position位置的点设置成selected状态，其它位置的dot设置成未选择状态
			ll_dots.getChildAt(i).setSelected(i == position);	
		}
	}

	/** 初始化ViewPager底部的点 */
	private void initDot() {
		for (int i = 0; i < imageResIds.length; i++) {
			int _5dp = dp2px(5);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(_5dp, _5dp);
			params.leftMargin = _5dp;
			View dot = new View(this);
			dot.setLayoutParams(params);
			dot.setBackgroundResource(R.drawable.selector_dot);
			ll_dots.addView(dot);
		}
	}
	
	/**
	 * 把一个dp的值转换为px值
	 * @param dp
	 * @return
	 */
    public int dp2px(int dp) {
    	float density = getResources().getDisplayMetrics().density;	// 获取手机屏幕的密度
    	return (int) (dp * density + 0.5f);	// 加0.5是为了四舍五入
    }
    
    @Override
    protected void onDestroy() {
//    	handler.removeMessages(SHOW_NEXT_PAGE);
    	handler.removeCallbacksAndMessages(null);
    	super.onDestroy();
    }

    
}
