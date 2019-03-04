package com.shenzhen.recycleview_coupousmoretyple.base;

import android.view.View;
import android.view.ViewGroup;

import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean2;


public interface BaseViewTypleFactory {

    int typle(ResultDataBean resultDataBean);
    int typle(ResultDataBean2 resultDataBean2);


    BaseViewHolder creatViewHolder(View v, int relatave);
}

