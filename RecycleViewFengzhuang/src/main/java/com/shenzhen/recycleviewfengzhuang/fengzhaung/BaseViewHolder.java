package com.shenzhen.recycleviewfengzhuang.fengzhaung;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder {

   public SparseArray <View> views;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }
    public View getView2(int viewId){
       View view = views.get(viewId);//

        if (view ==null){
            view = itemView.findViewById(viewId);
            views.put(viewId,view);//存入集合便于复用
        }
        return  view;
    }

    public <T extends View>T getView(int viewId){
        View view = views.get(viewId);
        if (view ==null){
            Log.i("TAG","查找控件");
            view = itemView.findViewById(viewId);
            views.put(viewId,view);//存入集合便于复用
        }else {
            return (T) view;
        }
        return (T) view;
    }

    public View getRootView(){
        return itemView;
    }
}
