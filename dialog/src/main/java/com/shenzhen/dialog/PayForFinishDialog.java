package com.shenzhen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


import com.shenzhen.dialog.adapter.YouHuiDialogAdapter;
import com.shenzhen.dialog.decoration.GridSpacingItemDecoration;
import com.shenzhen.dialog.decoration.HorizontalSpaceItemDecoration;


import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;


public class PayForFinishDialog {
    private Context context;
    private Dialog dialog;
    int second = 0;
    private TextView tv_pencent;
    private String content;
    private String[] stringArr;
    private RecyclerView youhui_recv;
    private final int width;
    private List<String> youHuiQuanDataBeanlist;
    private int num;

    public PayForFinishDialog(Context context, int num) {
        this.context = context;
        this.num = num;
        width = AppInfoUtil.getScreenWidth(context);
    }


    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.payfor_finish_dialog, null);
        LinearLayout linyout = v.findViewById(R.id.linyout);
        youhui_recv = v.findViewById(R.id.youhui_recv);

        //
       /*ImageView imageView_top = v.findViewById(R.id.img_top);
        RelativeLayout.LayoutParams reparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        reparams.topMargin = DensityUtil.dip2px(context, 5);
        reparams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageView_top.setLayoutParams(reparams);*/

        //int size = youHuiQuanDataBeanlist.size();//条目个数
        int size = num;
        int dianog_contentHeght;
       if (size >= 3) { //限定内容区域的大小
            dianog_contentHeght = DensityUtil.dip2px(context, 390);
            //自适应背景内容区域 移动 （相对性）调整图标的位置
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, dianog_contentHeght);
            params2.topMargin = DensityUtil.dip2px(context, 150);
            params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
            linyout.setLayoutParams(params2);
        }


        ImageView dialog_close = v.findViewById(R.id.dialog_close);
        dialog = builder.create();
        //背景透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(v);
         //dialog设置宽高
       /* WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width - width / 5 +DensityUtil.dip2px(context, 40);;//设置宽
        params.height = width +add_width+DensityUtil.dip2px(context, 80);//dialog_close置底部，这里可以调节高度
        dialog.getWindow().setAttributes(params);*/
        //设置间距
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(1, DensityUtil.dip2px(context, 5));
        HorizontalSpaceItemDecoration horizontalSpaceItemDecoration = new HorizontalSpaceItemDecoration(DensityUtil.dip2px(context, 10), DensityUtil.dip2px(context, 0));
        youhui_recv.removeItemDecoration(horizontalSpaceItemDecoration);
        youhui_recv.addItemDecoration(gridSpacingItemDecoration);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        youhui_recv.setLayoutManager(layoutManager);//设置布局管理器

        YouHuiDialogAdapter adapter = new YouHuiDialogAdapter(context, num, dialog);
        youhui_recv.setAdapter(adapter);


        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                // executor.shutdown();
            }
        });

    }
    /*关闭对话框
    * */
    public boolean closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            return true;
        }
        return false;
    }

}