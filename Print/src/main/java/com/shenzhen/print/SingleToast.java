package com.shenzhen.print;

import android.content.Context;
import android.widget.Toast;

public class SingleToast {
	private static final Object SYNC_LOCK = new Object();
	private static Toast mToast;
	/** 上下文 */
	private static Context context;

	private static Context getContext() {
		return context;
	}

	private static void setContext(Context context) {
		SingleToast.context = context;
	}

	/**
	 * 获取toast环境，为toast加锁
	 * 
	 * @param context
	 * @return
	 */
	private static void initToastInstance() {

		if (mToast == null) {
			synchronized (SYNC_LOCK) {
				if (mToast == null) {
					mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
				}
			}
		}
	}

	/**
	 * 展示吐司
	 * 
	 * @param context
	 *            环境
	 * @param text
	 *            内容
	 */
	public static void showToast(String text, Context context) {
		setContext(context);
		if (getContext() != null && text != null) {
			initToastInstance();
			mToast.setDuration(Toast.LENGTH_SHORT);
			mToast.setText(text);
			mToast.show();
		}
	}
}
