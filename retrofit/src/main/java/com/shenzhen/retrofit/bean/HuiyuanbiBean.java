package com.shenzhen.retrofit.bean;

public class HuiyuanbiBean {

    /**
     * resultStatus : 0
     * msg : SUCCESS
     * resultData : {"pkregister":"360e8ac8d5604593940271bfb8848612","amount":0.01,"sourcepk":"047561f6bc7141eb8c9fff8626c18fb1","pkmuser":"a16f983b5fe841b6bffb166908707449","totalCount":"556.90","virtualBalance":"855.11","status":1,"orderExchangeBalance":0}
     */

    private int resultStatus;
    private String msg;
    private ResultDataBean resultData;

   /* @Override
    public String toString() {
        return "HuiyuanbiBean{" +
                "resultStatus=" + resultStatus +
                ", msg='" + msg + '\'' +
                ", resultData=" + resultData +
                '}';
    }*/

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
         * pkregister : 360e8ac8d5604593940271bfb8848612
         * amount : 0.01
         * sourcepk : 047561f6bc7141eb8c9fff8626c18fb1
         * pkmuser : a16f983b5fe841b6bffb166908707449
         * totalCount : 556.90
         * virtualBalance : 855.11
         * status : 1
         * orderExchangeBalance : 0
         */

        private String pkregister;
        private double amount;
        private String sourcepk;
        private String pkmuser;
        private String totalCount;
        private String virtualBalance;
        private int status;
        private int orderExchangeBalance;

      /*  @Override
        public String toString() {
            return "ResultDataBean{" +
                    "pkregister='" + pkregister + '\'' +
                    ", amount=" + amount +
                    ", sourcepk='" + sourcepk + '\'' +
                    ", pkmuser='" + pkmuser + '\'' +
                    ", totalCount='" + totalCount + '\'' +
                    ", virtualBalance='" + virtualBalance + '\'' +
                    ", status=" + status +
                    ", orderExchangeBalance=" + orderExchangeBalance +
                    '}';
        }
*/
        public String getPkregister() {
            return pkregister;
        }

        public void setPkregister(String pkregister) {
            this.pkregister = pkregister;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getSourcepk() {
            return sourcepk;
        }

        public void setSourcepk(String sourcepk) {
            this.sourcepk = sourcepk;
        }

        public String getPkmuser() {
            return pkmuser;
        }

        public void setPkmuser(String pkmuser) {
            this.pkmuser = pkmuser;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getVirtualBalance() {
            return virtualBalance;
        }

        public void setVirtualBalance(String virtualBalance) {
            this.virtualBalance = virtualBalance;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOrderExchangeBalance() {
            return orderExchangeBalance;
        }

        public void setOrderExchangeBalance(int orderExchangeBalance) {
            this.orderExchangeBalance = orderExchangeBalance;
        }
    }
}
