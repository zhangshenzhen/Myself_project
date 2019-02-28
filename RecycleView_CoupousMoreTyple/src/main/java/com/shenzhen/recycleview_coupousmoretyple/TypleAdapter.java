package com.shenzhen.recycleview_coupousmoretyple;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import com.shenzhen.recycleview_coupousmoretyple.base.BaseValue;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewHolder;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewTypleFactory;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean2;
import com.shenzhen.recycleview_coupousmoretyple.factory.ViewTypeFactory;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TypleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final String TAG = "数据adapter";
    private BaseViewTypleFactory mFactory;
    private List<BaseValue> mList;
    private List<BaseValue>  mresultDataBean;
    private Context mContext;

    public TypleAdapter(Context context,  String result) {
           this.mContext = context;
           mFactory = new ViewTypeFactory();
           mresultDataBean = new ArrayList<>();
           makeData(result);
    }

    private void makeData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.optJSONArray("resultData");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = new JSONObject(jsonArray.get(i).toString());
                //类型 一
                if (i % 2 == 0) {
                    ResultDataBean dataBeans = new ResultDataBean();
                    dataBeans.setPayamount(object.optString("payamount")+"");
                    dataBeans.setValueamount(object.optString("valueamount")+"");
                    dataBeans.setContent(object.optString("content")+"");
                    dataBeans.setLabel(object.optString("label")+"");
                    dataBeans.setCoupon_label(object.optString("coupon_label")+"");
                    dataBeans.setCoupon_title(object.optString("coupon_title")+"");
                    mresultDataBean.add(dataBeans);
                } else { //类型 二
                    ResultDataBean2 dataBeans2 = new ResultDataBean2();
                    dataBeans2.setPayamount(object.optString("payamount")+"");
                    dataBeans2.setValueamount(object.optString("valueamount")+"");
                    dataBeans2.setLabel(object.optString("label"+""));
                    dataBeans2.setCoupon_label(object.optString("coupon_label")+"");
                    dataBeans2.setCoupon_title(object.optString("coupon_title")+"");
                    mresultDataBean.add(dataBeans2);
                }
               // Log.i(TAG, "解析.. " + dataBeans.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "解析.. " + e.getMessage().toString());
        }
    }


   /* @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(parent.getContext(), viewType, null);
        return mFactory.creatViewHolder(v, viewType);
    }
    */

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("ViewTypeonCreateView", ""+viewType);
        View v = View.inflate(parent.getContext(), viewType, null);
        return mFactory.creatViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.setUpView(mresultDataBean.get(position), position, this);
    }

    @Override
    public int getItemViewType(int position) {  //返回不同的类型
       Log.i("ViewTypegetItemViewType", ""+mresultDataBean.get(position).getLayoutId(mFactory));
        return mresultDataBean.get(position).getLayoutId(mFactory); //
    }

   //资料 https://blog.csdn.net/zchlww/article/details/51691123
    @Override
    public int getItemCount() {
        return mresultDataBean.size();
    }
}
