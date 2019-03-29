package com.shenzhen.dialog;

import android.app.Application;

import org.xutils.x;


public class DialogApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); //是否输出debug日志，开启debug会影响性能。

    }
}
