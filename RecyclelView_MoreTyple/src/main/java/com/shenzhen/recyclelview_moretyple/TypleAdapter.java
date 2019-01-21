package com.shenzhen.recyclelview_moretyple;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.shenzhen.recyclelview_moretyple.base.BaseValue;
import com.shenzhen.recyclelview_moretyple.base.BaseViewHolder;
import com.shenzhen.recyclelview_moretyple.base.BaseViewTypleFactory;
import com.shenzhen.recyclelview_moretyple.factory.ViewTypeFactory;
import com.shenzhen.recyclelview_moretyple.typlebean.Value1;
import com.shenzhen.recyclelview_moretyple.typlebean.Value2;
import com.shenzhen.recyclelview_moretyple.typlebean.Value3;

import java.util.ArrayList;
import java.util.List;

public class TypleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private BaseViewTypleFactory mFactory;
    private List<BaseValue> mList;
    private Context mContext;

    public TypleAdapter(Context context) {
        this.mContext = context;
        mFactory = new ViewTypeFactory();
        mList = new ArrayList<>();
        mList.add(new Value1("刘大"));
        mList.add(new Value2("北京"));
        mList.add(new Value3("8"));
        mList.add(new Value1("王二"));
        mList.add(new Value2("北京"));
        mList.add(new Value3("11"));
        mList.add(new Value1("李四"));
        mList.add(new Value2("上海"));
        mList.add(new Value1("周六"));
        mList.add(new Value2("深圳"));
        mList.add(new Value3("48"));
        mList.add(new Value3("18"));
        mList.add(new Value1("陈五"));
        mList.add(new Value2("南京"));
        mList.add(new Value3("32"));


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
        baseViewHolder.setUpView(mList.get(position), position, this);
    }

    @Override
    public int getItemViewType(int position) {  //返回不同的类型
        Log.i("ViewTypegetItemViewType", ""+mList.get(position).getLayoutId(mFactory));
        return mList.get(position).getLayoutId(mFactory); //
    }

   //资料 https://blog.csdn.net/zchlww/article/details/51691123
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
