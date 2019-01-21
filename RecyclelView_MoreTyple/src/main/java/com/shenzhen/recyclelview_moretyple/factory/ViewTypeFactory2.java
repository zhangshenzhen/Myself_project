package com.shenzhen.recyclelview_moretyple.factory;

import android.view.View;

import com.shenzhen.recyclelview_moretyple.base.BaseViewHolder;
import com.shenzhen.recyclelview_moretyple.base.BaseViewTypleFactory;
import com.shenzhen.recyclelview_moretyple.hold.ViewHolderOne;
import com.shenzhen.recyclelview_moretyple.hold.ViewHolderThree;
import com.shenzhen.recyclelview_moretyple.hold.ViewHolderTwo;
import com.shenzhen.recyclelview_moretyple.typlebean.Value1;
import com.shenzhen.recyclelview_moretyple.typlebean.Value2;
import com.shenzhen.recyclelview_moretyple.typlebean.Value3;



public class ViewTypeFactory2  {

    public BaseViewHolder creatViewHolder(View v, int Type) {
        BaseViewHolder mholder = null;
        if (Type == 1)
            mholder = new ViewHolderOne(v);
        else if (Type == 2)
            mholder = new ViewHolderTwo(v);
        else if (Type == 3)
            mholder = new ViewHolderThree(v);
        return mholder;

    }
}
