package com.shenzhen.print;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

import com.shenzhen.print.adapter.PrinterIndexAdapter;
import com.shenzhen.print.base.BaseFragment;
import com.shenzhen.print.koolpos.mobiledevdemo.DeviceModel;
import com.shenzhen.print.prin_t.PrepareReceiptInfo;

import cn.koolcloud.engine.service.aidl.IPrinterService;
import cn.koolcloud.engine.service.aidlbean.ApmpRequest;

public class PrinterFragment2 extends BaseFragment implements SingletonPrint.Change {


    private Button btnTest;
    private Spinner prtSpinner;
    private Spinner prtCount;
    private TextView tvPrinterIndex;
    private CheckBox cbIsLabelPrinter;

    private String[] prtList = {"0", "1", "2", "3", "4"};
    private String[] prtCountData = {"1", "2", "3", "4", "5", "6", "7", "8"};
    private String prtIndex = "0";
    private int printPaperCout = 1;
    private static IPrinterService iPrinterService;
   // public static ProgressDialog pDialog;
   private static Context mgetActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(R.layout.fragment_printer, container, false);
        mgetActivity = getActivity();

        btnTest = (Button) container.findViewById(R.id.btnTest);
        tvPrinterIndex = (TextView) container.findViewById(R.id.tvPrinterIndex);
        prtSpinner = (Spinner) container.findViewById(R.id.spinnerPrt);
        prtCount = (Spinner) container.findViewById(R.id.spinnerPrtCount);
        cbIsLabelPrinter = (CheckBox) container.findViewById(R.id.cbIsLabelPrinter);


       // iPrinterService = SingletonPrint.getiPrinterService(getActivity());
        SingletonPrint.getiPrinterService();
       // SingletonPrint.InitProgressDialog(getActivity());//初始化对话框
        SingletonPrint.setChange(this);//注册监听

     /* pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setTitle("提示");
        pDialog.setMessage("正在打印小票");
        pDialog.setCancelable(true);*/
        if (DeviceModel.isKool10() || DeviceModel.isKoolPad()) {
            cbIsLabelPrinter.setVisibility(View.VISIBLE);
        } else {
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
                print();
                handler.sendEmptyMessageDelayed(1, 10000);
            }
        });

        return container;
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    public static void print() {
        if(!SingletonPrint.initServiceSucess){
            Toast.makeText(mgetActivity, "打印机服务初始化失败", Toast.LENGTH_SHORT).show();
            return;
        }
        SingletonPrint.InitProgressDialog(mgetActivity,1);//初始化对话框
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg1 = new Message();
                msg1.what = SingletonPrint.printStart;
                SingletonPrint.printHandler.sendMessage(msg1);

                try {
                    SingletonPrint.iPrinterService.printPage(new ApmpRequest(PrepareReceiptInfo.getJsonReceipt(MyApp.getAppContext(),
                            false)));
                    Thread.sleep(500);
                    // 多个任务之间必须添加这个睡眠
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

              Intent intent = new Intent(MyApp.getAppContext(), SecondActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              MyApp.getAppContext().startActivity(intent);


        }
    };

    @Override
    public void onChange() {
        btnTest.setEnabled(true);
        Log.d("handleMessage", "onChange:调用了监听");
    }
}
