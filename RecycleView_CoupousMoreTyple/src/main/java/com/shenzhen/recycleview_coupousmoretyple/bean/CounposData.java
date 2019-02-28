package com.shenzhen.recycleview_coupousmoretyple.bean;

import com.shenzhen.recycleview_coupousmoretyple.base.BaseValue;
import com.shenzhen.recycleview_coupousmoretyple.base.BaseViewTypleFactory;

import java.math.BigDecimal;
import java.util.List;

public class CounposData {


    /**
     * resultStatus : 0
     * msg : SUCCESS
     * resultData : [{"pkorderid":"f41f25c054f945878f76f2fadff556af","pkcoupon":"bd34bf1ff1fc4b2187daaf3ff4907a72","payamount":26,"valueamount":3,"startdate":"2019-02-20","enddate":"2019-02-28","remark":"中石油（四通）","usestatus":0,"scope_of_use":1,"label":"中石油（四通）","dialog_title":"中石油（四通）","dialog_label":"中石油（四通）标签","coupon_title":"中石油（四通）","coupon_label":"中石油（四通）1","content":"适用商家：中石油（四通）"},{"pkorderid":"c9bbba3bf4584d3493da9e627c479357","pkcoupon":"4aa7540620124cd8bcde016d3a353df7","payamount":200,"valueamount":199.9,"startdate":"2019-01-01","enddate":"2019-01-31","remark":"春秋商厦","usestatus":0,"scope_of_use":1,"label":"春秋商厦","dialog_title":"春秋商厦","dialog_label":"繁城都市长期97折","coupon_title":"春秋商厦","coupon_label":"繁城都市长期97折","content":"适用商家：春秋国旅（商厦）"},{"pkorderid":"b8240aca637d46b18bbe6ae3a8ba69f7","pkcoupon":"6226c5e8e72b40d1a7204bde1323930b","payamount":10,"valueamount":9.9,"startdate":"2019-01-03","enddate":"2019-01-31","remark":"app领取不同商家券","usestatus":0,"scope_of_use":1,"dialog_title":"app领取不同商家券","dialog_label":"长期优惠98这就是这样的","coupon_title":"app领取不同商家券","coupon_label":"长期优惠98这就是这样的","content":"快典"},{"pkorderid":"7b0858ba6cc84e9ba54e79a272771feb","pkcoupon":"1ba3ad0e831d4da1b5377cc075d25b44","payamount":20,"valueamount":16,"startdate":"2019-01-01","enddate":"2019-01-31","remark":"春秋国旅（商厦）","usestatus":0,"scope_of_use":1,"label":"春秋国旅（商厦）","dialog_title":"春秋国旅（商厦）","dialog_label":"繁城都市长期97折","coupon_title":"春秋国旅（商厦）","coupon_label":"繁城都市长期97折","content":"适用商家：春秋国旅（商厦）"},{"pkorderid":"6d04eb52cdfd49adb9f6904f364bc560","pkcoupon":"a0a61cc0b52d4b20b8521b91e87e01ce","payamount":56.8,"valueamount":19.2,"startdate":"2019-01-01","enddate":"2019-01-31","remark":"春秋国旅商厦","usestatus":0,"scope_of_use":1,"label":"春秋国旅商厦","dialog_title":"春秋国旅商厦","dialog_label":"繁城都市长期97折","coupon_title":"春秋国旅商厦","coupon_label":"繁城都市长期97折","content":"适用商家：春秋国旅（商厦）"}]
     */

    private int resultStatus;
    private String msg;
    private List<ResultDataBean> resultData;

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultDataBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean> resultData) {
        this.resultData = resultData;
    }


  /*  public List<ResultDataBean2> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean2> resultData2) {
        this.resultData = resultData;
    }*/

}
