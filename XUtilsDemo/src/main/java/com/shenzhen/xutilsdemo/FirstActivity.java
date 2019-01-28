package com.shenzhen.xutilsdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private TextView download_text;
    private ProgressBar download_progess;
    private String TAG = "MainActivity数据";
    //1、首先声明一个数组permissions，将需要的权限都放在里面
    String[] permissions = new String[]{Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private int typle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        findViewById(R.id.btn_Download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 checkAppPermission();
                  typle = 1;
                mothodClassTyple();
            }
        });

        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typle = 2;
                mothodClassTyple();
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typle = 3;
                mothodClassTyple();
            }
        });
    }

    private void mothodClassTyple() {
        if (Build.VERSION.SDK_INT >= 23) {
            checkAppPermission();
        } else {
           startActivity(typle);
        }
    }

    /*
     * 问题
     * 1，https://blog.csdn.net/zuyi532/article/details/9304557
     *
     * */

    private final int mRequestCode = 100;//权限请求码

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
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        } else { //说明权限都已经通过，可以做你想做的事情去 }
            startActivity(typle);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;
        //有权限没有通过
        if (mRequestCode == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }

            }
        }
        //如果有权限没有被允许
        if (hasPermissionDismiss) {
            Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            // showPermissionDialog();
            //跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
        } else {
            //全部权限通过，可以进行下一步操作。。。
            startActivity(typle);
        }
    }


    private void startActivity(int typle){
        switch (typle){
            case 1:
                startActivity(new Intent(FirstActivity.this,DownloadActivity.class));
                break;
            case 2:
                startActivity(new Intent(FirstActivity.this,UpLoadActivity.class));
                break;
            case 3:
            startActivity(new Intent(FirstActivity.this,PostActivity.class));
            break;
        }

    }

}
