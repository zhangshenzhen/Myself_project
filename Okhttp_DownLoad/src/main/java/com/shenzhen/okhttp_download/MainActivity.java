package com.shenzhen.okhttp_download;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shenzhen.okhttp_download.utils.OkHttpUtils;
import com.shenzhen.okhttp_download.utils.ProgessListener;



import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
//  佛主保佑，永無bug
public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity数据";
    private ProgressBar download_progress;
    private TextView download_text;

    //https://www.jb51.net/article/117675.htm
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        download_progress = findViewById(R.id.download_progress);
        download_text = findViewById(R.id.download_text);
          
        findViewById(R.id.ok_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/VipCard.apk
                String url = "http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/VipCard.apk";
                final String fileName = url.split("/")[url.split("/").length - 1];
                String localPath = Environment.getExternalStorageDirectory().getPath();
                OkHttpUtils.downLoadFile(url, localPath, fileName, new ProgessListener() {
                    @Override
                    public void onProgress(long currentBytes, long contentLength, boolean done) {
                        Log.i(TAG,"currentBytes : "+ currentBytes +" contentLength : "+ contentLength);
                        int progess = (int) (currentBytes*100/contentLength);
                        download_progress.setProgress(progess);
                        download_text.setText(progess+"%");
                    }

                    @Override
                    public void onSuecess(File file) {
                        Log.i(TAG,"file : "+ file.getPath());
                        //覆盖替换安装新版本
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
                        MainActivity.this.startActivity(intent);
                    }

                    @Override
                    public void onFailer(Call call, IOException e) {
                        Log.i(TAG,"IOException : "+ e.getMessage());
                    }
                    @Override
                    public void onError(Call call, Response response) {

                    }

                });

            }
        });

    }




}
