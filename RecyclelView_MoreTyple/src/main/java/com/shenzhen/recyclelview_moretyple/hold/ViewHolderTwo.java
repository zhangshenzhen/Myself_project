package com.shenzhen.recyclelview_moretyple.hold;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.recyclelview_moretyple.R;
import com.shenzhen.recyclelview_moretyple.TypleAdapter;
import com.shenzhen.recyclelview_moretyple.base.BaseViewHolder;
import com.shenzhen.recyclelview_moretyple.typlebean.Value2;


public class ViewHolderTwo extends BaseViewHolder<Value2> {
    public ViewHolderTwo(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final Value2 modle, int position, TypleAdapter adpter) {
       final TextView tv = (TextView) getView(R.id.tv);
        tv.setText(modle.getAdress());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tv.getContext(), modle.getAdress(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
