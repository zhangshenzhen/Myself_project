package com.shenzhen.payfor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import com.shenzhen.payfor.decoration.GridSpacingItemDecoration;
import com.shenzhen.payfor.decoration.HorizontalSpaceItemDecoration;
import com.shenzhen.payfor.utils.AppInfoUtil;
import com.shenzhen.payfor.utils.DensityUtil;

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
    private RecyclerView youhui_recv;

    public UpgradeDialog(Context context, String url, String content) {
         this.url = url;
         this.context = context;
         this.content = content;
    }

   /*
   * 更新内容的文本*/
/*    private void upDateContent(LinearLayout view){
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
    }*/
    public  void showDialog(){
        int width = AppInfoUtil.getScreenWidth(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.upgrade_dialog, null);
       // LinearLayout liner_conent = v.findViewById(R.id.liner_conent);
        LinearLayout linyout = v.findViewById(R.id.linyout);
        youhui_recv = v.findViewById(R.id.youhui_recv);
        int size = 3;//条目个数

        int dianog_contentHeght = size>2? DensityUtil.dip2px(context, 330):DensityUtil.dip2px(context, 250);
       //自适应图标距离顶部的位置
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(DensityUtil.dip2px(context, 400), dianog_contentHeght);
        params2.topMargin = DensityUtil.dip2px(context, 80);
        linyout.setLayoutParams(params2);


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
        int add_width = size > 2 ? width /4+DensityUtil.dip2px(context, 5):width /15;
        params.height = width +add_width;//dialog_close置底部，这里可以调节高度
       // params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);
        //设置间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(context, 5));
        HorizontalSpaceItemDecoration horizontalSpaceItemDecoration = new HorizontalSpaceItemDecoration(DensityUtil.dip2px(context, 10), DensityUtil.dip2px(context, 0));
        youhui_recv.removeItemDecoration(horizontalSpaceItemDecoration);
        youhui_recv.addItemDecoration(gridSpacingItemDecoration);



        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        youhui_recv.setLayoutManager(layoutManager);//设置布局管理器

        YouHuiAdapter adapter = new YouHuiAdapter(context,size);
        youhui_recv.setAdapter(adapter);



        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
               // executor.shutdown();
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