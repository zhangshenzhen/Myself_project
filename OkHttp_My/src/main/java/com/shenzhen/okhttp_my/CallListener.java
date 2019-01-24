package com.shenzhen.okhttp_my;

public  interface CallListener<Object> {

    void onSuccess(int reqcode, Object result);
    void onFailed(int reqcode, Object result);
}
