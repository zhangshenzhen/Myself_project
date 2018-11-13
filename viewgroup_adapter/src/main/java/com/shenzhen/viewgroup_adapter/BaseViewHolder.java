package com.shenzhen.viewgroup_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

   public SparseArray <View> views;
    public final ImageView imageView;
    public final TextView tv;
    public final LinearLayout liner;



    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        views = new SparseArray<>();
        imageView = itemView.findViewById(R.id.img);
        tv = itemView.findViewById(R.id.tv);
        liner = itemView.findViewById(R.id.liner);
    }
    public <T extends View>  T getView(int viewId){
        View view = views.get(viewId);
        if (view ==null){
            view = itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }

    public View getRootView(){
        return itemView;
    }
}
