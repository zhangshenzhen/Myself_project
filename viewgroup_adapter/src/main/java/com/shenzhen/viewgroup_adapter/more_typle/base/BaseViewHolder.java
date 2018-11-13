package com.shenzhen.viewgroup_adapter.more_typle.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.shenzhen.viewgroup_adapter.UserAdapter;
import com.shenzhen.viewgroup_adapter.more_typle.MyAdapter;

public abstract class BaseViewHolder <T>extends RecyclerView.ViewHolder {
    private SparseArray<View> mArray;
    private View mView;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
        mArray = new SparseArray<>();
    }

    public View getView(int resId) {
        View view = mArray.get(resId);
        if (view == null) {
            view = mView.findViewById(resId);
            mArray.put(resId, view);
        }
        return view;
    }
    public abstract void setUpView(T modle,int position ,MyAdapter adpter);

}
