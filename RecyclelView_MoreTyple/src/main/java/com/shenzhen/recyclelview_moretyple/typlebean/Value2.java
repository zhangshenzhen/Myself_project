package com.shenzhen.recyclelview_moretyple.typlebean;

import com.shenzhen.recyclelview_moretyple.base.BaseValue;
import com.shenzhen.recyclelview_moretyple.base.BaseViewTypleFactory;

public class Value2 implements BaseValue {
    public Value2(String adress) {
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    private String  adress;

    @Override
    public int getLayoutId(BaseViewTypleFactory factory) {
        return factory.typle(this);
    }
}
