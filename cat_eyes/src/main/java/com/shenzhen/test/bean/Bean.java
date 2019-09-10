package com.shenzhen.test.bean;

import java.util.List;

public class Bean {


    /**
     * resultStatus : 0
     * msg : SUCCESS
     * resultData : {"pkmuser":"82ef0dff141b4cb8977aa29a1443f320","muname":"中石油（开发区）","logo":"996cedfaddba42c6a8fb680b10f51bbb.jpg","bigPic":"merchantAd103cf08ba98348b1b8db716444d64373.jpg","discount":"0.000","address":"安徽阜阳经济开发区阜颍路东侧","latitude":"32.864193","longitude":"115.857003","linkage_pkdealer":"11ba7cad2a204b6cb57512e26a835cdd","switch_recharge":"0","switch_pay":"1","phone":"05582220377","switch_product":"0","merchant_activitys":[],"album_total":"0","album_records":[],"purchaserules":"温馨提示：平台商家将不定期有不同幅度优惠活动，敬请关注各商家电子卡使用说明！","moreservices":"WiFi","comment_total":0,"comments":[],"startLevel":"0","can_recieve_coupons":[{"pkcoupon":"bd2bc389115547a7b43a8120ad54d28b","payamount":50,"maxpayamount":50,"valueamount":100,"startdate":"2018-11-22","policystatus":1,"remark":"50抵扣100","activity_id":"8"},{"pkcoupon":"a5aadb66908e4237ad59bd56be182fb2","payamount":65,"maxpayamount":65,"valueamount":100,"startdate":"2018-11-22","policystatus":1,"remark":"65抵扣100","activity_id":"7"}],"dividend_memberpoint_normal":1000}
     */

    private int resultStatus;
    private String msg;
    private ResultDataBean resultData;

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

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * pkmuser : 82ef0dff141b4cb8977aa29a1443f320
         * muname : 中石油（开发区）
         * logo : 996cedfaddba42c6a8fb680b10f51bbb.jpg
         * bigPic : merchantAd103cf08ba98348b1b8db716444d64373.jpg
         * discount : 0.000
         * address : 安徽阜阳经济开发区阜颍路东侧
         * latitude : 32.864193
         * longitude : 115.857003
         * linkage_pkdealer : 11ba7cad2a204b6cb57512e26a835cdd
         * switch_recharge : 0
         * switch_pay : 1
         * phone : 05582220377
         * switch_product : 0
         * merchant_activitys : []
         * album_total : 0
         * album_records : []
         * purchaserules : 温馨提示：平台商家将不定期有不同幅度优惠活动，敬请关注各商家电子卡使用说明！
         * moreservices : WiFi
         * comment_total : 0
         * comments : []
         * startLevel : 0
         * can_recieve_coupons : [{"pkcoupon":"bd2bc389115547a7b43a8120ad54d28b","payamount":50,"maxpayamount":50,"valueamount":100,"startdate":"2018-11-22","policystatus":1,"remark":"50抵扣100","activity_id":"8"},{"pkcoupon":"a5aadb66908e4237ad59bd56be182fb2","payamount":65,"maxpayamount":65,"valueamount":100,"startdate":"2018-11-22","policystatus":1,"remark":"65抵扣100","activity_id":"7"}]
         * dividend_memberpoint_normal : 1000.0
         */

        private String pkmuser;
        private String muname;
        private String logo;
        private String bigPic;
        private String discount;
        private String address;
        private String latitude;
        private String longitude;
        private String linkage_pkdealer;
        private String switch_recharge;
        private String switch_pay;
        private String phone;
        private String switch_product;
        private String album_total;
        private String purchaserules;
        private String moreservices;
        private int comment_total;
        private String startLevel;
        private double dividend_memberpoint_normal;
        private List<?> merchant_activitys;
        private List<?> album_records;
        private List<?> comments;
        private List<CanRecieveCouponsBean> can_recieve_coupons;

        public String getPkmuser() {
            return pkmuser;
        }

        public void setPkmuser(String pkmuser) {
            this.pkmuser = pkmuser;
        }

        public String getMuname() {
            return muname;
        }

        public void setMuname(String muname) {
            this.muname = muname;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getBigPic() {
            return bigPic;
        }

        public void setBigPic(String bigPic) {
            this.bigPic = bigPic;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLinkage_pkdealer() {
            return linkage_pkdealer;
        }

        public void setLinkage_pkdealer(String linkage_pkdealer) {
            this.linkage_pkdealer = linkage_pkdealer;
        }

        public String getSwitch_recharge() {
            return switch_recharge;
        }

        public void setSwitch_recharge(String switch_recharge) {
            this.switch_recharge = switch_recharge;
        }

        public String getSwitch_pay() {
            return switch_pay;
        }

        public void setSwitch_pay(String switch_pay) {
            this.switch_pay = switch_pay;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSwitch_product() {
            return switch_product;
        }

        public void setSwitch_product(String switch_product) {
            this.switch_product = switch_product;
        }

        public String getAlbum_total() {
            return album_total;
        }

        public void setAlbum_total(String album_total) {
            this.album_total = album_total;
        }

        public String getPurchaserules() {
            return purchaserules;
        }

        public void setPurchaserules(String purchaserules) {
            this.purchaserules = purchaserules;
        }

        public String getMoreservices() {
            return moreservices;
        }

        public void setMoreservices(String moreservices) {
            this.moreservices = moreservices;
        }

        public int getComment_total() {
            return comment_total;
        }

        public void setComment_total(int comment_total) {
            this.comment_total = comment_total;
        }

        public String getStartLevel() {
            return startLevel;
        }

        public void setStartLevel(String startLevel) {
            this.startLevel = startLevel;
        }

        public double getDividend_memberpoint_normal() {
            return dividend_memberpoint_normal;
        }

        public void setDividend_memberpoint_normal(double dividend_memberpoint_normal) {
            this.dividend_memberpoint_normal = dividend_memberpoint_normal;
        }

        public List<?> getMerchant_activitys() {
            return merchant_activitys;
        }

        public void setMerchant_activitys(List<?> merchant_activitys) {
            this.merchant_activitys = merchant_activitys;
        }

        public List<?> getAlbum_records() {
            return album_records;
        }

        public void setAlbum_records(List<?> album_records) {
            this.album_records = album_records;
        }

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public List<CanRecieveCouponsBean> getCan_recieve_coupons() {
            return can_recieve_coupons;
        }

        public void setCan_recieve_coupons(List<CanRecieveCouponsBean> can_recieve_coupons) {
            this.can_recieve_coupons = can_recieve_coupons;
        }

        public static class CanRecieveCouponsBean {
            /**
             * pkcoupon : bd2bc389115547a7b43a8120ad54d28b
             * payamount : 50.0
             * maxpayamount : 50.0
             * valueamount : 100.0
             * startdate : 2018-11-22
             * policystatus : 1
             * remark : 50抵扣100
             * activity_id : 8
             */

            private String pkcoupon;
            private double payamount;
            private double maxpayamount;
            private double valueamount;
            private String startdate;
            private int policystatus;
            private String remark;
            private String activity_id;

            public String getPkcoupon() {
                return pkcoupon;
            }

            public void setPkcoupon(String pkcoupon) {
                this.pkcoupon = pkcoupon;
            }

            public double getPayamount() {
                return payamount;
            }

            public void setPayamount(double payamount) {
                this.payamount = payamount;
            }

            public double getMaxpayamount() {
                return maxpayamount;
            }

            public void setMaxpayamount(double maxpayamount) {
                this.maxpayamount = maxpayamount;
            }

            public double getValueamount() {
                return valueamount;
            }

            public void setValueamount(double valueamount) {
                this.valueamount = valueamount;
            }

            public String getStartdate() {
                return startdate;
            }

            public void setStartdate(String startdate) {
                this.startdate = startdate;
            }

            public int getPolicystatus() {
                return policystatus;
            }

            public void setPolicystatus(int policystatus) {
                this.policystatus = policystatus;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }
        }
    }
}
