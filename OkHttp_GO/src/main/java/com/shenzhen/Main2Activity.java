package com.shenzhen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import com.shenzhen.callback.GetNetData;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements CallListener{
    private static final String TAG = "数据";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void LoadDataPost(View view) {
        //封装后
            Map<String,String> map = new HashMap<>();
                map.put("pkregister", "360e8ac8d5604593940271bfb8848612");
                map.put("begin", "0");
                map.put("pageLength", 10 + "");
        GetNetData.LoadDataPost(this,112,"http://123.57.232.188:8080/hyb/ws/coupon/list",map,this);
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


     /*
     * 自定义吐司*/
    public void Toase(Context context) {
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.success_dialog, null);
        toast.setView(v);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

      /*  boolean[] isInitView = {false};
        ViewTreeObserver viewTreeObserver = button.getViewTreeObserver();
        TreeListener treeListener = new TreeListener(isInitView,0);
        viewTreeObserver.addOnPreDrawListener(treeListener);*/

    }

    @Override
    public void onSuccess(int reqcode, Object result) {
        Log.d(TAG, reqcode+" get onSuccess = " + result);
    }

    @Override
    public void onFailed(int reqcode, Object result) {
        Log.d(TAG, reqcode+ " get onFailed = " + result.toString());
    }


    public class TreeListener implements ViewTreeObserver.OnPreDrawListener {
        private boolean[] isInitView = {false};

        public TreeListener(boolean[] isInitView, int pssition) {
            this.pssition = pssition;
            this.isInitView = isInitView;
        }

        private int pssition;

        @Override
        public boolean onPreDraw() {
            return false;
        }
    }
}
