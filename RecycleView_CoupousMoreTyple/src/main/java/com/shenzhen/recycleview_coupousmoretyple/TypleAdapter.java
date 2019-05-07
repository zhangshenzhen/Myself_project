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
    private List<BaseValue>  mresultDataBean ;
    private Context mContext;

    public TypleAdapter(Context context,  List<BaseValue>  mresultDataBean) {
           this.mContext = context;
           this.mresultDataBean = mresultDataBean;
           mFactory = new ViewTypeFactory();  //确定条目的类型
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
        baseViewHolder.setUpView(mContext,mresultDataBean.get(position), position, this);
    }

    @Override
    public int getItemViewType(int position) {  //返回不同的类型
       Log.i("ViewTypegetItemView", position+" ——- "+mresultDataBean.get(position).getLayoutId(mFactory));
        return mresultDataBean.get(position).getLayoutId(mFactory); //
    }

   //资料 https://blog.csdn.net/zchlww/article/details/51691123
    @Override
    public int getItemCount() {
        return mresultDataBean.size();
    }
}
