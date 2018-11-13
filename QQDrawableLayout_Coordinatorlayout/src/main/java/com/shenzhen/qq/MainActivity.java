package com.shenzhen.qq;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //toolbar点击
        onclick(toolbar );



        //侧滑菜单;
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setCheckedItem(R.id.nav_call);//默认选中的
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                toast("关闭侧边栏。。。^_^");
                navigationView.setCheckedItem(item.getItemId());//选中的
                drawerLayout.closeDrawers();//关闭侧边栏
                return true;
            }
        });

        /*
        开关侧边栏
        * */
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        LinearLayout layout = findViewById(R.id.layout);
        /*DrawerLayout的监听，定制属于我们自己的DrawerLayout，然后完成QQ的侧滑栏功能！
        * 需要内容部分跟着侧滑栏的滑动而滑动，而默认效果是侧滑栏遮住内容部分了，
        * 所以我们要想法拿到内容部分的View，然后再获取到侧滑的偏移量，
        * 然后根据重新计算的偏移量来动态平移内容部分的View，OK来代码实现一下：*/
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //滑动过程中不断回调 slideOffset:0~1
                //drawerLayout的第一个孩子是CoordinatorLayout
                View content = drawerLayout.getChildAt(0);
                View menu = drawerView;
                float scale = 1 - slideOffset;//1~0
                content.setTranslationX(menu.getMeasuredWidth() * (1 - scale));//0~width
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                  toast("打开了侧边栏^_^");
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                  toast("关闭了侧边栏^_^");
            }

            @Override
            public void onDrawerStateChanged(int i) {
            }
        });
       // drawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void toast(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }


    private void onclick(Toolbar toolbar) {
        // 显示导航按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你点击了返回按钮",Toast.LENGTH_SHORT).show();

            }
        });

        //设置菜单第二步：设置菜单按钮
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_wlf:
                        Toast.makeText(MainActivity.this,"中国搏击市场开拓者",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_klf:
                        Toast.makeText(MainActivity.this,"世界斗赛事",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_ufc:
                        Toast.makeText(MainActivity.this,"世界顶级格斗赛事",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_setting:
                        Toast.makeText(MainActivity.this,"扫一扫",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 设置菜单第一步：
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
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
