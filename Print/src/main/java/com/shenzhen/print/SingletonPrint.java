package com.shenzhen.print;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.print.utils.AndroidUtils;

import cn.koolcloud.engine.service.aidl.IPrintCallback;
import cn.koolcloud.engine.service.aidl.IPrinterService;
import cn.koolcloud.engine.service.aidlbean.IMessage;

public class SingletonPrint {
    // 打印服务
    public static IPrinterService iPrinterService;

    public static IPrinterService getiPrinterService() {

        if (iPrinterService == null) {
            Intent printService = new Intent(IPrinterService.class.getName());
            printService = AndroidUtils.getExplicitIntent(MyApp.getAppContext(), printService);
            MyApp.getAppContext().bindService(printService, printerServiceConnection, Context.BIND_AUTO_CREATE);
        }
            //绑定过程耗时 iPrinterService 可能为空
        return iPrinterService;
    }

    public static void InitProgressDialog(Context context,int index) {
        switch (index) {
            case 1:
                pDialog = pDialog_1;
                if (pDialog == null) {
                    pDialog = new ProgressDialog(context);//content 根据不同的界面使用不同的Context
                    pDialog.setCancelable(false);
                    pDialog.setTitle("提示");
                    pDialog.setMessage("正在打印小票");
                    pDialog.setCancelable(true);
                }
                pDialog_1 =pDialog ;
                break;
            case 2:
                pDialog = pDialog_2;
                if (pDialog == null) {
                    pDialog = new ProgressDialog(context);//content 根据不同的界面使用不同的Context
                    pDialog.setCancelable(false);
                    pDialog.setTitle("提示");
                    pDialog.setMessage("正在打印小票");
                    pDialog.setCancelable(true);
                }
                pDialog_2 =pDialog ;
                break;

        }
        Log.d("pDialog", "pDialog :" + pDialog +"  pDialog_1 :"+pDialog_1 +" pDialog_2 :"+pDialog_2);

      /*pDialog = new ProgressDialog(context);//content 根据不同的界面使用不同的Context
        pDialog.setCancelable(false);
        pDialog.setTitle("提示");
        pDialog.setMessage("正在打印小票");
        pDialog.setCancelable(true);*/
    }


    public static ServiceConnection printerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPrinterService = IPrinterService.Stub.asInterface(service);
            try {
                iPrinterService.registerPrintCallback(callback);
                Log.d("handleMessage", "service:" + service.isBinderAlive());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPrinterService = null;
        }
    };

    public static ProgressDialog pDialog;
    public static ProgressDialog pDialog_1;
    public static ProgressDialog pDialog_2;
    protected static final int printStart = 0;
    protected static final int printEnd = 1;
    protected static final int printError = 2;


    public static IPrintCallback.Stub callback = new IPrintCallback.Stub() {
        @Override
        public void handleMessage(IMessage message) throws RemoteException {
            int ret = message.what;
            Log.d("handleMessage", "handleMessage ret:" + ret);
            // 0：正常 -1：缺纸 -2：未合盖 -3：卡纸 -4 初始化异常 -100：其他故障
            // -999：不支持该功能（可以不支持）
            if (ret == 0) {
                Message msg3 = new Message();
                msg3.what = printEnd;
                printHandler.sendMessage(msg3);
            } else {
                Message msg3 = new Message();
                msg3.what = printError;
                msg3.arg1 = ret;
                printHandler.sendMessage(msg3);
            }
        }
    };


    static Handler printHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Log.d("handleMessage", "handleMessage msg:" + msg.what);
            switch (msg.what) {
                case printStart:
                    try {
                        if (!pDialog.isShowing()) {
                            pDialog.show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case printEnd:
                    try {
                        pDialog.dismiss();
                        mchange.onChange();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case printError:
                    pDialog.dismiss();
                    mchange.onChange();
                    try {
                        // 0：正常 -1：缺纸 -2：未合盖 -3：卡纸 -4 初始化异常 -100：其他故障
                        // -999：不支持该功能（可以不支持）
                        String errorMsg = "";
                        Log.e("", "printer errorCode is " + msg.arg1);
                        switch (msg.arg1) {
                            case -1:
                                errorMsg = "result=-1：缺纸";
                                break;
                            case -2:
                                errorMsg = "result=-2：未合盖";
                                break;
                            case -3:
                                errorMsg = "result=-3：卡纸";
                                break;
                            case -4:
                                errorMsg = "result=-4 初始化异常";
                                break;
                            case -999:
                                errorMsg = "result=-999：不支持该功能";
                                break;
                            default:
                                errorMsg = "result=-100：其他故障";
                                break;
                        }
                        pDialog.dismiss();
                        Toast.makeText(MyApp.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };


    public static void unRigiest() {
        if (iPrinterService != null) {
            try {
                iPrinterService.unRegisterPrintCallback();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            MyApp.getAppContext().unbindService(printerServiceConnection);

            iPrinterService = null;

            Log.i("注销服务", "iPrinterService = " + iPrinterService);
        }
    }


    //接口监听器

    private static Change mchange;

    public static void setChange(Change mchange) {
        SingletonPrint.mchange = mchange;
    }

    public interface Change {
        void onChange();
    }


}
