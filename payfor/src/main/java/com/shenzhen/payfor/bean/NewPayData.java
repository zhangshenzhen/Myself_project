package com.shenzhen.payfor.bean;

public class NewPayData {

    private String msg;
    private String resultStatus;
    private NewPayBean resultData;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public NewPayBean getResultData() {
        return resultData;
    }

    public void setResultData(NewPayBean resultData) {
        this.resultData = resultData;
    }
}
