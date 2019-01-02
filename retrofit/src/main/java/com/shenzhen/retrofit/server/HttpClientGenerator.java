package com.shenzhen.retrofit.server;


import com.shenzhen.retrofit.CommonApplication;

import java.io.File;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by chenkaijian on 17-8-31.
 */

public class HttpClientGenerator {

    private static final String BASE_URL = "http://dota.uuu9.com/";

    private static Retrofit mRetrofit;
    private static HttpClientService mHttpClientService;

    public static HttpClientService getHttpClientService() {
        if (mHttpClientService == null) {
            mHttpClientService = getRetrofit().create(HttpClientService.class);
        }
        return mHttpClientService;
    }

    private static Retrofit getRetrofit() {
        //创建retrofit，把OkHttpClient对象写入
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    //.baseUrl(BASE_URL)
                    .baseUrl("http://123.57.232.188:8080/hyb/")
                    //gson转化器
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                     .client(getHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    private static OkHttpClient getHttpClient() {
        //设置缓存路径
        File httpCacheDirectory = new File(CommonApplication.getInstance().getCacheDir(), "responses");
        //设置缓存 10M
        okhttp3.Cache cache = new okhttp3.Cache(httpCacheDirectory, 10 * 1024 * 1024);
        //创建OkHttpClient，并添加拦截器和缓存代码
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CacheInterceptor(CommonApplication.getInstance()))
               // .addInterceptor(new CacheInterceptor(CommonApplication.getInstance()))
                .cache(cache).build();
        return client;
    }

}
