package com.shenzhen.aidl_test;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface remoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void bindService(View view){
        System.out.println("=====begin bindService");
       // Intent service = new Intent("android.intent.RemoveService");//自定义隐示意图
        Intent service = new Intent(this,RemoveService.class);//自定义隐示意图

        //通过bindService绑定服务
        bindService(service,conn,BIND_AUTO_CREATE);
    }

     ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                remoteService = IMyAidlInterface.Stub.asInterface(service);
                try {
                    int pid = 0;
                    try {
                        pid = remoteService.getPid();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                  //  int currentPid = Process.myPid();
                    int currentPid = 0;
                    System.out.println("currentPid: "+currentPid+", remotePid: "+pid);
                    remoteService.basicTypes(1,12,true,3,3,"aa");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                System.out.println("=====bind Success");
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Toast.makeText(MainActivity.this, "service disConneted unexpected", Toast.LENGTH_SHORT).show();
                remoteService = null;
            }
        };

    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
