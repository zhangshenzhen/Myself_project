package com.shenzhen.print;

import android.content.Context;

import net.iaf.framework.app.BaseApplication;

public class MyApp extends BaseApplication {
	private static Context context;
	private boolean isConnected = false;

	public static final Context getAppContext() {
		return context;
	};

	@Override
	public void onCreate() {
		context = getApplicationContext();
		super.onCreate();
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

}
