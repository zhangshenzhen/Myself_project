package com.shenzhen.retrofit.bean;

import java.math.BigDecimal;
import java.util.List;

public class YouHuiquanListBean {

    /**
     * resultStatus : 0
     * msg : SUCCESS
     * resultData : [{"pkcoupon":"030ba751786d415fa61346529be72dbe","pkmuser":"f1556414e0ed485bb79224d41082f3b7","payamount":75,"valueamount":100,"startdate":"2017-03-13","enddate":"2018-03-13","remark":"无门槛","usestatus":2,"scope_of_use":2},{"pkcoupon":"d85da30c2d274ffea9a6a27277a6dec8","pkmuser":"f1556414e0ed485bb79224d41082f3b7","payamount":80,"valueamount":100,"startdate":"2018-11-11","enddate":"2018-11-30","remark":"无门槛","usestatus":0,"scope_of_use":2},{"pkcoupon":"9a6b41cfb94b4664a3f27c278e710abe","pkmuser":"8d91228426a344c68e0d4147b3cd7a80","payamount":43,"valueamount":50,"startdate":"2017-03-10","enddate":"2018-12-26","remark":"无门槛","usestatus":0,"scope_of_use":2},{"pkcoupon":"9a6b41cfb94b4664a3f27c278e710abe","pkmuser":"8d91228426a344c68e0d4147b3cd7a80","payamount":43,"valueamount":50,"startdate":"2017-03-10","enddate":"2018-12-26","remark":"无门槛","usestatus":0,"scope_of_use":2},{"pkcoupon":"46091847bfb145b7b015cc2838d373e7","pkmuser":"f1556414e0ed485bb79224d41082f3b7","payamount":80,"valueamount":100,"startdate":"2017-03-04","enddate":"2019-03-11","remark":"无门槛","usestatus":0,"scope_of_use":2}]
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

    public static class ResultDataBean {

        /**
         * pkorderid : 868927fb4e3940e4aa54f46808bf96ee
         * pkcoupon : 8745936b37b2412681620a646ad2e8ab
         * payamount : 70.0
         * valueamount : 100.0
         * startdate : 2018-11-23
         * enddate : 2018-11-26
         * remark : 平台券
         * usestatus : 0
         * scope_of_use : 1
         * coupon_url : http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/1542939303006796.png
         * label : 果蔬生鲜，商店超市
         */
        private String paytime;
        private int policystatus;
        private String policycontent;
        private String muname;

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public int getPolicystatus() {
            return policystatus;
        }

        public void setPolicystatus(int policystatus) {
            this.policystatus = policystatus;
        }

        public String getPolicycontent() {
            return policycontent;
        }

        public void setPolicycontent(String policycontent) {
            this.policycontent = policycontent;
        }

        public String getMuname() {
            return muname;
        }

        public void setMuname(String muname) {
            this.muname = muname;
        }



        private String pkorderid;
        private String pkcoupon;
        private BigDecimal payamount;
        private BigDecimal valueamount;
        private String startdate;
        private String enddate;
        private String remark;
        private int usestatus;
        private int scope_of_use;
        private String coupon_url;
        private String label;

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

        public BigDecimal getPayamount() {
            return payamount;
        }

        public void setPayamount(BigDecimal payamount) {
            this.payamount = payamount;
        }

        public BigDecimal getValueamount() {
            return valueamount;
        }

        public void setValueamount(BigDecimal valueamount) {
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

        public String getCoupon_url() {
            return coupon_url;
        }

        public void setCoupon_url(String coupon_url) {
            this.coupon_url = coupon_url;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
