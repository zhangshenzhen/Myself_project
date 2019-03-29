package com.shenzhen.dialog.adapter;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.shenzhen.dialog.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YouHuiDialogAdapter extends RecyclerView.Adapter {

    private Context context;
    private int num;
    private Dialog dialog;
    private  List<String> list;
    // private YouHuiQuanBean.YouHuiQuanDataBean youHuiQuanData;
    private Map<Integer,Boolean> map;
    public YouHuiDialogAdapter(Context context, int num, Dialog dialog) {
        this.context = context;
        this.dialog = dialog;
        this.num = num;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_youhui, null);
        YouHuiHolder youHuiHolder = new YouHuiHolder(view);
        return youHuiHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {

        return num;
    }

    static class YouHuiHolder extends RecyclerView.ViewHolder {



        public YouHuiHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
