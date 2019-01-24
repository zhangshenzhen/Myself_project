package com.shenzhen.okhttp_my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.shenzhen.okhttp_my.callback.GetNetData;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  implements CallListener{

    private String TAG = "MainActivity数据";
    private int shouye_Default_coupons = 113;
    private int coupons_list = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void LoadDataPost(View view) {
        //封装后
          Map<String,String> map = new HashMap<>();
          map.put("pkregister", "360e8ac8d5604593940271bfb8848612");
          map.put("begin", "0");
          map.put("pageLength", 10 + "");
        GetNetData.LoadDataPost(this,coupons_list,"http://123.57.232.188:8080/hyb/ws/coupon/list",map,this);
          map.clear();//清空参数
        GetNetData.LoadDataPost(this,shouye_Default_coupons,"http://123.57.232.188:8080/hyb/ws/coupon/user_coupon",map,this);

        //封装前
       /* OkGo.<String>post("http://123.57.232.188:8080/hyb/ws/coupon/list")
                .tag(this)
                .params("pkregister", "360e8ac8d5604593940271bfb8848612")
                .params("begin", "0")
                .params("pageLength", 10 + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess = " + response.body().toString());
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.d(TAG, "onError = " + response.body().toString());
                    }
                });*/
    }

    //"http://123.57.232.188:8080/hyb/ws/get/virtualcount" + "?pkregister=" + "360e8ac8d5604593940271bfb8848612" + "&sourcepk=" + "047561f6bc7141eb8c9fff8626c18fb1"
    /*
     * get 拼接参数请求数据*/
    public void LoadDataGet(View view) {

        GetNetData.LoadDataGet(this, 111,"http://123.57.232.188:8080/hyb/ws/get/virtualcount" + "?pkregister=" + "360e8ac8d5604593940271bfb8848612" + "&sourcepk=" + "047561f6bc7141eb8c9fff8626c18fb1",this);

        /*
        OkGo.<String>get("http://123.57.232.188:8080/hyb/ws/get/virtualcount" + "?pkregister=" + "360e8ac8d5604593940271bfb8848612" + "&sourcepk=" + "047561f6bc7141eb8c9fff8626c18fb1")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, " get onSuccess = " + response.body().toString());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.d(TAG, " get onError = " + response.body().toString());
                    }
                });*/
    }

    @Override
    public void onSuccess(int reqcode, Object result) {
        Log.d(TAG, reqcode+" get onSuccess = " + result);
    }
    @Override
    public void onFailed(int reqcode, Object result) {
        Log.d(TAG, reqcode+ " get onFailed = " + result);
    }
}
