package com.shenzhen.recycleviewfengzhuang.fengzhaung;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenzhen.recycleviewfengzhuang.R;
import com.shenzhen.recycleviewfengzhuang.bean.UserPersonBean;


import java.util.List;

public class UserAdapter extends BaseRecycleViewAdapter<UserPersonBean> {

    private  Context context;
    private LinearLayout ll_all;
    private ImageView imageView;
    private TextView tv_name;

    public UserAdapter(Context context, List<UserPersonBean> datas ) {
        super(context, datas, R.layout.user_item);
        this.context = context;

    }
     /*
     * 初始化控件*/
    @Override
    protected void findById(BaseViewHolder holder) {
        ll_all = holder.getView(R.id.liner);
        imageView = holder.getView(R.id.img);
        tv_name = (TextView) holder.getView2(R.id.tv);//采用了类型转化的方式查找
    }

    /*
         绑定数据
        * */
    @Override
    protected void bindViewHold(final BaseViewHolder holder, final UserPersonBean data, final int position) {

        imageView.setBackgroundResource(R.mipmap.ic_launcher);
        tv_name.setText(data.getName()+":"+data.getAge());



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
                onItemClickListner.onItemClickListner(imageView,position);
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListner.onItemLongClickListner(imageView,position);
                return true;//true 代表长按结束后短按的不接受，false 长按结束后短按也接受
            }
        });

        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
                onItemClickListner.onItemClickListner(tv_name,position);
            }
        });
        tv_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListner.onItemLongClickListner(tv_name,position);
                return true;//true 代表长按结束后短按的不接受，false 长按结束后短按也接受
            }
        });
    }
}
