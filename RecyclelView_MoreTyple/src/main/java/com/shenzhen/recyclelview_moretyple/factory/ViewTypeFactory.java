package com.shenzhen.recyclelview_moretyple.factory;

import android.view.View;

;import com.shenzhen.recyclelview_moretyple.R;
import com.shenzhen.recyclelview_moretyple.base.BaseViewHolder;
import com.shenzhen.recyclelview_moretyple.base.BaseViewTypleFactory;
import com.shenzhen.recyclelview_moretyple.hold.ViewHolderOne;
import com.shenzhen.recyclelview_moretyple.hold.ViewHolderThree;
import com.shenzhen.recyclelview_moretyple.hold.ViewHolderTwo;
import com.shenzhen.recyclelview_moretyple.typlebean.Value1;
import com.shenzhen.recyclelview_moretyple.typlebean.Value2;
import com.shenzhen.recyclelview_moretyple.typlebean.Value3;

public class ViewTypeFactory implements BaseViewTypleFactory {
    private final int oneId = R.layout.typle1;
    private final int twoId = R.layout.typle2;
   /* private final int oneId = R.layout.item_suit;
    private final int twoId = R.layout.item_common;*/
    private final int threeId = R.layout.typle3;

    @Override
    public int typle(Value1 typle) {
        return oneId;
    }

    @Override
    public int typle(Value2 typle) {
        return twoId;
    }

    @Override
    public int typle(Value3 typle) {
        return threeId;
    }

    @Override
    public BaseViewHolder creatViewHolder(View v, int viewType) {
        BaseViewHolder mholder = null;
        if (viewType == oneId)
            mholder = new ViewHolderOne(v);
        else if (viewType == twoId)
            mholder = new ViewHolderTwo(v);
        else if (viewType == threeId)
            mholder = new ViewHolderThree(v);
        return mholder;

    }
}
