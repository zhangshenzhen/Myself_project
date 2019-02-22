package com.shenzhen.scrollview_inner_slide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.shenzhen.scrollview_inner_slide.R;
import com.shenzhen.scrollview_inner_slide.holder.ScViewHolder;

import java.util.List;

public class FirstHorizatalAdapter extends RecyclerView.Adapter {

    private List<Integer> listdata;
    private  Context context;
    public FirstHorizatalAdapter(Context context,List<Integer> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item,null);
        ScViewHolder scViewHolder = new ScViewHolder(view);
        return scViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
           ScViewHolder scholder = (ScViewHolder) viewHolder;
           scholder.imgv.setImageResource(R.mipmap.ic_launcher);
           scholder.tv_num.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


}
