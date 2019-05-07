package com.shenzhen.print;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.print.base.BaseActivity;
import com.shenzhen.print.utils.AndroidUtils;
import com.shenzhen.print.utils.JavaUtil;
import com.shenzhen.print.utils.MyLog;
import com.shenzhen.print.wegit.AnyWhereDialog;

import cn.koolcloud.ipos.appstore.service.aidl.IMSCService;
import cn.koolcloud.ipos.appstore.service.aidl.ParcelableApp;

public class CheckVersionUpdate {

    private int screenWidth;
    private int screenHeigh;
    private Activity mContext;

    public CheckVersionUpdate(Activity mContext) {
        this.mContext = mContext;
        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeigh = dm.heightPixels;
    }

    private IMSCService mIService;
    private ParcelableApp app;
    private Toast toast;
    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIService = IMSCService.Stub.asInterface(service);
            new CheckVersionTask().execute("com.ypt.merchant");
            //com.ypt.merchant
            //com.koolpos.mobiledevdemo
            //new CheckVersionTask().execute(getPackageName());

            //com.koolpos.mobiledevdemo
            //checkPosEngine(BaseActivity.this);
        }

        public void onServiceDisconnected(ComponentName name) {
            mIService = null;
        }
    };

    protected void callBindService() {
        Intent serviceIntent = new Intent(IMSCService.class.getName());
        serviceIntent = AndroidUtils.getExplicitIntent(mContext,
                serviceIntent);
        if (serviceIntent == null) {
            handlerInit.sendEmptyMessageDelayed(1,2000);
            // 生产机预装单一应用中心，可不执行此判断(出现此情况原因：应用中心未安装或者存在两个同名Action)
            MyLog.e("请检查本机是否已安装应用中心，或者安装多个不同包名的应用中心");

            return;
        }
        mContext.bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }

    public Handler handlerInit = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            callBindService();
        }
    };

    protected void callUnbindService() {
        if (mIService != null) {
            mContext.unbindService(connection);
        }
        //清除handler
        handlerInit.removeCallbacksAndMessages(null);
    }

    class CheckVersionTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            if (app != null) {
                try {
                    //showAppUpdateDialog(BaseActivity.this, mIService, app);
                    showAppUpdateDialog2(mContext, mIService, app);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(String... params) {
            checkNewVersion(params[0]);
            return null;
        }

    }

    public static void showAppUpdateDialog(final Context context,
                                           final IMSCService service, final ParcelableApp app)
            throws PackageManager.NameNotFoundException {
        final AnyWhereDialog dialog = new AnyWhereDialog(context,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0,
                R.layout.app_update_dialog, R.style.Theme_dialog2,
                Gravity.CENTER, false);
        dialog.setCancelable(true);
        Button peddingUpdateButton = (Button) dialog
                .findViewById(R.id.peddindUpdateButton);
        Button startUpdateButton = (Button) dialog
                .findViewById(R.id.startUpdateButton);
        TextView tipTextView = (TextView) dialog
                .findViewById(R.id.tip2TextView);
        TextView currentVersion = (TextView) dialog
                .findViewById(R.id.update_curr_value_text);
        TextView latestVersion = (TextView) dialog
                .findViewById(R.id.update_new_value_text);
        TextView sizeTextView = (TextView) dialog
                .findViewById(R.id.update_size_value_text);

        tipTextView.setText(context.getResources().getString(
                R.string.update_new_text));
        String packageName = app.getPackageName();
        MyLog.e("==code is "+app.getVersionCode());
        String versionName = context.getPackageManager().getPackageInfo(
                packageName, 0).versionName;
        currentVersion.setText(versionName);
        latestVersion.setText(app.getVersion());
        sizeTextView.setText(JavaUtil.bytes2kb(Long.parseLong(app.getSize())));

        peddingUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });

        startUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    service.openAppDetail(app);
                } catch (RemoteException e) {
                    MyLog.e(e.getMessage());
                }
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    //修改的更新弹框
    //版本更新
    private TextView tv_back;
    private TextView tv_entry;
    private TextView tv_version;
    private PopupWindow popupWindow;
    public /*static*/ void showAppUpdateDialog2(final Activity context,
                                                final IMSCService service, final ParcelableApp app)
            throws PackageManager.NameNotFoundException {
       /* DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeigh = dm.heightPixels;*/

        View view = View.inflate(context, R.layout.layout_popupwindow, null);
        popupWindow = new PopupWindow(view, screenWidth, screenHeigh);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        tv_back = (TextView) view.findViewById(R.id.tv_popup_back);
        tv_entry = (TextView) view.findViewById(R.id.tv_popup_entry);
        tv_version = (TextView) view.findViewById(R.id.tv_new_version);
        tv_version.setText("");
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                popupWindow.dismiss();

            }
        });
        tv_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新
                try {
                    service.openAppDetail(app);
                } catch (RemoteException e) {
                    MyLog.e(e.getMessage());
                }
                popupWindow.dismiss();

            }
        });
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }




    /**
     * 检测新版本
     *
     * @param packageName
     * @return
     */
    private ParcelableApp checkNewVersion(String packageName) {
        if (mIService != null) {
            try {
                MyLog.e("packageName:" + packageName);
                PackageManager pm=mContext.getPackageManager();
                int versionCode =pm.getPackageInfo(packageName, 0).versionCode;
                MyLog.e("versionCode:" + versionCode);
                app = mIService.checkUpdate(packageName, versionCode);
            } catch (Exception e) {
                MyLog.e("CheckException:" + e.getMessage());
            }
        }
        return app;
    }

}
