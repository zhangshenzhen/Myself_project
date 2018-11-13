package com.shenzhen.viewgroup_adapter.more_typle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.shenzhen.viewgroup_adapter.more_typle.base.BaseValue;
import com.shenzhen.viewgroup_adapter.more_typle.base.BaseViewHolder;
import com.shenzhen.viewgroup_adapter.more_typle.base.BaseViewTypleFactory;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value1;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value2;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value3;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private List<BaseValue> mList;
    private BaseViewTypleFactory mFactory;

    public MyAdapter(Context context) {
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
        mList.add(new Value3("18"));
        mList.add(new Value1("陈五"));
        mList.add(new Value2("南京"));
        mList.add(new Value3("32"));
        mList.add(new Value1("周六"));
        mList.add(new Value2("深圳"));
        mList.add(new Value1("小明"));
        mList.add(new Value1("小红"));
        mList.add(new Value3("48"));
        mList.add(new Value2("天津"));
        mList.add(new Value1("小光"));
        mList.add(new Value2("广州"));
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getLayoutId(mFactory);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(parent.getContext(), viewType, null);
        return mFactory.creatViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.setUpView(mList.get(position), position,this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
