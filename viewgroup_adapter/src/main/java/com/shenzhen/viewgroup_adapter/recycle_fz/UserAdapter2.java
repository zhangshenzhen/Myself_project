package com.shenzhen.viewgroup_adapter.recycle_fz;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.shenzhen.viewgroup_adapter.R;
import com.shenzhen.viewgroup_adapter.UserPersonBean;

import java.util.List;

public class UserAdapter2 extends BaseRecycleViewAdapter<UserPersonBean> {

    private  Context context;
    private LinearLayout ll_all;
    private ImageView imageView;
    private TextView tv_name;

    public UserAdapter2(Context context, List<UserPersonBean> datas ) {
        super(context, datas, R.layout.user_item);
        this.context = context;

    }
     /*
     * 初始化控件*/
    @Override
    protected void findById(BaseViewHolder2 holder) {
        ll_all = holder.getView(R.id.liner);
        imageView = holder.getView(R.id.img);
        tv_name = (TextView) holder.getView2(R.id.tv);//采用了类型转化的方式查找
    }

    /*
         绑定数据
        * */
    @Override
    protected void bindViewHold(final BaseViewHolder2 holder, final UserPersonBean data, final int position) {

        imageView.setBackgroundResource(R.drawable.ic_launcher);
        tv_name.setText(data.getName()+":"+data.getAge());
        ll_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
                onItemClickListner.onItemClickListner(holder.getRootView(),position);
            }
        });
        ll_all.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListner.onItemLongClickListner(holder.getRootView(),position);
                return true;//true 代表长按结束后短按的不接受，false 长按结束后短按也接受
            }
        });
    }
}
