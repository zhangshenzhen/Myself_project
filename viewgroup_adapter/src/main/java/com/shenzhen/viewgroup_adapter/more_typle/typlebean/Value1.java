package com.shenzhen.viewgroup_adapter.more_typle.typlebean;

import com.shenzhen.viewgroup_adapter.more_typle.base.BaseValue;
import com.shenzhen.viewgroup_adapter.more_typle.base.BaseViewTypleFactory;

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
