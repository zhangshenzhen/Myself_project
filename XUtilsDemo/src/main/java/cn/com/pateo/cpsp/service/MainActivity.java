package cn.com.pateo.cpsp.service;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback  {
    public String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
           };
    private int mRequestCode = 100;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

        checkAppPermission();
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
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        } else { //说明权限都已经通过，可以做你想做的事情去 }
            iniCopy();
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

        iniCopy();
        }
        //暂时默认同意
    }


    private void iniCopy() {
        String dbPath = "/data/data/" + this.getPackageName()
                + "/databases/" + "city.db";
        final File file = new File(dbPath);

        if (file.exists() && file.length() > 1000) {
            System.out.println("测试数据库存在 "+file.length());
             //  file.delete();
        } else {

            new Thread() {
                @Override
                public void run() {
                    super.run();

                    try {
                        InputStream is = getAssets().open("city.db");
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);

                        }
                        fos.close();
                        is.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("测试数据库bu存在 "+e.getMessage());
                    }
                }
            }.start();
            // 读取数据库
        }

    }
}
