package com.shenzhen.aidl_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class RemoveService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int getPid() throws RemoteException {
            //return Process.myPid();
            System.out.println("=====Thread getPid: "+Thread.currentThread().getName());
            return 0;
        }
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            System.out.println("=====Thread basicTypes: "+Thread.currentThread().getName());

        }
    };

}
