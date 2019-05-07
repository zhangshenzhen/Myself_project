package com.shenzhen.recycleview_coupousmoretyple.factory;

import android.view.View;


import com.shenzhen.recycleview_coupousmoretyple.R;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewHolder;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewTypleFactory;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean;
import com.shenzhen.recycleview_coupousmoretyple.bean.ResultDataBean2;
import com.shenzhen.recycleview_coupousmoretyple.hold.ViewHolderOne;

import com.shenzhen.recycleview_coupousmoretyple.hold.ViewHolderTwo;


public class ViewTypeFactory implements BaseViewTypleFactory {
/*    private final int oneId = R.layout.typle1;
    private final int twoId = R.layout.typle2;*/
    private final int oneId = R.layout.item_suit;
    private final int twoId = R.layout.item_common;
    private final int threeId = R.layout.typle3;


    //返回不同的类型                   
    @Override
    public int typle(ResultDataBean typle) {
        return oneId; //类型
    }

    @Override
    public int typle(ResultDataBean2 typle) {
        return twoId;
    }

    @Override
    public BaseViewHolder creatViewHolder(View v, int viewType) {
        BaseViewHolder mholder = null;
        if (viewType == oneId){
            mholder = new ViewHolderOne(v);
        } else {
            mholder = new ViewHolderTwo(v);
        }
        return mholder;
    }
}
