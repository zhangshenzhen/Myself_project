package com.shenzhen.xutilsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UpLoadActivity  extends AppCompatActivity {

    private String TAG  = "上传文件";
    private ImageView img;
    //{"resultStatus":0,"msg":"SUCCESS","resultData":{"pkregister":"4075ffd325fe48c48ed05ee4c0372635","phoneno":"15151962093","loginpassword":"720ae1c15dae310d217183fd4a7f19b6","paypassword":"c354d051ab57f2a7c6ddbc734ac1d98c","position":"","nickname":"","cpuid":"355848063319019","extralparam":"","bbs_result":"password error","nativeLoginPassword":"123456q","is_card_sales":0,"card_sale_rate":0}}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        img = findViewById(R.id.img);//头像
    }
    /*
    * 上传文件*/
    public void upLoad(View view){
        String path="/mnt/sdcard/Download/picture.jpg";
        RequestParams params = new RequestParams("http://123.57.232.188:8080/hyb/ws/post/uploadImage");
        params.setMultipart(true);
      // params.addBodyParameter("file",new File(path));
        params.addBodyParameter("image",new File(path));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //{"resultStatus":0,"msg":"SUCCESS","resultData":{"fileName":"http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_a235b3bbcf5d42a1bb17afb23fbfdc0e.jpg"}}
                Log.i(TAG,"onSuccess : "+result);
                try {
                    JSONObject jsonresult = new JSONObject(result);
                    String resultData = jsonresult.optString("resultData");
                    Log.i(TAG,"onSuccess resultData: "+resultData);
                    JSONObject Data = new JSONObject(resultData);
                    String  fileUrl =Data.optString("fileName");
                    Log.i(TAG,"onSuccess fileName : "+fileUrl);
                    upData(fileUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG,"onError : "+ex.getMessage());
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
                Log.i(TAG,"Finished : ");
            }
        });

    }

    private void upData(String fileName) {


    }
}
