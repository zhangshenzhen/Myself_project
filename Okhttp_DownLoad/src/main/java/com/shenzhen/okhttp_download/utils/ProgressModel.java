package com.shenzhen.okhttp_download.utils;

class ProgressModel {

    private long currentBytes;
    private long contentLength;
    private boolean isDown;

    public ProgressModel(long currentBytes, long contentLength, boolean isDown) {
        this.currentBytes = currentBytes;
        this.contentLength = contentLength;
        this.isDown = isDown;
    }

    public long getCurrentBytes() {
        return currentBytes;
    }

    public long getContentLength() {
        return contentLength;
    }

    public boolean isDown() {
        return isDown;
    }


}
