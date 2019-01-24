package com.shenzhen.callback;

import android.content.Context;
import android.os.RemoteCallbackList;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.shenzhen.CallListener;

import java.util.HashMap;
import java.util.Map;

public class GetNetData {

    /*
    * get 可以评价参数请求*/
    public static void LoadDataGet(Context context , final int recode , String url, final CallListener callListener) {
        OkGo.<String>get(url)
                 .tag(context)
                 .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callListener.onSuccess(recode,response.body().toString());
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callListener.onFailed(recode,response);
                    }
                });
    }

    /*
    * post 可以写带参数请求*/
    public static void LoadDataPost(Context context , final int recode , String url, Map<String,String> map, final CallListener callListener) {
          PostRequest<String> post = OkGo.<String>post(url).tag(context);
           post.params(map,true).execute(new StringCallback() {  ////把map设置入参
            @Override
            public void onSuccess(Response<String> response) {
                callListener.onSuccess(recode,response.body().toString());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                callListener.onFailed(recode,response.body().toString());
            }
        });;
        /*或者*/
       /*OkGo.<String>post(url)
                .tag(context)
                .params(map,true) //把map设置入参
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callListener.onSuccess(recode,response.body().toString());
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callListener.onFailed(recode,response.body().toString());
                    }
                });*/
    }


}
