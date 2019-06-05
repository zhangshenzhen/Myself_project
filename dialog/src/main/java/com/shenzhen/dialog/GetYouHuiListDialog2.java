package com.shenzhen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenzhen.dialog.decoration.GridSpacingItemDecoration;
import com.shenzhen.dialog.decoration.HorizontalSpaceItemDecoration;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

;


public class GetYouHuiListDialog2 {
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
    private final int width;
    private List<YouHuiQuanBean.YouHuiQuanDataBean> youHuiQuanDataBeanlist;


    public GetYouHuiListDialog2(Context context, List<YouHuiQuanBean.YouHuiQuanDataBean> youHuiQuanDataBeanlist) {

         this.context = context;
         this.youHuiQuanDataBeanlist = youHuiQuanDataBeanlist;
        width = AppInfoUtil.getScreenWidth(context);
    }


    public  void showDialog(){ //替换了布局文件
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.payfor_finish_dialog, null);
        LinearLayout linyout = v.findViewById(R.id.linyout);
        youhui_recv = v.findViewById(R.id.youhui_recv);

        ImageView imageView_top = v.findViewById(R.id.img_top);
        RelativeLayout.LayoutParams reparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        reparams.topMargin = DensityUtil.dip2px(context, 5);
        reparams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageView_top.setLayoutParams(reparams);

        int size = youHuiQuanDataBeanlist.size();//条目个数

        int dianog_contentHeght ;
        int add_width;
        if (size == 1) {
            //dianog_contentHeght = DensityUtil.dip2px(context, 180);
            dianog_contentHeght = DensityUtil.dip2px(context, 225);
            add_width = 0;
        }else if(size==2){
           // dianog_contentHeght = DensityUtil.dip2px(context, 250);
            dianog_contentHeght = DensityUtil.dip2px(context, 295);
            add_width = width /8;
        }else {
           // dianog_contentHeght = DensityUtil.dip2px(context, 330);
            dianog_contentHeght = DensityUtil.dip2px(context, 385);
            add_width = width /4+ DensityUtil.dip2px(context, 45);
        }

       //自适应背景内容区域 移动 （相对性）调整图标的位置
         RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(DensityUtil.dip2px(context, 400), dianog_contentHeght);
         params2.topMargin = DensityUtil.dip2px(context, 150);//80
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
        params.width = width - width / 5 +DensityUtil.dip2px(context, 40);;//设置宽
        //测试屏蔽 // add_width = size > 2 ? width /4+DensityUtil.dip2px(context, 5):width /15;
        params.height = width +add_width+DensityUtil.dip2px(context, 80);//dialog_close置底部，这里可以调节高度
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

       // YouHuiDialogAdapter adapter = new YouHuiDialogAdapter(context,youHuiQuanDataBeanlist,dialog);
       // youhui_recv.setAdapter(adapter);


        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
               // executor.shutdown();
            }
        });

      }
      /*关闭弹框显示*/
      public boolean  closeDialog(){
         if(dialog != null && dialog.isShowing()){
             dialog.dismiss();
             return  true;
         }
         return false;
      }

}