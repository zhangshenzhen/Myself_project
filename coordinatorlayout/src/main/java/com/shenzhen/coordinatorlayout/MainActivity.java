package com.shenzhen.coordinatorlayout;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity.position" ;
    private Toolbar toolbar;
    public int fp = -1;

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
       // drawerLayout.openDrawer(Gravity.LEFT);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        LinearLayout layout = findViewById(R.id.layout);
        //layout.setBackgroundColor(Color.TRANSPARENT);//透明
        init();
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
                        Toast.makeText(MainActivity.this,"世界顶级站立式格斗赛事",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_ufc:
                        Toast.makeText(MainActivity.this,"世界顶级无限制格斗赛事",Toast.LENGTH_SHORT).show();
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


     List<Pair<String, Fragment>> items;
    private  TabLayout tab;
    private ViewPager viewPager;

    private void init() {
        tab = (TabLayout)findViewById(R.id.tab);
         viewPager = findViewById(R.id.vpager);
        items = new ArrayList<>();
        items.add(new Pair<String, Fragment>("第一个", new MyFragment()));
        items.add(new Pair<String, Fragment>("第二个", new MyFragment()));
        items.add(new Pair<String, Fragment>("第三个", new MyFragment()));
        items.add(new Pair<String, Fragment>("第四个", new MyFragment()));
        items.add(new Pair<String, Fragment>("第五个", new MyFragment()));
        items.add(new Pair<String, Fragment>("第六个", new MyFragment()));


        viewPager.setAdapter(new MainAdapter(this.getSupportFragmentManager()));
        tab.setupWithViewPager(viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.i(TAG,"Scrolled:= "+i);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG,"Selected:= "+position);
                if (position ==0){
                    isShow = false;
                }else {
                    isShow = true;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.i(TAG,"StateChanged:= "+i);
            }
        });
    }

    boolean isShow;
    private class MainAdapter extends FragmentPagerAdapter {

        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            fp = position;
            Log.i(TAG,".........."+fp);
            return items.get(position).second;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position).first;
        }

    }


}
