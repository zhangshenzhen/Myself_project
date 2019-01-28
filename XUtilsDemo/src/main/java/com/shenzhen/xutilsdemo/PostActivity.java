package com.shenzhen.xutilsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "加载数据" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void btn_Post(View view){
       //360e8ac8d5604593940271bfb8848612
        //4075ffd325fe48c48ed05ee4c0372635
        Map<String, String> map = new HashMap<String, String>();
        map.put("pkregister", "360e8ac8d5604593940271bfb8848612");
        map.put("fileName", "http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_46b6a19ea3394dbaad777ef4c7a6bfca.jpg");
        String url = "http://123.57.232.188:8080/hyb/ws/post/updateUserPosition";
        RequestParams params = new RequestParams(url);
        params.addParameter("pkregister", "360e8ac8d5604593940271bfb8848612");
        params.addParameter("fileName", "http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_46b6a19ea3394dbaad777ef4c7a6bfca.jpg");
        params.addBodyParameter("pkregister", "360e8ac8d5604593940271bfb8848612");
        x.http().request(HttpMethod.PUT, params, new Callback.CommonCallback<String>() {
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
        });

    }

}
