package com.shenzhen.viewgroup_adapter.more_typle.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.viewgroup_adapter.R;
import com.shenzhen.viewgroup_adapter.UserAdapter;
import com.shenzhen.viewgroup_adapter.more_typle.MyAdapter;
import com.shenzhen.viewgroup_adapter.more_typle.base.BaseViewHolder;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value1;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value2;

public class ViewHolderTwo extends BaseViewHolder<Value2> {
    public ViewHolderTwo(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final Value2 modle, int position, MyAdapter adpter) {
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
