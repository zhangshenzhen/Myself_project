package com.shenzhen.cachedemo;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.shenzhen.cachedemo.utils.CustomBitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Android锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷--锟斤拷锟界缓锟斤拷-锟斤拷锟截伙拷锟斤拷-锟节存缓锟斤拷
 *
 * @author ZHY
 *
 */
public class MainActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

	private ListView list;
	private Button btn;
	private CustomBitmapUtils utils;

    private static final String BASE_URL = "http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/common6126d29780154ba08641495235d58f94";
    // 初始化一些网络图片


    String[] urls = { BASE_URL + ".jpg", BASE_URL + ".jpg",
			BASE_URL + ".jpg", BASE_URL + ".jpg", BASE_URL + ".jpg",
			BASE_URL + ".jpg", BASE_URL + ".jpg", BASE_URL + ".jpg",
			BASE_URL + ".jpg", BASE_URL + ".jpg", BASE_URL + ".jpg",
			BASE_URL + ".jpg", BASE_URL + ".jpg", BASE_URL + ".jpg",
			BASE_URL + ".jpg", BASE_URL + ".jpg", BASE_URL + ".jpg",
			BASE_URL + ".jpg", BASE_URL + ".jpg", BASE_URL + ".jpg",
			BASE_URL + ".jpg", BASE_URL + ".jpg", BASE_URL + ".jpg",
			BASE_URL + ".jpg", BASE_URL + ".jpg", BASE_URL + ".jpg",
		 };

	String[] permissions = new String[]{Manifest.permission.INTERNET,
			Manifest.permission.CAMERA,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.READ_EXTERNAL_STORAGE,
	              };
	private int REQUEST_CODE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.list);
		btn = (Button) findViewById(R.id.btn_load);
		utils = new CustomBitmapUtils();

		if (Build.VERSION.SDK_INT >= 23) {
			checkAppPermission();
		}

		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyAdapter adapter = new MyAdapter();
				list.setAdapter(adapter);
			}
		});

	}


	private void checkAppPermission() {
		//2、创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
		List<String> mPermissionList = new ArrayList<>();
		mPermissionList.clear();//清空没有通过的权限
		for (int i = 0; i < permissions.length; i++) {
			if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
				mPermissionList.add(permissions[i]);//添加还未授予的权限 } }
			}
		}

		//申请权限
		if (mPermissionList.size() > 0) {
			//有权限没有通过，需要申请
			ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
		}
	}


	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return urls.length;
		}

		@Override
		public String getItem(int position) {
			return urls[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(MainActivity.this,
						R.layout.item_list, null);
				holder = new ViewHolder();
				holder.ivPic = (ImageView) convertView.findViewById(R.id.iv);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			utils.display(holder.ivPic, urls[position]);
			return convertView;
		}

		class ViewHolder {
			ImageView ivPic;
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		boolean hasPermissionDismiss = false;
		//有权限没有通过
		if (REQUEST_CODE == requestCode) {
			for (int i = 0; i < grantResults.length; i++) {
				if (grantResults[i] == -1) {
					hasPermissionDismiss = true;
				}

			}
		}

		/*
		* 打开程序设置界面 资源
		* https://blog.csdn.net/luckrr/article/details/78211465*/
		//如果有权限没有被允许
		if (hasPermissionDismiss) {
			Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
			// showPermissionDialog();
			//可以跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
            String model = android.os.Build.MODEL; // 手机型号
            String release = android.os.Build.VERSION.RELEASE; // android系统版本号
            String brand = Build.BRAND;//手机厂商
			if (TextUtils.equals(brand.toLowerCase(), "redmi") || TextUtils.equals(brand.toLowerCase(), "xiaomi")) {
				gotoMiuiPermission();//小米
			} else if (TextUtils.equals(brand.toLowerCase(), "meizu")) {
				gotoMeizuPermission();
			} else if (TextUtils.equals(brand.toLowerCase(), "huawei") || TextUtils.equals(brand.toLowerCase(), "honor")) {
				gotoHuaweiPermission();
			} else {
				startActivity(getAppDetailSettingIntent());
			}


		}
	}
	/*打开本程序的应用管理 手动设置权限 */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }

	/**
	 * 跳转到miui的权限管理页面
	 */
	private void gotoMiuiPermission() {
		try { // MIUI 8
			Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
			localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
			localIntent.putExtra("extra_pkgname", getPackageName());
			startActivity(localIntent);
		} catch (Exception e) {
			try { // MIUI 5/6/7
				Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
				localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
				localIntent.putExtra("extra_pkgname", getPackageName());
			   startActivity(localIntent);
			} catch (Exception e1) { // 否则跳转到应用详情
				startActivity(getAppDetailSettingIntent());
			}
		}
	}

	/**
	 * 跳转到魅族的权限管理系统
	 */
	private void gotoMeizuPermission() {
		try {
			Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			startActivity(getAppDetailSettingIntent());
		}
	}

	/**
	 * 华为的权限管理页面
	 */
	private void gotoHuaweiPermission() {
		try {
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
			intent.setComponent(comp);
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			startActivity(getAppDetailSettingIntent());
		}

	}

}
