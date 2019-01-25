package com.shenzhen.okhttp_download.utils;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface ProgessListener {
    //已完成的 总的文件长度 是否完成
    void onProgress(long currentBytes, long contentLength, boolean done);
    void onSuecess(File file);
    void onFailer(Call call, IOException e);
    void onError(Call call, Response response);

}
