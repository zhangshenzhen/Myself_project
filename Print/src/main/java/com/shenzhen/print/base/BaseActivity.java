package com.shenzhen.print.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.shenzhen.print.MyApp;

import com.shenzhen.print.R;
import com.shenzhen.print.utils.AndroidUtils;
import com.shenzhen.print.utils.JavaUtil;
import com.shenzhen.print.utils.MyLog;
import com.shenzhen.print.wegit.AnyWhereDialog;

import cn.koolcloud.ipos.appstore.service.aidl.IMSCService;
import cn.koolcloud.ipos.appstore.service.aidl.ParcelableApp;

/**
 * @author chice.xu
 * @date 2016-3-30
 */
public class BaseActivity extends FragmentActivity {
	//protected MyApp myApp;
	private IMSCService mIService;
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
	private ParcelableApp app;
	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//myApp = (MyApp) getApplication();

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	protected void callBindService() {
		Intent serviceIntent = new Intent(IMSCService.class.getName());
		serviceIntent = AndroidUtils.getExplicitIntent(BaseActivity.this,
				serviceIntent);
		if (serviceIntent == null) {
			// 生产机预装单一应用中心，可不执行此判断(出现此情况原因：应用中心未安装或者存在两个同名Action)
			MyLog.e("请检查本机是否已安装应用中心，或者安装多个不同包名的应用中心");
			toast.show();
			finish();
			return;
		}
		bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
	}

	protected void callUnbindService() {
		if (mIService != null) {
			this.unbindService(connection);
		}
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
					showAppUpdateDialog2(BaseActivity.this, mIService, app);
				} catch (NameNotFoundException e) {
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
			throws NameNotFoundException {
		final AnyWhereDialog dialog = new AnyWhereDialog(context,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0, 0,
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

		peddingUpdateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});

		startUpdateButton.setOnClickListener(new OnClickListener() {
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
	private int screenWidth;
	private int screenHeigh;
	private TextView tv_back;
	private TextView tv_entry;
	private TextView tv_version;
	private PopupWindow popupWindow;
	public /*static*/ void showAppUpdateDialog2(final Context context,
										   final IMSCService service, final ParcelableApp app)
			throws NameNotFoundException {
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeigh = dm.heightPixels;

		View view = View.inflate(this, R.layout.layout_popupwindow, null);
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
				PackageManager pm=getPackageManager();
				int versionCode =pm.getPackageInfo(packageName, 0).versionCode;
				MyLog.e("versionCode:" + versionCode);
				app = mIService.checkUpdate(packageName, versionCode);
			} catch (Exception e) {
				MyLog.e("CheckException:" + e.getMessage());
			}
		}
		return app;
	}

	public void addLayoutFragment(int layout, Fragment fragment, boolean add) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(layout, fragment);
		if (add) {
			ft.addToBackStack(null);
		}
		ft.commitAllowingStateLoss();
	}


	/**
	 * 以dialog形式提示用户相关信息
	 *
	 * @param title
	 *            diaolog标题
	 * @param msg
	 *            dialog内容
	 */
	protected void showMsgDialog(String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();

					}
				});
		builder.setCancelable(false);
		builder.create().show();

	}
}
