package com.shenzhen.scrollview_inner_slide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.scrollview_inner_slide.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class InnGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> list;


    public InnGridViewAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            viewHolder.roundImageView = (ImageView) convertView.findViewById(R.id.imgv);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //绑定数据
        viewHolder.tv_name.setText(position + "");

        viewHolder.roundImageView.setImageResource(list.get(position));
        viewHolder.roundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "这是第 ："+position+" 个", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView roundImageView;//处理圆图
        TextView tv_name;//显示名字

    }
}
