package com.shenzhen.okhttp_download.utils;

import android.os.RecoverySystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .writeTimeout(5000, TimeUnit.MILLISECONDS)
            .build();

    public static void downLoadFile(String url, final String downloadpath, final String fileName, final ProgessListener listener) {
        //增加拦截器
        OkHttpClient client = okHttpClient.newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) {
                Response response = null;
                try {
                    response = chain.proceed(chain.request());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response.newBuilder().body(new ProgressResponseBody(response.body(), listener)).build();
            }
        }).build();


        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailer(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response != null) {
                    try {
                        InputStream is = response.body().byteStream();
                        FileOutputStream fos = new FileOutputStream(new File(downloadpath + "/" + fileName));
                        int len = 0;
                        byte[] buffer = new byte[2048];
                        while (-1 != (len = is.read(buffer))) {
                            fos.write(buffer, 0, len);
                        }
                        fos.flush();
                        fos.close();
                        is.close();
                        //下载成功
                        listener.onSuecess(new File(downloadpath + "/" + fileName));

                    } catch (IOException e) {
                        e.printStackTrace();
                        //下载异常
                        listener.onFailer(call, e);
                    }

                } else {
                    listener.onError(call,response);
                }
            }
        });
    }

}
