package com.shenzhen.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PopupWindowAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public PopupWindowAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<String> lists){
        list=lists;
        this.notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        HolderView holderView=null;
        if(null==convertView){
            holderView=new HolderView();
            convertView= LayoutInflater.from(context).inflate(R.layout.popupwindow_item,  parent,false);
            holderView.time= (TextView) convertView.findViewById(R.id.tv_popupwindow);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView) convertView.getTag();
        }
        String time=(String) getItem(position);
        holderView.time.setText(time);

        return convertView;
    }

    class HolderView {
        TextView time;
    }

}
