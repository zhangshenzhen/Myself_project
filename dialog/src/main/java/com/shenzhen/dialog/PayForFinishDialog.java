package com.shenzhen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.shenzhen.payfor.utils.AppInfoUtil;
import com.shenzhen.payfor.utils.DensityUtil;


public class PayForFinishDialog {
    private Context context;
    private String url;
    private int width ;
    private int num;
    public PayForFinishDialog(Context context, int num) {
        this.context = context;
        this.url = url;
        this.num = num;
        width = AppInfoUtil.getScreenWidth(context);
    }


    public  void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
         View v = inflater.inflate(R.layout.payfor_finish_dialog,null);
         ImageView dialog_close = v.findViewById(R.id.dialog_close);
          Button btn_look_for = v.findViewById(R.id.btn_look_for);
        RelativeLayout rel_recv_concent = v.findViewById(R.id.rel_recv_concent);

        final Dialog dialog = builder.create();
        //背景透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(v);
        //dialog设置宽高
       /* WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width - width / 5;//设置宽
        params.height = width +width / 10;//+//dialog_close置底部，这里可以调节高度
        dialog.getWindow().setAttributes(params);
        //设置图片的位置宽高
        RelativeLayout.LayoutParams img_rang = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        img_rang.addRule(RelativeLayout.CENTER_HORIZONTAL);
        img_rang.width = width - width / 4;//设置宽
        img_rang.height = width /5;//设置高
        img_rang.topMargin =  params.height/2 - img_rang.height/2 + DensityUtil.dip2px(context, 5); //为了显示间距
        rel.setLayoutParams(img_rang);*/
        //显示图片
        ViewGroup.LayoutParams relativeLayout = rel_recv_concent.getLayoutParams();
        if (num>=3) {
            relativeLayout.height = DensityUtil.dip2px(context, 260);;
         }
        rel_recv_concent.setLayoutParams(relativeLayout);
        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });



      }



}