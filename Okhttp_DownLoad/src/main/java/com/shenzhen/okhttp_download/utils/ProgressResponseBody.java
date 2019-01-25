package com.shenzhen.okhttp_download.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {
    public static final int UPDATE = 0x01;
    public static final String TAG = ProgressResponseBody.class.getName();
    private ResponseBody responseBody;
    private ProgessListener mListener;
    private BufferedSource bufferedSource;
    private Handler myHandler;

    public ProgressResponseBody(ResponseBody body, ProgessListener listener) {
        responseBody = body;
        mListener = listener;
        if (myHandler == null) {
            myHandler = new MyHandler();
        }
    }


    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null){
            bufferedSource = Okio.buffer(mySource(responseBody.source()));
        }
        return bufferedSource;
    }

    /*
     * 将进度放到主线程*/

    class MyHandler extends Handler {
        public MyHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE:
                    ProgressModel progressModel = (ProgressModel) msg.obj;
                    //接口返回
                    if (mListener!=null)mListener.onProgress(progressModel.getCurrentBytes(),progressModel.getContentLength(),progressModel.isDown());
                    break;

            }
        }
    }

    private Source mySource(Source source) {

        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            @Override
            public long read(Buffer sink, long byteCount) {
                long bytesRead = 0;
                try {
                  bytesRead = super.read(sink, byteCount);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                totalBytesRead +=bytesRead!=-1?bytesRead:0;
                //发送消息到主线程，ProgressModel为自定义实体类
                Message msg = Message.obtain();
                msg.what = UPDATE;
                msg.obj = new ProgressModel(totalBytesRead,contentLength(),totalBytesRead==contentLength());
                myHandler.sendMessage(msg);

                return bytesRead;
            }
        };
    }
}
