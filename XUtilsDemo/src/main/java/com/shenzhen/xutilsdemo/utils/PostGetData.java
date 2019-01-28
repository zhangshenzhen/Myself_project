package com.shenzhen.xutilsdemo.utils;

import android.util.Log;
import android.widget.Toast;

import com.shenzhen.xutilsdemo.inter_face.ResultDataListener;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Iterator;
import java.util.Map;

public class PostGetData {

    private static String TAG = "数据";

    public static void MethodPost(final int requecode, String url, Map<String, String> map, final ResultDataListener listener) {

        RequestParams params = new RequestParams(url);
        //便利map集合
        for (String key : map.keySet()) {
            String value = map.get(key);
            //设置入参
            params.addParameter(key, value);
        }
        x.http().request(HttpMethod.POST, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i(TAG, "upDataonSuccess : " + result);
                listener.onSuccess(requecode, result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onFailar(requecode, ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /*Get
    * */
    public static void MethodGet(final int requecode, String url, Map<String, String> map, final ResultDataListener listener) {
        RequestParams params = new RequestParams(url);
        //便利map集合
        for (String key : map.keySet()) {
            String value = map.get(key);
            //设置入参
            params.addParameter(key, value);
        }
        x.http().get(params, new Callback.CommonCallback<String>() {

            public void onSuccess(String result) {
                // Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.i("JAVA", "onSuccess result:" + result);
              listener.onSuccess(requecode,result);
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onSuccess(requecode,ex.getMessage());
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {

            }
        });
    }
}
