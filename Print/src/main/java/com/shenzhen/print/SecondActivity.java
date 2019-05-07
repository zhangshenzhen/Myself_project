package com.shenzhen.print;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.shenzhen.print.prin_t.PrepareReceiptInfo;

import cn.koolcloud.engine.service.aidl.IPrinterService;
import cn.koolcloud.engine.service.aidlbean.ApmpRequest;

public class SecondActivity extends Activity /*implements SingletonPrint.Change */{

    private IPrinterService iPrinterService;
    //private  static ProgressDialog pDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //iPrinterService = SingletonPrint.getiPrinterService(this);
        SingletonPrint.getiPrinterService();
       // SingletonPrint.InitProgressDialog(this);//初始化对话框
       // SingletonPrint.setChange(this);//注册监听

    }

    public void 打印(View view){
        if(!SingletonPrint.initServiceSucess){
            Toast.makeText(this, "打印机服务初始化失败", Toast.LENGTH_SHORT).show();
            return;
        }
        SingletonPrint.InitProgressDialog(this,2);//初始化对话框
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg1 = new Message();
                msg1.what = SingletonPrint.printStart;
                SingletonPrint.printHandler.sendMessage(msg1);
                try {
                    SingletonPrint.iPrinterService.printPage(new ApmpRequest(PrepareReceiptInfo.getJsonReceipt(MyApp.getAppContext(),
                            false)));
                    // 多个任务之间必须添加这个睡眠
                    Thread.sleep(500);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void 打印2(View view){
        PrinterFragment2.print();
    }

   /* @Override
    public void onChange() {
        Log.d("handleMessage","onChange:Activity调用了监听"  );
    }*/
}
