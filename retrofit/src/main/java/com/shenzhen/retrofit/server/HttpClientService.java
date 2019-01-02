package com.shenzhen.retrofit.server;


import com.shenzhen.retrofit.bean.BnnerBean;
import com.shenzhen.retrofit.bean.HuiyuanbiBean;
import com.shenzhen.retrofit.bean.YouHuiquanListBean;

import org.json.JSONObject;

import java.util.HashMap;


import okhttp3.ResponseBody;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenkaijian on 17-9-1.
 */

public interface HttpClientService {

    //方式一 1
   /*
   * get  接口上声明 部分url
   * */
    @GET("/hero/")
    Observable<ResponseBody> getHeros();
    //方式一 2
    /*接口上声明 部分url+携带参数
     * */
    @GET("ws/get/virtualcount"+"?pkregister=" + "360e8ac8d5604593940271bfb8848612" + "&sourcepk=" + "047561f6bc7141eb8c9fff8626c18fb1") //url+参数
    Observable<HuiyuanbiBean> getHeros2();
    //方式一 3
    @GET  // 部分url+携带参数 传值
    Observable<HuiyuanbiBean> getHeros3(@Url String url);

    //方式二 1
    /*
    * post 带参数 + 拼接url
    * */
    // @FormUrlEncoded
    @POST("ws/coupon/list") //拼接地址 传入参数
    Observable<YouHuiquanListBean> getTestInfo(@QueryMap HashMap<String ,String> map);

    //方式二 2
    // @FormUrlEncoded
    @POST  //亦可以当参数传递
    Observable<BnnerBean> getTestInfo2( @Url String url,@QueryMap HashMap<String ,String> map);



}
