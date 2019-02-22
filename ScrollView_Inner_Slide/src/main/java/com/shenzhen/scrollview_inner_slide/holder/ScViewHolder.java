package com.shenzhen.scrollview_inner_slide.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenzhen.scrollview_inner_slide.R;

public class ScViewHolder extends RecyclerView.ViewHolder {

    public final ImageView imgv;
    public final TextView tv_num;

    public ScViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_num = itemView.findViewById(R.id.tv_num);
        imgv = itemView.findViewById(R.id.imgv);
    }
}
