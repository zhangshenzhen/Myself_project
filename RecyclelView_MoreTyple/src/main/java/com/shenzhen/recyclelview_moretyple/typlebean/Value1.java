package com.shenzhen.recyclelview_moretyple.typlebean;

import com.shenzhen.recyclelview_moretyple.base.BaseValue;
import com.shenzhen.recyclelview_moretyple.base.BaseViewTypleFactory;

public class Value1 implements BaseValue {
    public Value1(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String  name;

    @Override
    public int getLayoutId(BaseViewTypleFactory factory) {
        return factory.typle(this);
    }
}
