package com.shenzhen.scrollview_inner_slide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenzhen.scrollview_inner_slide.R;
import com.shenzhen.scrollview_inner_slide.holder.ScViewHolder;

import java.util.List;

public class ListTestAdapter extends BaseAdapter {
    private Context context;
    private int num;

    public ListTestAdapter(Context context, int num) {
        this.context = context;
        this.num = num;
    }
   public void updateCount(int num){
        this.num = num;
        notifyDataSetChanged();
   }

    @Override
    public int getCount() {
        return num;
    }

    @Override
    public Object getItem(int i) {
        return num;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ScViewHolder scViewHolder = null;
        if (view == null) {
            scViewHolder = new ScViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            scViewHolder.imgv = view.findViewById(R.id.imgv);
            scViewHolder.tv_num = view.findViewById(R.id.tv_num);
            view.setTag(scViewHolder);
        }else {
          scViewHolder = (ScViewHolder) view.getTag();
        }
        scViewHolder.tv_num.setText(i+"");
        scViewHolder.imgv.setImageResource(R.mipmap.ic_launcher_round);

        return view;
    }

    static class ScViewHolder {

        public ImageView imgv;
        public TextView tv_num;

    }
}
