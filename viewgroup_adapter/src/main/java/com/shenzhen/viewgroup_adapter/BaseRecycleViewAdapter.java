package com.shenzhen.viewgroup_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;

    private LayoutInflater inflater;

    private List<T> datas;

    private int layoutId;

   protected OnItemClickListner onItemClickListner;//单击事件

    protected OnItemLongClickListner onItemLongClickListner;//长按单击事件*/

    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    public BaseRecycleViewAdapter(Context context, List<T> datas ,int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LinearLayout.inflate(context,layoutId,null);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

        bindViewHold(baseViewHolder,datas.get(i),i);
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    protected abstract void bindViewHold(BaseViewHolder holder, T data, int position);


   public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }


    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }


    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
}
