package com.shenzhen.qqslidingmenue;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
   private  SlidingMenue sliding_menu;
    private LinearLayout ll_menu;
    private TextView tv_news;
    private Toolbar toolbar;

    // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toobar);
        //把布局中的Toolbar当作ActionBar
        setSupportActionBar(toolbar);
        //设置图标
        toolbar.setLogo(R.mipmap.ic_launcher);
        //设置标题 二选一
        getSupportActionBar().setTitle("shenzhen");
        toolbar.setTitle("zhangshenzhen");
        //标题颜色
        toolbar.setTitleTextColor(Color.parseColor("#ff0000"));
        //设置副标题
        toolbar.setSubtitle(fomartdate(System.currentTimeMillis()+""));
        //设置返回键 二选一
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回按钮
        toolbar.setNavigationIcon(R.mipmap.back);
         setOnClick(toolbar);
        //布局
        sliding_menu = (SlidingMenue) findViewById(R.id.sliding_menu);;
        ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
        tv_news = (TextView) findViewById(R.id.tv_news);
         setCurrentSelectedMenuItem(tv_news);
    }

    /*开关按钮*/
    private void setOnClick(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你点击了返回按钮",Toast.LENGTH_SHORT).show();
               sliding_menu.toggle();
            }
        });
    }

    public void onMenuItemClick(View v){
        TextView textView = (TextView) v;
        //textView.getText() 获取条目的文本名称
        Toast.makeText(this ,textView.getText(), Toast.LENGTH_SHORT).show();
        setCurrentSelectedMenuItem(v);
    }
    /** 设置当前选择的菜单Item */
    private void setCurrentSelectedMenuItem(View menuItem) {
        for (int i = 0; i < ll_menu.getChildCount(); i++) {
            View child = ll_menu.getChildAt(i);
            child.setSelected(child == menuItem);
        }
    }
    /**
     * 主界面的点击按钮;
     * @param v
     */
      public  void  onMenuClick(View v){
        sliding_menu.toggle();

     }

    /*格式化时间
     *
     * */
    private  String fomartdate(String time){
        long t = Long.parseLong(time);
        Date date = new Date(t);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//yyyy.MM.dd HH:mm:ss
        return format.format(date)+"";
    }
}
