package com.shenzhen.scrollview_inner_slide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        View view = View.inflate(viewGroup.getContext(), R.layout.gridview_item,null);
        ScViewHolder scViewHolder = new ScViewHolder(view);
        return scViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,int position) {
           ScViewHolder scholder = (ScViewHolder) viewHolder;
           scholder.imgv.setImageResource(listdata.get(position));
           scholder.tv_num.setText(position+"");
           scholder.imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, "这是Recycleview第："+ position+" 个条目", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


}
