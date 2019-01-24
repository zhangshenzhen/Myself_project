package com.shenzhen;

public  interface CallListener<Object> {

    void onSuccess(int reqcode, Object result);
    void onFailed(int reqcode,Object result);
}
