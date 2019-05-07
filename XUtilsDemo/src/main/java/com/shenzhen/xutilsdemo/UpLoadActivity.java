package com.shenzhen.xutilsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UpLoadActivity extends AppCompatActivity {

    private String TAG = "上传文件";
    private ImageView img;
    private String fileUrl;
    //{"resultStatus":0,"msg":"SUCCESS","resultData":{"pkregister":"4075ffd325fe48c48ed05ee4c0372635","phoneno":"15151962093","loginpassword":"720ae1c15dae310d217183fd4a7f19b6","paypassword":"c354d051ab57f2a7c6ddbc734ac1d98c","position":"","nickname":"","cpuid":"355848063319019","extralparam":"","bbs_result":"password error","nativeLoginPassword":"123456q","is_card_sales":0,"card_sale_rate":0}}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        img = findViewById(R.id.img);//头像
    }

    /*
     * 上传文件*/
    public void upLoad(View view) {
        String path = "/mnt/sdcard/Download/picture.jpg";
        RequestParams params = new RequestParams("http://123.57.232.188:8080/hyb/ws/post/uploadImage");
        params.setMultipart(true);
        // params.addBodyParameter("file",new File(path));
        params.addBodyParameter("image", new File(path));
        params.addParameter("pkregister", "360e8ac8d5604593940271bfb8848612");
       // params.put("fileName", backUrl);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //{"resultStatus":0,"msg":"SUCCESS","resultData":{"fileName":"http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_a235b3bbcf5d42a1bb17afb23fbfdc0e.jpg"}}
                Log.i(TAG, "onSuccess : " + result);
                try {
                    JSONObject jsonresult = new JSONObject(result);
                    String resultData = jsonresult.optString("resultData");
                    Log.i(TAG, "onSuccess resultData: " + resultData);
                    JSONObject Data = new JSONObject(resultData);
                    fileUrl = Data.optString("fileName");
                    Log.i(TAG, "onSuccess fileName : " + fileUrl);
                    upData(fileUrl);
                    ImageRadius(fileUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG, "onError : " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                Log.i(TAG, "Finished : ");
            }
        });

    }

    public void ImageRadius(String fileUrl) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(120, 120)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setUseMemCache(true)
                .setRadius(10) // 设置成圆形图片
                .setFadeIn(true) //淡入效果
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .build();
        //http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_aa677667a4c84fab9ef8bc26e2673055.jpg
        //http://pic6.nipic.com/20100418/4581549_084724004690_2.jpg
        x.image().bind(img, fileUrl, imageOptions);

    }

    /*
     * 上传json 数据*/

    public void upLoad_Json(View view) {

        //String url = "http://123.57.232.188:8080/hyb/ws/coupon/list";
        String url = "http://123.57.232.188:8080/hyb/ws/coupon/user_coupon";
        RequestParams params = new RequestParams(url);
        params.addParameter("pkregister", "360e8ac8d5604593940271bfb8848612");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "Success : " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG, "onError : " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }

    private void upData(String fileName) {
  //http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_e714393878e842f09546b43773f13bed

    }
}
