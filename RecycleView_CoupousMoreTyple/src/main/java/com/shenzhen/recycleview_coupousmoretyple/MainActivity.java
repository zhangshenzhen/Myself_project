package com.shenzhen.recycleview_coupousmoretyple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean2;
import com.shenzhen.recycleview_coupousmoretyple.utils.DensityUtil;
import com.shenzhen.recycleview_coupousmoretyple.utils.GridSpacingItemDecoration;
import com.shenzhen.recycleview_coupousmoretyple.utils.HorizontalSpaceItemDecoration;
import com.shenzhen.recycleview_coupousmoretyple.utils.RecyclerViewSpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String TAG = "优惠券数据";
    private List mresultDataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mresultDataBean = new ArrayList<>();

        recyclerView = findViewById(R.id.rcv);
        //设置间距 方式一
//        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
//        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.TOP_DECORATION, 10);//top间距
//        recyclerView.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));

        //设置间距 方式一
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(this, 5));//5
        recyclerView.addItemDecoration(gridSpacingItemDecoration);

        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        couponsData();
    }

    private void couponsData() {
        /*
         * 封装前*/
        RequestParams params = new RequestParams("http://123.57.232.188:8080/hyb/ws/coupon/all/list");
        params.addParameter("pkregister", "4075ffd325fe48c48ed05ee4c0372635");
        params.addParameter("begin", "0");
        params.addParameter("pageLength", 10 + "");
        params.addParameter("sortType", "1");

        x.http().request(HttpMethod.POST, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                JsonData(result.toString());
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

    private void JsonData(String result) {

        recyclerView.setAdapter(new TypleAdapter(this, result));
    }
}
