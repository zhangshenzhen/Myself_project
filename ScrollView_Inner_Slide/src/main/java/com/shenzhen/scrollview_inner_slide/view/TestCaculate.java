package com.shenzhen.scrollview_inner_slide.view;

import android.content.Context;
import android.widget.Toast;

public class TestCaculate {

    public int a = 10;
    public int b = 1;//已经修复

    public void caculate(Context context) { //测试热修复功能
        Toast.makeText(context, "结果" + a / b, Toast.LENGTH_SHORT).show();
    }

}
