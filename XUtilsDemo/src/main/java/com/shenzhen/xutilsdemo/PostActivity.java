package com.shenzhen.xutilsdemo;

import android.net.SSLCertificateSocketFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.shenzhen.xutilsdemo.inter_face.ResultDataListener;
import com.shenzhen.xutilsdemo.utils.PostGetData;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

public class PostActivity extends AppCompatActivity implements ResultDataListener {

    private static final String TAG = "加载数据" ;

    /* /*增强for 循环*/
       /* for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println("加载数据 1 "+key + "  " + value);
        }*/

    /*迭代器*/
        /*Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = map.get(key);
            System.out.println("加载数据 2 "+key + " " + value);
        }*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void btn_Post(View view){
       //360e8ac8d5604593940271bfb8848612
        //4075ffd325fe48c48ed05ee4c0372635
        Map<String,String> map = new HashMap<>();
        map.put("pkregister", "4075ffd325fe48c48ed05ee4c0372635");
        map.put("begin", "0");
        map.put("pageLength", 10 + "");
       // map.put("fileName", "http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_46b6a19ea3394dbaad777ef4c7a6bfca.jpg");
        String url = "http://123.57.232.188:8080/hyb/ws/coupon/list";
        PostGetData.MethodPost( 110,url,map,this);

      /*
      * 封装前*/
        /*RequestParams params = new RequestParams(url);
        params.addParameter("pkregister", "4075ffd325fe48c48ed05ee4c0372635");
        params.addParameter("begin", "0");
        params.addParameter("pageLength", 10 + "");
        x.http().request(HttpMethod.POST, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(),result,Toast.LENGTH_LONG).show();
                Log.i(TAG,"upDataonSuccess : " + result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });*/

    }


    public void btn_Get(View view){

       // String url =  "http://123.57.232.188:8080/hyb/ws/get/virtualcount" + "?pkregister=" + "360e8ac8d5604593940271bfb8848612" + "&sourcepk=" + "047561f6bc7141eb8c9fff8626c18fb1";
        String url =  "http://123.57.232.188:8080/hyb/ws/get/virtualcount";
        Map<String,String> map = new HashMap<>();
        map.put("pkregister", "360e8ac8d5604593940271bfb8848612");
        map.put("sourcepk", "047561f6bc7141eb8c9fff8626c18fb1");
        PostGetData.MethodGet(120,url,map,this);
        PostGetData.MethodGet2(120,url,map,this);

        //封装前
        /* 拼接参数 或者入参
        * get 方式一*/
      /*  // String url =  "http://123.57.232.188:8080/hyb/ws/get/virtualcount" + "?pkregister=" + "360e8ac8d5604593940271bfb8848612" + "&sourcepk=" + "047561f6bc7141eb8c9fff8626c18fb1";
        String url =  "http://123.57.232.188:8080/hyb/ws/get/virtualcount";
        RequestParams params = new RequestParams(url);
         //params.setSslSocketFactory(new SSLCertificateSocketFactory(3000));// 设置ssl
        params.addQueryStringParameter("pkregister","360e8ac8d5604593940271bfb8848612");
        params.addQueryStringParameter("sourcepk","047561f6bc7141eb8c9fff8626c18fb1");
       x.http().request(HttpMethod.GET, params, new Callback.CommonCallback<String>() {

            public void onSuccess(String result) {
               // Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("JAVA", "onSuccess result:" + result);

            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {

            }
        });*/
        /*
         * get 方式二*/
        /*
        // String url =  "http://123.57.232.188:8080/hyb/ws/get/virtualcount" + "?pkregister=" + "360e8ac8d5604593940271bfb8848612" + "&sourcepk=" + "047561f6bc7141eb8c9fff8626c18fb1";
        String url =  "http://123.57.232.188:8080/hyb/ws/get/virtualcount";
        RequestParams params = new RequestParams(url);
         //params.setSslSocketFactory(new SSLCertificateSocketFactory(3000));// 设置ssl
        params.addQueryStringParameter("pkregister","360e8ac8d5604593940271bfb8848612");
        params.addQueryStringParameter("sourcepk","047561f6bc7141eb8c9fff8626c18fb1");

        x.http().get(params, new Callback.CommonCallback<String>() {

            public void onSuccess(String result) {
                // Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("JAVA", "onSuccess result:" + result);

            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {

            }
        });*/

    }

    @Override
    public void onSuccess(int requestcode, Object result) {
        Log.i(TAG, requestcode+" Success : " + result);
    }

    @Override
    public void onFailar(int requestcode, Object result) {
        Log.i(TAG, requestcode+" onFailar : " + result);
    }
}
