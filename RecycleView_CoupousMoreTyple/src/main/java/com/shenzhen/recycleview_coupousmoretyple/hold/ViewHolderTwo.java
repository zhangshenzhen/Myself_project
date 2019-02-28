package com.shenzhen.recycleview_coupousmoretyple.hold;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.recycleview_coupousmoretyple.R;
import com.shenzhen.recycleview_coupousmoretyple.TypleAdapter;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewHolder;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean2;


public class ViewHolderTwo extends BaseViewHolder<ResultDataBean2> {
    public ViewHolderTwo(@NonNull View itemView) {
        super(itemView);
    }

    @Override
     public void setUpView(final ResultDataBean2 modle, int position, TypleAdapter adpter) {
      final TextView tv = (TextView) getView(R.id.tv_lable2);
        tv.setText(modle.getCoupon_label()+"");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tv.getContext(), modle.getCoupon_title(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
