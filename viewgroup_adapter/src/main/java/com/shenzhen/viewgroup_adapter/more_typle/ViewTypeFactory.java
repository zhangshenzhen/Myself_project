package com.shenzhen.viewgroup_adapter.more_typle;

import android.view.View;

import com.shenzhen.viewgroup_adapter.R;
import com.shenzhen.viewgroup_adapter.more_typle.base.BaseViewHolder;
import com.shenzhen.viewgroup_adapter.more_typle.base.BaseViewTypleFactory;
import com.shenzhen.viewgroup_adapter.more_typle.holder.ViewHolderOne;
import com.shenzhen.viewgroup_adapter.more_typle.holder.ViewHolderThree;
import com.shenzhen.viewgroup_adapter.more_typle.holder.ViewHolderTwo;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value1;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value2;
import com.shenzhen.viewgroup_adapter.more_typle.typlebean.Value3;

public class ViewTypeFactory  implements BaseViewTypleFactory {
    private final int oneId = R.layout.typle1;
    private final int twoId = R.layout.typle2;
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
        BaseViewHolder mv = null;
        if (viewType == oneId)
            mv = new ViewHolderOne(v);
        else if (viewType == twoId)
            mv = new ViewHolderTwo(v);
        else if (viewType == threeId)
            mv = new ViewHolderThree(v);
        return mv;

    }
}
