package com.shenzhen.payfor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import com.shenzhen.payfor.utils.AppInfoUtil;

import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;



public class UpgradeDialog {
    private ScheduledThreadPoolExecutor executor;
    private ProgressBar pb_progess;
    private Context context;
    private String url;
    private Dialog dialog;
    int second = 0;
    private TextView tv_pencent;
    private String content;
    private String[] stringArr;

    public UpgradeDialog(Context context, String url, String content) {
         this.url = url;
         this.context = context;
         this.content = content;
    }

   /*
   * 更新内容的文本*/
    private void upDateContent(LinearLayout view){
        if (!content.equals("")){
            view.setGravity(Gravity.VERTICAL_GRAVITY_MASK);
            view.removeAllViews();
            stringArr = content.split(";");
            for (int i =0 ; i<stringArr.length;i++){
                 TextView textView = new TextView(context);
                 textView.setText("");
                 textView.setTextSize(3);
                 view.addView(textView);
                 TextView tv = new TextView(context);
                 tv.setText(stringArr[i]);
                 view.addView(tv);
            }
         }
    };
    public  void showDialog(){
        int width = AppInfoUtil.getScreenWidth(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.upgrade_dialog, null);
        LinearLayout liner_conent = v.findViewById(R.id.liner_conent);
        upDateContent(liner_conent);//更新的文本内容
        Button btn_sure = v.findViewById(R.id.btn_sure);
        tv_pencent = v.findViewById(R.id.tv_pencent);
        pb_progess = v.findViewById(R.id.progress_bar);
        ImageView dialog_close = v.findViewById(R.id.dialog_close);
        dialog = builder.create();
        //背景透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(v);
        //dialog设置宽高
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width - width / 5;//设置宽
        params.height = width - width /20;//dialog_close置底部，这里可以调节高度
       // params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);
        pb_progess.setProgress(0);
        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                executor.shutdown();
            }
        });
        executor = new ScheduledThreadPoolExecutor(2);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // executor.scheduleAtFixedRate(runnable,100,100,TimeUnit.MILLISECONDS);
                executor.execute(runnable);
            }
        });
  }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            com.lidroid.xutils.HttpUtils httpUtils = new com.lidroid.xutils.HttpUtils();
            final File sdDir = Environment.getExternalStorageDirectory();
            if (sdDir.exists()){//存在sd卡文件
                String apkName = url.substring(url.lastIndexOf("/")+1,url.length());
                File file = new File(sdDir.getPath()+"/DownLoad", apkName);
                httpUtils.download(url, file.getAbsolutePath(), new RequestCallBack<File>() {
                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                       //下载完成
                        executor.shutdown();
                        dialog.dismiss();
                        //覆盖替换安装新版本
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.setDataAndType(Uri.fromFile(responseInfo.result),"application/vnd.android.package-archive");
                        context.startActivity(intent);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                         int pencent = (int) ((current*100)/total);
                         pb_progess.setProgress(pencent);
                         tv_pencent.setText(pencent+"%");
                    }
                    @Override
                    public void onFailure(HttpException e, String s) {
                       // LogUtil.print(sdDir.getAbsolutePath()+"升级:"+e.getMessage());
                    }
                });
            }
        }
    };
}