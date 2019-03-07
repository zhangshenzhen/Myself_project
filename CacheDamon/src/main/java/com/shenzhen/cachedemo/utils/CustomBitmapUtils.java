package com.shenzhen.cachedemo.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.shenzhen.cachedemo.R;


/**
 * 自定义的加载图片的工具类，类似于Xutils中的BitmapUtil，在实际使用中，一般使用BitmapUtil，为了理解三级缓存，
 * 这里模拟BitmapUtil自定义了CustomBitmapUtil
 *
 * @author ZHY
 *
 */
public class CustomBitmapUtils {

	private Bitmap bitmap;

	private NetCacheUtils netCacheUtils;
	private LocalCacheUtils localCacheUtils;
	private MemoryCacheUtils memoryCacheUtils;


	public CustomBitmapUtils() {
		localCacheUtils = new LocalCacheUtils();
		memoryCacheUtils = new MemoryCacheUtils();
		netCacheUtils = new NetCacheUtils(localCacheUtils,memoryCacheUtils);
	}

	/**
	 * 加载图片，将当前URL对应的图片显示到ivPic的控件上
	 *
	 * @param ivPic
	 *            ImageView控件
	 * @param url
	 *            图片的地址
	 */
	public void display(ImageView ivPic, String url) {
		// 设置默认显示的图片
		ivPic.setImageResource(R.drawable.ic_launcher);

		// 1、内存缓存
		bitmap = memoryCacheUtils.getBitmapFromMemory(url);
		if (bitmap != null) {
			ivPic.setImageBitmap(bitmap);
			System.out.println("从内存缓存中加载图片");
			return;
		}

		/*// 1、内存缓存
		bitmap = lruMemoryCach.getJsonFromMemCache(url);
		if (bitmap != null) {
			ivPic.setImageBitmap(bitmap);
			System.out.println("从内存缓存中加载图片");
			return;
		}*/
		// 2、本地磁盘缓存
		bitmap = localCacheUtils.getBitmapFromLocal(url);
		if (bitmap != null) {
			ivPic.setImageBitmap(bitmap);
			System.out.println("从本地SD卡加载的图片");
			memoryCacheUtils.setBitmap2Memory(url, bitmap);// 将图片保存到内存
			return;
		}
		// 3、网络缓存加载网络资源
		netCacheUtils.getBitmapFromNet(ivPic, url);
		/*
		 * 从网络获取图片之后，将图片保存到手机SD卡中，在进行图片展示的时候，优先从SD卡中读取缓存,key是图片的URL的MD5值，
		 * value是保存的图片bitmap
		 */
	}

}
