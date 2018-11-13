package com.shenzhen.qqsliding;

import android.animation.FloatEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ListView menulistview;
    private ListView mainlistview;
    private SlideMenu slidemenu;
    private ImageView iv_head;
    private ImageView iv_menu_head;
    //浮点计算
    private FloatEvaluator floatEvaluator = new FloatEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menulistview = (ListView) findViewById(R.id.menu_listview);
        mainlistview = (ListView) findViewById(R.id.main_listview);
        slidemenu = (SlideMenu) findViewById(R.id.slide_menu);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_menu_head = (ImageView) findViewById(R.id.iv_menu_head);
        //填充数据
        mainlistview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Constant.NAMES));
        menulistview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Constant.sCheeseStrings) {
            //设置字体颜色
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(Color.WHITE);
                return tv;
            }
        });
        //动画动作监听
        slidemenu.setOnSlideListener(new SlideMenu.OnSlideListener() {
            //@Override
            public void onOpen() {
                Toast.makeText(MainActivity.this, "打开", Toast.LENGTH_SHORT).show();
                //随机到某一个条目
                menulistview.smoothScrollToPosition(new Random().nextInt(Constant.sCheeseStrings.length));
                mainlistview.setOnTouchListener(new View.OnTouchListener() {
                    //@Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;  //关闭状态拦截;
                    }
                });
            }

            // @Override
            public void OnClose() {
                Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                ViewCompat.animate(iv_head).translationX(35)
                        .setInterpolator(new CycleInterpolator(5)).setDuration(600).start();
                mainlistview.setOnTouchListener(new View.OnTouchListener() {
                    //@Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;  //关闭状态拦截  false 代表打开后仍可滑动主界面列表;
                    }
                });
            }

            //@Override
            public void OnDraging(float fraction) {
                iv_head.setAlpha(1 - fraction);//界面移动过程头像变透明
                iv_head.setRotationY(floatEvaluator.evaluate(fraction, 0, 1080));//界面移动过程头像变旋转
                // iv_head.setRotation(floatEvaluator.evaluate(fraction , 0,1080));

                iv_menu_head.setAlpha(fraction);//界面移动过程头像变透明
                iv_menu_head.setRotationY(floatEvaluator.evaluate(fraction, 0, 1080));//界面移动过程头像变旋转

            }
        });

    }

}