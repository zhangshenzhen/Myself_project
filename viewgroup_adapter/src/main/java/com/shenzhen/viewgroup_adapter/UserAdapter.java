package com.shenzhen.viewgroup_adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class UserAdapter extends BaseRecycleViewAdapter<UserPersonBean> {

    private  Context context;
    public UserAdapter(Context context, List<UserPersonBean> datas ) {
        super(context, datas,R.layout.user_item);
        this.context = context;

    }
    /*
      填充布局+绑定数据
    * */
    @Override
    protected void bindViewHold(BaseViewHolder holder, final UserPersonBean data, int position) {
         holder.imageView.setBackgroundResource(R.drawable.ic_launcher);
         holder.tv.setText(data.getName());

         holder.liner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
