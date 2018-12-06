package com.shenzhen.payfor.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


import com.shenzhen.payfor.R;


public class GetYouHuiListDialog {
    private Context context;
     int width ;
    public GetYouHuiListDialog(Context context) {
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
         final Dialog dialog = builder.create();
        //背景透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(v);
        //dialog设置宽高
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width - width / 5;//设置宽
        params.height = width +width / 15;//+//dialog_close置底部，这里可以调节高度
        dialog.getWindow().setAttributes(params);

        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

      }



}