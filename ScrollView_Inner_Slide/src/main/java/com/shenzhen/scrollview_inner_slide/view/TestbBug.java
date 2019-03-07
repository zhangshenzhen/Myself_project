package com.shenzhen.scrollview_inner_slide.view;


import android.content.Context;
import android.widget.Toast;

public class TestbBug {

   public  String st;

    public void bug(Context context) { //测试热修复功能
        boolean b = st.equals("st");
        Toast.makeText(context, "is "+ b, Toast.LENGTH_SHORT).show();
    }

}
