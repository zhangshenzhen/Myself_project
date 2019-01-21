package com.shenzhen.recyclelview_moretyple.base;

import android.view.View;

import com.shenzhen.recyclelview_moretyple.typlebean.Value1;
import com.shenzhen.recyclelview_moretyple.typlebean.Value2;
import com.shenzhen.recyclelview_moretyple.typlebean.Value3;


public interface BaseViewTypleFactory {

    int typle(Value1 typle);
    int typle(Value2 typle);
    int typle(Value3 typle);

    BaseViewHolder creatViewHolder(View v, int relatave);
}

