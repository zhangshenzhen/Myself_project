package com.shenzhen.recyclelview_moretyple.typlebean;


import com.shenzhen.recyclelview_moretyple.base.BaseValue;
import com.shenzhen.recyclelview_moretyple.base.BaseViewTypleFactory;

public class Value3 implements BaseValue {


    public String getAge() {
        return age;
    }

    public void setAdress(String adress) {
        this.age = age;
    }

    public Value3(String age) {
        this.age = age;
    }

    private String  age;

    @Override
    public int getLayoutId(BaseViewTypleFactory factory) {
        return factory.typle(this);
    }
}
