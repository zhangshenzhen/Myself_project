package com.shenzhen.viewgroup_adapter.more_typle.base;

import android.view.View;

import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value1;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value2;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value3;

public interface BaseViewTypleFactory {

    int typle(Value1 typle);
    int typle(Value2 typle);
    int typle(Value3 typle);

    BaseViewHolder creatViewHolder(View v  , int relatave );
}

