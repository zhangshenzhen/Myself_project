package com.shenzhen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;


public class GetSuccessDialog {
    private Context context;
    private Dialog dialog;

    public GetSuccessDialog(Context context) {
         this.context = context;
         
    }


    public  void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
         View v = inflater.inflate(R.layout.success_dialog,null);
           dialog = builder.create();
        //背景透明
         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
         dialog.setCanceledOnTouchOutside(false);
         dialog.show();

         WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
         params.width = DensityUtil.dip2px(context, 150);
         params.height = DensityUtil.dip2px(context, 60);
         dialog.getWindow().setAttributes(params);
         dialog.getWindow().setContentView(v);
         handler.sendEmptyMessageDelayed(1,2000);

      }

     public Handler handler = new Handler(){
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             if (dialog != null &&dialog.isShowing()){
               dialog.dismiss();
             }
         }
     };
}