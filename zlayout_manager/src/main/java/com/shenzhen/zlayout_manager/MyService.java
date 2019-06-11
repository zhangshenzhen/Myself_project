package com.shenzhen.zlayout_manager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    //@androidx.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
             Log.d("MyService","开启了服务");
        return super.onStartCommand(intent, flags, startId);
    }
}
