package com.shenzhen.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shenzhen.dialog.adapter.YouHuiDialogAdapter;


import java.util.List;

public class ListDialog {

    public static void createListDialog(Context context, List<String> list, String title) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //获取屏幕宽度
        int width = metrics.widthPixels;
        //dp转换为px
        int margin = (int) (10 * (context.getResources().getDisplayMetrics().density) + 0.5f);
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        View view = View.inflate(context, R.layout.layout_list_dialog, null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        ListView lv = view.findViewById(R.id.lv);
         //限定内容大小,
        if(list.size()>3){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 200));
        lv.setLayoutParams(layoutParams);
        }

        dialog.show();
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams windowParams = window.getAttributes();
            //设置Dialog宽高
            windowParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            windowParams.width = width - 2 * margin;
            window.setAttributes(windowParams);
        }

        tv_title.setText(title); //设置数据
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

    }

}
