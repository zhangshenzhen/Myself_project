package com.shenzhen.print;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;


import com.shenzhen.print.adapter.PrinterIndexAdapter;
import com.shenzhen.print.koolpos.mobiledevdemo.DeviceModel;
import com.shenzhen.print.prin_t.PrepareReceiptInfo;
import com.shenzhen.print.utils.AndroidUtils;

import cn.koolcloud.engine.service.aidl.IPrintCallback;
import cn.koolcloud.engine.service.aidl.IPrinterService;
import cn.koolcloud.engine.service.aidlbean.ApmpRequest;
import cn.koolcloud.engine.service.aidlbean.IMessage;

public class PrinterFragment extends BaseFragment {
    // 打印服务
    private static IPrinterService iPrinterService;
    private ServiceConnection printerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPrinterService = IPrinterService.Stub.asInterface(service);
            try {
                iPrinterService.registerPrintCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPrinterService = null;
        }
    };

    private ProgressDialog pDialog;
    private Button btnTest;
    private Spinner prtSpinner;
    private Spinner prtCount;
    private TextView tvPrinterIndex;
    private CheckBox cbIsLabelPrinter;
    protected static final int printStart = 0;
    protected static final int printEnd = 1;
    protected static final int printError = 2;

    private String[] prtList = { "0", "1", "2", "3", "4" };
    private String[] prtCountData = { "1", "2", "3", "4", "5", "6", "7", "8" };

    private String prtIndex = "0";
    private int printPaperCout = 1;

    protected Handler printHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
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
                    if (printerRspCount >= printPaperCout) {
                        pDialog.dismiss();
                        btnTest.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case printError:
                try {
                    // 0：正常 -1：缺纸 -2：未合盖 -3：卡纸 -4 初始化异常 -100：其他故障
                    // -999：不支持该功能（可以不支持）
                    String errorMsg = "";
                    Log.e("","printer errorCode is " + msg.arg1);
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
                    SingleToast.showToast(errorMsg, myApp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                btnTest.setEnabled(true);
                break;
            }
            super.handleMessage(msg);
        }
    };

    private int printerRspCount = 0;
    private boolean isLastPrintSuccess = true;
    IPrintCallback.Stub callback = new IPrintCallback.Stub() {
        @Override
        public void handleMessage(IMessage message) throws RemoteException {
            int ret = message.what;
           Log.d("","handleMessage ret:" + ret);
            // 0：正常 -1：缺纸 -2：未合盖 -3：卡纸 -4 初始化异常 -100：其他故障
            // -999：不支持该功能（可以不支持）
            printerRspCount++;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(R.layout.fragment_printer, container, false);

        Intent printService = new Intent(IPrinterService.class.getName());
        printService = AndroidUtils.getExplicitIntent(getActivity(), printService);
        getActivity().bindService(printService, printerServiceConnection, Context.BIND_AUTO_CREATE);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setTitle("提示");
        pDialog.setMessage("正在打印小票");

        btnTest = (Button) container.findViewById(R.id.btnTest);
        tvPrinterIndex = (TextView) container.findViewById(R.id.tvPrinterIndex);
        prtSpinner = (Spinner) container.findViewById(R.id.spinnerPrt);
        prtCount = (Spinner) container.findViewById(R.id.spinnerPrtCount);
        cbIsLabelPrinter = (CheckBox) container.findViewById(R.id.cbIsLabelPrinter);
        if (DeviceModel.isKool10()||DeviceModel.isKoolPad()) {
            cbIsLabelPrinter.setVisibility(View.VISIBLE);
        }else {
            cbIsLabelPrinter.setVisibility(View.GONE);
        }
        // ArrayAdapter<String> adapter = new ArrayAdapter(this.getActivity(),
        // android.R.layout.simple_list_item_1, prtList);
        PrinterIndexAdapter adapter = new PrinterIndexAdapter(myApp, prtList);

        prtSpinner.setAdapter(adapter);
        prtSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prtIndex = "" + position;
                PrepareReceiptInfo.setPrtIndex(prtIndex);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (!(DeviceModel.isPOSPAD() || DeviceModel.isKoolPad())) {
            tvPrinterIndex.setVisibility(View.GONE);
            prtSpinner.setVisibility(View.GONE);
        }
        PrinterIndexAdapter countAdapter = new PrinterIndexAdapter(myApp, prtCountData);

        prtCount.setAdapter(countAdapter);
        prtCount.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                printPaperCout = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // 开始打印
        btnTest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btnTest.setEnabled(false);
                printerRspCount=0;
                print();
            }
        });

        return container;
    }

    @Override
    public void onDestroyView() {
        if (iPrinterService != null) {
            try {
                iPrinterService.unRegisterPrintCallback();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            getActivity().unbindService(printerServiceConnection);
        }
        super.onDestroyView();
    }

    private void print() {
        Log.e("","printPaperCout:" + printPaperCout);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg1 = new Message();
                msg1.what = printStart;
                printHandler.sendMessage(msg1);
                try {

                    for (int i = 0; i < printPaperCout; i++) {
                        if (isLastPrintSuccess) {
                            iPrinterService.printPage(new ApmpRequest(PrepareReceiptInfo.getJsonReceipt(getActivity(),
                                    cbIsLabelPrinter.isChecked())));
                            // 多个任务之间必须添加这个睡眠
                            Thread.sleep(500);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
