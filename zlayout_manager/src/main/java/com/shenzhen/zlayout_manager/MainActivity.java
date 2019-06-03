package com.shenzhen.zlayout_manager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shenzhen.zlayout_manager.test.LocationMgr;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String TAG = "AppCompatActivity";
    //  private RecyclerView mRv;
    public String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET};
    private int mRequestCode = 100;
    public TextView tv_show_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_show_db = (TextView) findViewById(R.id.tv_show_db);

        String city = "北京市";
        System.out.println("测试数据库bu存在 : "+ city.substring(0,(city.indexOf("市"))));;

    }

    //查询
    public void getdb(View view) {
        String []  srt =  LocationMgr.getInstance().getCityLngAndLat(this,"阜阳");
        Log.d(TAG,srt+"经度："+srt[0]+"  维度: "+srt[1]);

        //测试一
//         tv_show_db.setText("");
//        ContentResolver resolver = getContentResolver();
//        Uri uri = Uri.parse("content://com.shenzhen.db/person");
//        Cursor cursor = resolver.query(uri,new String[]{"_id","name","money"} , null, null, null);
//       // Cursor cursor = resolver.query(uri,new String[]{"_id","name","money"} , "name= ?", new String[]{"shenzhen"}, null);
//        StringBuilder sb  = new StringBuilder();
//
//        while(cursor.moveToNext()){
//            String id = cursor.getString(0);
//            String name = cursor.getString(1);
//            String money = cursor.getString(2);
//
//            String data = "_id :"+ id+"---name:"+name+"----"+"money:"+money;
//            System.out.println("跨进程获取_id :"+ id+"---name:"+name+"----"+"money:"+money);
//            sb.append("  "+data+"\n");
//        }
//        cursor.close();
//        tv_show_db.setText(sb);
        //测试二，
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://cn.com.pateo.cpsp.service.airtrip.IContentProvider/city");
      /*  Cursor cursor = resolver.query(uri, new String[]{"name", "lon", "lat", "pinyin", "jianpin", "pinyin_firstchar"}, null, null, null);
        while (cursor.moveToNext()) {
            String cityName = cursor.getString(0);
            String lng = cursor.getString(1);
            String lat = cursor.getString(2);
            System.out.println(" 测试数据库存在 " + cursor.getString(3) + "  cityName :" + cityName + " lng :" + lng + "----" + "lat :" + lat + "jianping :" + cursor.getString(4) + " ,jianping_firstchar :" + cursor.getString(5));

        }*/
        Cursor cursor2 = resolver.query(uri,new String[]{"name", "lon", "lat", "pinyin", "jianpin", "pinyin_firstchar"}, "name=?", new String[]{"北京"}, null);
        //new String[]{"name", "lon", "lat", "pinyin", "jianpin", "pinyin_firstchar"};//要查询那些字段
        //new String[]{"lon","lat"} ;//只查询经纬度

       while (cursor2.moveToNext()){
           String cityName = cursor2.getString(0);//城市名称
           String lon = cursor2.getString(1);//经度
           String lat = cursor2.getString(2);//维度
           String pinyin =cursor2.getString(3);//拼音
           String jianpin = cursor2.getString(4);//简拼
           String firstchar = cursor2.getString(5);//首字母；
          System.out.println(" 测试数据库存在 城市 : "+cityName+ " , 经度 : " +lon +" , 维度 :"+ lat+" , 拼音 ："+pinyin+" , 简拼 ："+jianpin+" , 首字母 ；"+firstchar);
        }
        cursor2.close();
    }

    //删除
    public void delete(View view) {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.shenzhen.db/person");
        // resolver.delete(uri, "name=?", new String[]{"shenzhen"});
        resolver.delete(uri, "_id=?", new String[]{"20"});
        getdb(tv_show_db);
    }

    //修改
    public void update(View view) {
//        ContentResolver resolver = getContentResolver();
//        Uri uri = Uri.parse("content://com.shenzhen.db/person");
//        ContentValues values = new ContentValues();
//        values.put("name", "tom_");
//        resolver.update(uri, values, "name=?", new String[]{"lisi"});
//        //  resolver.update(uri, values, "_id=?", new String[]{"20"});


        ContentResolver resolver2 = getContentResolver();
        Uri uri2 = Uri.parse("content://cn.com.pateo.cpsp.service.airtrip.IContentProvider/city");
        ContentValues values2 = new ContentValues();
        values2.put("pinyin", "beijing");
        resolver2.update(uri2, values2, "name=?", new String[]{"北京"});

        getdb(tv_show_db);



    }

    public void add(View view) {
        // 得到内容提供者的解析器
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.shenzhen.db/person");
        ContentValues values = new ContentValues();
        // values.put("_id",1);
        values.put("name", "tom_jeary");
        values.put("money", 12345678);
        // 通过内容解析器让内容提供者添加一条数据
        resolver.insert(uri, values);

        getdb(tv_show_db);
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

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
