package com.shenzhen.cachedemo.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 本地缓存
 *
 * @author ZHY
 * 本质就是写入和读取
 */
public class LocalCacheUtils {
	/**
	 * 文件保存的路径
	 */
	public static final String FILE_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/cache/pics";

	/**
	 * 从本地SD卡获取网络图片，key是url的MD5值
	 *
	 * @param url
	 * @return
	 */
	public Bitmap getBitmapFromLocal(String url) {

		try {
			String fileName = MD5Encoder.encode(url);
			File file = new File(FILE_PATH, fileName);
			if (file.exists()) {
				Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
						file));
				return bitmap;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 向本地SD卡写网络图片
	 *
	 * @param url
	 * @param bitmap
	 */
	public void setBitmap2Local(String url, Bitmap bitmap) {
		try {
			// 文件的名字
			String fileName = MD5Encoder.encode(url);
			// 创建文件流，指向该路径，文件名叫做fileName
			File file = new File(FILE_PATH, fileName);
			// file其实是图片，它的父级File是文件夹，判断一下文件夹是否存在，如果不存在，创建文件夹
			File fileParent = file.getParentFile();
			if (!fileParent.exists()) {
				// 文件夹不存在
				fileParent.mkdirs();// 创建文件夹
			}
			// 将图片保存到本地
			bitmap.compress(CompressFormat.JPEG, 100,
					new FileOutputStream(file,false)); //每次重新写入

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//物理层,数据链路层,网络层,传输层,会话层,表示层,应用层,

}
