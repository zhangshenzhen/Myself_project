package com.shenzhen.recycleview_coupousmoretyple.hold;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.recycleview_coupousmoretyple.R;
import com.shenzhen.recycleview_coupousmoretyple.TypleAdapter;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewHolder;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean;


public class ViewHolderOne extends BaseViewHolder<ResultDataBean> {
    public ViewHolderOne(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final ResultDataBean modle, int position, TypleAdapter adpter) {
       final TextView tv = (TextView) getView(R.id.tv_siut_content);
        tv.setText(modle.getContent());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tv.getContext(), modle.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
