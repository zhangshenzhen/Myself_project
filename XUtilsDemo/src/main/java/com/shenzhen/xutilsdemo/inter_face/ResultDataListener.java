package com.shenzhen.xutilsdemo.inter_face;

public interface ResultDataListener {

    void onSuccess(int requestcode,Object result);
    void onFailar(int requestcode,Object result);
}
