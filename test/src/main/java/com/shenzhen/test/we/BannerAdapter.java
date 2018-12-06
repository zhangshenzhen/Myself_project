package com.shenzhen.test.we;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BannerAdapter extends PagerAdapter {

	private int[] imageResIds;
	
	public BannerAdapter(int[] imageResIds) {
		this.imageResIds = imageResIds;
	}

	/** 返回有多少页 */
	@Override
	public int getCount() {
		return imageResIds.length * 10000 * 100;	// 返回一个这么大的值是为了实现无限循环
	}

	/** 用于判断ViewPager是否可以复用 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;	// 固定写法
	}
	
	/**
	 * 跟ListView中的Adpater中的getView方法类似，用于创建一个Item
	 * @param container ViewPager
	 * @param position 要生成item的位置
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = new ImageView(container.getContext());
		position = position % imageResIds.length;
		imageView.setBackgroundResource(imageResIds[position]);
		container.addView(imageView);	// 把一个item添加到ViewPager容器
		return imageView;
	}
	
	/**
	 * 销毁一个Item
	 * @param container ViewPager
	 * @param position 要销毁item的位置
	 * @param object instantiateItem方法的返回值
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((ImageView) object);
	}

}
