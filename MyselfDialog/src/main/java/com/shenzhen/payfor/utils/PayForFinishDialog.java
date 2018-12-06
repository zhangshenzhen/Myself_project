package com.shenzhen.payfor.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shenzhen.payfor.R;


public class PayForFinishDialog {
    private Context context;
     int width ;
    public PayForFinishDialog(Context context) {
        this.context = context;
        width = AppInfoUtil.getScreenWidth(context);
    }


    public  void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.payfor_finish_dialog,null);

       /*  int dianog_contentHeght = size>2? DensityUtil.dip2px(context, 330):DensityUtil.dip2px(context, 250);
        //自适应图标距离顶部的位置
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(DensityUtil.dip2px(context, 400), dianog_contentHeght);
         params2.topMargin = DensityUtil.dip2px(context, 80);
         linyout.setLayoutParams(params2);
       */

         ImageView dialog_close = v.findViewById(R.id.dialog_close);
         Button btn_look_for = v.findViewById(R.id.btn_look_for);
         RelativeLayout rel = v.findViewById(R.id.rel);


         final Dialog dialog = builder.create();
        //背景透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(v);
        //dialog设置宽高
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width - width / 5;//设置宽
        params.height = width +width / 10;//+//dialog_close置底部，这里可以调节高度
        dialog.getWindow().setAttributes(params);

        RelativeLayout.LayoutParams img_rang = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        img_rang.addRule(RelativeLayout.CENTER_HORIZONTAL);
        img_rang.width = width - width / 4;//设置宽
        img_rang.height = width /5;//设置高
        img_rang.topMargin =  params.height/2 - img_rang.height/2 +DensityUtil.dip2px(context, 5); //为了显示间距
        rel.setLayoutParams(img_rang);
        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_look_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

      }



}