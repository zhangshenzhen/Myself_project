package com.shenzhen.cachedemo.utils.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public  abstract class MyBaseAdapter extends BaseAdapter {

   @Override
    public int getCount() {
        return getNum();
    }


    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return MyView(position,convertView,parent);
    }

    public abstract int  getNum();
    public abstract View MyView(int position, View convertView, ViewGroup parent);

}
