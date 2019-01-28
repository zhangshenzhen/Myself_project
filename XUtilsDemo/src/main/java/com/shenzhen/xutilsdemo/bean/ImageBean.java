package com.shenzhen.xutilsdemo.bean;

public class ImageBean {

    /**
     * resultStatus : 0
     * msg : SUCCESS
     * resultData : {"fileName":"http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_a235b3bbcf5d42a1bb17afb23fbfdc0e.jpg"}
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
         * fileName : http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_a235b3bbcf5d42a1bb17afb23fbfdc0e.jpg
         */

        private String fileName;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }
}
