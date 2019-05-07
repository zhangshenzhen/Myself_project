package com.shenzhen.retrofit;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.shenzhen.retrofit.bean.BnnerBean;
import com.shenzhen.retrofit.bean.HuiyuanbiBean;
import com.shenzhen.retrofit.bean.YouHuiquanListBean;
import com.shenzhen.retrofit.server.HttpClientGenerator;
import com.shenzhen.retrofit.utils.DisCacheUtils;
import com.shenzhen.retrofit.utils.LruJsonCache;


import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private LruJsonCache lruJsonCache;
    private String[] permissions = new String[]{Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private List<String> mPermissionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // DiskStringCacheManager.init(new File(getCacheDir(), DiskStringCacheManager.DEFAULT_CACHE_FILE_NAME), DiskStringCacheManager.MAX_CACHE_SIZE);

        //2、创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中


        /*
         * Post 测试 一
         * */

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("pkregister", "360e8ac8d5604593940271bfb8848612");
                map.put("begin", "0");
                map.put("pageLength", 10 + "");//
                HttpClientGenerator.getHttpClientService().getTestInfo(map)
                        .subscribeOn(Schedulers.io())  //必须添加
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<YouHuiquanListBean>() {


                            @Override
                            public void onNext(YouHuiquanListBean responseBody) {
                                String html = new String(responseBody.toString());
                                ObjectMapper om = new ObjectMapper();
                                try {
                                    String json = om.writeValueAsString(responseBody);
                                    Log.d("Main.Retrofit.加载json..", " " + json);
                                    //内存缓存的数据
                                    lruJsonCache = new LruJsonCache();
                                    lruJsonCache.addJsonToMemoryCache("coupon_list", json);
                                    //磁盘缓存
                                    String Name = "RobotName";
                                    String Num = "hs88";
                                    String json2 = "{"+'"'+Name+'"'+":"+'"'+Num+'"'+"}";
                                    DisCacheUtils.setData(MainActivity.this, "coupon_list", json);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onCompleted() {
                                Log.v("ckjc", "getHeros() onCompleted ： ");
                            }

                            @Override
                            public void onError(Throwable e) { //请求失败时使用缓存中的数据
                                if (lruJsonCache != null) {
                                    Log.v("ckjc", "getHeros() 内存缓存中的数据 " + lruJsonCache.getJsonFromMemCache("coupon_list"));
                                } else {

                                }
                                Log.v("ckjc", "getHeros() failure ： " + e.getMessage() + "  : " + DisCacheUtils.getData(MainActivity.this, "coupon_list"));
                                e.printStackTrace();

                            }
                        });
            }
        });



        /*
         * Post 测试 二
         * */
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("type", "13");
                HttpClientGenerator.getHttpClientService().getTestInfo2("hybApplicationConfig/getBanner/", map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<BnnerBean>() {

                            @Override
                            public void onNext(BnnerBean responseBody) {
                                String html = new String(responseBody.toString());
                                ObjectMapper om = new ObjectMapper();
                                try {
                                    String json = om.writeValueAsString(responseBody);
                                    Log.d("Main.Retrofit.加载json..", " " + json);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onCompleted() {
                                Log.v("ckjc", "getHeros() onCompleted ： ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.v("ckjc", "getHeros() failure ： " + e.getMessage());
                                e.printStackTrace();
                            }
                        });


            }
        });

        /*
         * get 测试
         * */
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                //{"resultStatus":0,"msg":"SUCCESS","resultData":{"pkregister":"360e8ac8d5604593940271bfb8848612","amount":0.01,"sourcepk":"047561f6bc7141eb8c9fff8626c18fb1","pkmuser":"a16f983b5fe841b6bffb166908707449","totalCount":"556.90","virtualBalance":"855.11","status":1,"orderExchangeBalance":0}}
                // Wethod.httpGet(this.context, 1001, Config.web.virtualcount + "?pkregister=" + pkregister + "&sourcepk=" + sourcepk, this);

                HttpClientGenerator.getHttpClientService().getHeros3("ws/get/virtualcount" + "?pkregister=" + "360e8ac8d5604593940271bfb8848612" + "&sourcepk=" + "047561f6bc7141eb8c9fff8626c18fb1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<HuiyuanbiBean>() {
                            @Override
                            public void onNext(HuiyuanbiBean responseBody) {
                                //  String  html = new String(responseBody.bytes(), "GB2312");

                                try {
                                    ObjectMapper om = new ObjectMapper();
                                    String json = om.writeValueAsString(responseBody);
                                    Log.d("Main.Retrofit.加载json..", " " + json);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onCompleted() {
                                Log.v("ckjc", "getHeros() onCompleted ： ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.v("ckjc", "getHeros() failure ： " + e.getMessage());
                                e.printStackTrace();
                            }
                        });
            }
        });
        getPermission();
    }

    private void getPermission() {
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限
            }
        }
        if (mPermissionList.size() > 0) {
            if (Build.VERSION.SDK_INT >= 23) {
                //有权限没有通过，需要申请
                ActivityCompat.requestPermissions(this, permissions, 123);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Log.v("ckjc", "getHeros() 5555555555555555555： ");

        if (123 == requestCode) {
            for (int i = 0; i < permissions.length; i++) {
                //

            }
        }
        // 获取到Activity下的Fragment
       /* List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return;
        }
        // 查找在Fragment中onRequestPermissionsResult方法并调用
        for (Fragment fragment : fragments) {

            if (fragment != null && !fragment.isHidden()) {
                // 这里就会调用我们Fragment中的onRequestPermissionsResult方法
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

    }*/
    }

}
