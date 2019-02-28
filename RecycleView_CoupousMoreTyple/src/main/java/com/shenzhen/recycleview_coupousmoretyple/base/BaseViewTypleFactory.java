package com.shenzhen.recycleview_coupousmoretyple.base;

import android.view.View;

import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean2;


public interface BaseViewTypleFactory {

    int typle(ResultDataBean resultDataBean);
    int typle(ResultDataBean2 resultDataBean);


    BaseViewHolder creatViewHolder(View v, int relatave);
}

