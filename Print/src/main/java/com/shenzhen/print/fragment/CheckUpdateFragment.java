package com.shenzhen.print.fragment;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.shenzhen.print.base.BaseFragment;
import com.shenzhen.print.R;
import com.shenzhen.print.utils.AndroidUtils;

import cn.koolcloud.ipos.appstore.service.aidl.IMSCService;
import cn.koolcloud.ipos.appstore.service.aidl.ParcelableApp;

public class CheckUpdateFragment extends BaseFragment implements OnClickListener {
	private AlertDialog.Builder dialog;
	private IMSCService mIService;
	private ServiceConnection connection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			mIService = IMSCService.Stub.asInterface(service);
		}

		public void onServiceDisconnected(ComponentName name) {
			mIService = null;
		}
	};
	private Button btnCheckUpdateSelf, btnCheckUpdatePos,btnCheckUpdateEngine;
	private ParcelableApp mApp;

	// private LinearLayout infoLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		container = (ViewGroup) inflater.inflate(R.layout.fragment_update,
				container, false);
		Intent service = new Intent(IMSCService.class.getName());
		service= AndroidUtils.getExplicitIntent(getActivity(), service);
		getActivity()
				.bindService(service, connection, Context.BIND_AUTO_CREATE);

		btnCheckUpdateSelf = (Button) container
				.findViewById(R.id.btn_check_update_self);
		btnCheckUpdateSelf.setOnClickListener(this);
		btnCheckUpdatePos = (Button) container
				.findViewById(R.id.btn_check_update_pos);
		btnCheckUpdatePos.setOnClickListener(this);
		btnCheckUpdateEngine= (Button) container
				.findViewById(R.id.btn_check_update_engine);
		btnCheckUpdateEngine.setOnClickListener(this);

		return container;
	}

	private void showAppUpdateInfo(final ParcelableApp app) {

		dialog = new AlertDialog.Builder(this.getActivity());
		dialog.setTitle(R.string.str_update_tip);
		// 可根据获取到的服务器端的应用数据，判断是否需要提示升级
		dialog.setMessage(getString(R.string.str_latest_version)
				+ app.getVersion() + "\n" + getString(R.string.str_apk_size)
				+ byte2MByte(app.getSize()) + getString(R.string.str_mb_cap));
		dialog.setPositiveButton(R.string.str_goto_appstore,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						try {
							mIService.openAppDetail(app);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		dialog.setNegativeButton(R.string.str_cancel_string,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
		dialog.show();

	}

	/**
	 * 将以字节（B）为单位的字符型数值转换成以MB为单位的字符型数值
	 * 
	 * @param byteString
	 *            字符型整数
	 * @return 字符型小数
	 */
	public String byte2MByte(String byteString) {
		double i = Double.valueOf(byteString + ".000");
		double j = i / (1024 * 1024);
		String result = String.format("%.2f", j);
		return result;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_check_update_self: {
			new CheckVersionTask().execute(getActivity().getPackageName());
			break;
		}
		case R.id.btn_check_update_pos: {
			/**
			 * cn.koolcloud.pos.mobile2为收银通的包名
			 */
			new CheckVersionTask().execute("cn.koolcloud.pos.mobile2");
			break;
		}
		case R.id.btn_check_update_engine: {
			/**
			 * cn.koolcloud.engine为支付驱动的包名
			 */
			new CheckVersionTask().execute("cn.koolcloud.engine");
			break;
		}
		}
	}

	class CheckVersionTask extends AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mApp != null) {
				showAppUpdateInfo(mApp);
			} else {
				Toast.makeText(
						getActivity(),
						(getResources()
								.getString(R.string.str_is_latest_version)),
						Toast.LENGTH_SHORT).show();
			}
			mApp = null;
			super.onPostExecute(result);

		}

		@Override
		protected Void doInBackground(String... params) {
			checkNewVersion(params[0]);
			return null;
		}

	}

	/**
	 * 检测新版本
	 * 
	 * @param packageName
	 *            应用的包名
	 * @return
	 */
	private ParcelableApp checkNewVersion(String packageName) {
		if (mIService != null) {
			try {
				Log.e("TAG","packageName:" + packageName);
				int versionCode = getLocalVersionCode(packageName);
				Log.e("TAG","versionCode is "+versionCode);
				mApp = mIService.checkUpdate(packageName, versionCode);
			} catch (RemoteException e) {
				// If can not find this package , it will run into this branch
			} catch (Exception e) {
			}
		}else {
			Log.e("TAG","Appstore AIDL is null");
		}
		return mApp;
	}

	@Override
	public void onDestroyView() {
		/**
		 * 注意！！！ 使用完AIDL服务后，请解绑定!
		 */
		if (connection != null)
			Log.e("TAG","CheckUpdateFragment is running into onDestroyView");
			getActivity().unbindService(connection);
		super.onDestroyView();
	}

	/**
	 * 获取本地应用的版本号
	 * 
	 * @param packageName
	 *            应用包名
	 * @return int型版本号
	 */
	private int getLocalVersionCode(String packageName) {
		PackageManager manager;
		PackageInfo info = null;
		manager = this.getActivity().getPackageManager();
		try {
			info = manager.getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();

		}
		return info.versionCode;
	}
}
