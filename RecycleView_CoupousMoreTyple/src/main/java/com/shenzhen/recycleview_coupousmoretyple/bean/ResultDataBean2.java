package com.shenzhen.recycleview_coupousmoretyple.bean;

import com.shenzhen.recycleview_coupousmoretyple.base.BaseValue;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewTypleFactory;

public  class ResultDataBean2 implements BaseValue {


    /**
     * pkorderid : f41f25c054f945878f76f2fadff556af
     * pkcoupon : bd34bf1ff1fc4b2187daaf3ff4907a72
     * payamount : 26.0
     * valueamount : 3.0
     * startdate : 2019-02-20
     * enddate : 2019-02-28
     * remark : 中石油（四通）
     * usestatus : 0
     * scope_of_use : 1
     * label : 中石油（四通）
     * dialog_title : 中石油（四通）
     * dialog_label : 中石油（四通）标签
     * coupon_title : 中石油（四通）
     * coupon_label : 中石油（四通）1
     * content : 适用商家：中石油（四通）
     */

    private String pkorderid;
    private String pkcoupon;
    private String payamount;
    private String valueamount;
    private String startdate;
    private String enddate;
    private String remark;
    private int usestatus;
    private int scope_of_use;
    private String label;
    private String dialog_title;
    private String dialog_label;

    @Override
    public String toString() {
        return "ResultDataBean{" +
                "pkorderid='" + pkorderid + '\'' +
                ", pkcoupon='" + pkcoupon + '\'' +
                ", payamount='" + payamount + '\'' +
                ", valueamount='" + valueamount + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", remark='" + remark + '\'' +
                ", usestatus=" + usestatus +
                ", scope_of_use=" + scope_of_use +
                ", label='" + label + '\'' +
                ", dialog_title='" + dialog_title + '\'' +
                ", dialog_label='" + dialog_label + '\'' +
                ", coupon_title='" + coupon_title + '\'' +
                ", coupon_label='" + coupon_label + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    private String coupon_title;
    private String coupon_label;
    private String content;

    public String getPkorderid() {
        return pkorderid;
    }

    public void setPkorderid(String pkorderid) {
        this.pkorderid = pkorderid;
    }

    public String getPkcoupon() {
        return pkcoupon;
    }

    public void setPkcoupon(String pkcoupon) {
        this.pkcoupon = pkcoupon;
    }

    public String getPayamount() {
        return payamount;
    }

    public void setPayamount(String payamount) {
        this.payamount = payamount;
    }

    public String getValueamount() {
        return valueamount;
    }

    public void setValueamount(String valueamount) {
        this.valueamount = valueamount;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUsestatus() {
        return usestatus;
    }

    public void setUsestatus(int usestatus) {
        this.usestatus = usestatus;
    }

    public int getScope_of_use() {
        return scope_of_use;
    }

    public void setScope_of_use(int scope_of_use) {
        this.scope_of_use = scope_of_use;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDialog_title() {
        return dialog_title;
    }

    public void setDialog_title(String dialog_title) {
        this.dialog_title = dialog_title;
    }

    public String getDialog_label() {
        return dialog_label;
    }

    public void setDialog_label(String dialog_label) {
        this.dialog_label = dialog_label;
    }

    public String getCoupon_title() {
        return coupon_title;
    }

    public void setCoupon_title(String coupon_title) {
        this.coupon_title = coupon_title;
    }

    public String getCoupon_label() {
        return coupon_label;
    }

    public void setCoupon_label(String coupon_label) {
        this.coupon_label = coupon_label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getLayoutId(BaseViewTypleFactory factory) {
        return factory.typle(this);
    }
}