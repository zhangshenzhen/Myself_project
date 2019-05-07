package com.shenzhen.dialog;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.shenzhen.xutilsdemo.inter_face.ResultDataListener;
import com.shenzhen.xutilsdemo.utils.PostGetData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements ResultDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 一 ，
                List<String> list=new ArrayList<>(); for (int i = 0; i<5; i++){
                    list.add(String.valueOf(i));
                }
                ListDialog.createListDialog(MainActivity.this,list,"DataList");

                // 二 ，
                PayForFinishDialog dialog = new PayForFinishDialog(MainActivity.this, 3);
                dialog.showDialog();

                Map<String,String> map = new HashMap<>();
                map.put("pkregister", "4075ffd325fe48c48ed05ee4c0372635");
                map.put("begin", "0");
                map.put("pageLength", 10 + "");
                // map.put("fileName", "http://huiyuanbao.oss-cn-hangzhou.aliyuncs.com/userposition_46b6a19ea3394dbaad777ef4c7a6bfca.jpg");
                String url = "http://123.57.232.188:8080/hyb/ws/coupon/list";
                PostGetData.MethodPost( 110,url,map,MainActivity.this);

            }
        });
    }

    @Override
    public void onSuccess(int i, Object o) {
        Log.d("onSuccess ", "requecode = "+i  +" ------ " + o.toString());

    }

    @Override
    public void onFailar(int i, Object o) {
        Log.d("onFailar ", "requecode = "+i  +" ------ " + o.toString());
    }

 /*移动平均法：是利用平均过程所具有的平滑作用从时间序列数据中去除局部不规则性
 ，排除随机性，从而找出时间序列数据变化趋势的方法
 *
 * 信息分析的特点，1研究课题的针对灵活，研究内容的综合与系统
 * 研究成果智能创造，研究方法的科学与特殊，研究社会性 研究预测与近似
 *
 * 信息分析的作用：：在科学挂那里中发挥参谋价值，研究中担任助手功能，动态的跟踪与预警作用
 * 保障和向导作用；
 *
 * 实际调查具有哪些特征：
 * 针对性强，互动性好，直观性强远原始数据多，内容新，速度快，形式多样，渠道广泛
 *
  方法是：人类认识世界改造世界的思路、途径、程序、方式、

 * */
}
