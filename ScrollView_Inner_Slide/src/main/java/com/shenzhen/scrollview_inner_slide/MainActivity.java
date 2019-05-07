package com.shenzhen.scrollview_inner_slide;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.scrollview_inner_slide.adapter.BannerPager;
import com.shenzhen.scrollview_inner_slide.adapter.FirstHorizatalAdapter;
import com.shenzhen.scrollview_inner_slide.adapter.InnGridViewAdapter;
import com.shenzhen.scrollview_inner_slide.adapter.ListTestAdapter;
import com.shenzhen.scrollview_inner_slide.fragment.MyFragment;
import com.shenzhen.scrollview_inner_slide.holder.CustomSwipeRefreshLayout;
import com.shenzhen.scrollview_inner_slide.utils.AppInfoUtil;
import com.shenzhen.scrollview_inner_slide.utils.DensityUtil;
import com.shenzhen.scrollview_inner_slide.utils.HotFix;
import com.shenzhen.scrollview_inner_slide.view.FixedSpeedScroller;
import com.shenzhen.scrollview_inner_slide.view.TestCaculate;
import com.shenzhen.scrollview_inner_slide.view.TestbBug;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ActivityCompat.OnRequestPermissionsResultCallback {

    private List<Integer> listpicture;
    private List<Integer> viewpagerpicture;
    private RecyclerView rev1;
    private RecyclerView rev2;
    private ViewPager vp;
    private int currentItem;
    private boolean isLoop = true;
    private ListView list_item;
    private Timer timer;
    private TextView tv_more;
    private ProgressBar pb;
    private int num = 16;
    private ListTestAdapter listadapter;
    private NestedScrollView nestedScrollview;
    //private SwipeRefreshLayout swipe_layout;
    private CustomSwipeRefreshLayout swipe_layout;
    private String TAG = "Refresh";
    private TabLayout tab;
    private ViewPager vpager_fragment;
    private List<Pair<String, Fragment>> items;
    private boolean isjishu = true;
    private int currentposition;
    private LinearLayout liner_indicate;
    private int pointDis;
    private ImageView img_blue_move;
    private boolean isNoDta;
    private int REQUEST_CODE = 100;
    String[] permissions = new String[]{Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            test(); //测试热修复的功能，与 下面其他代码无关
            switch (msg.what) {
                case 1:
                    if (num <= 30) { //限制数据
                        if (listadapter != null) {
                            listadapter.updateCount(num);
                            pb.setVisibility(View.GONE);
                            tv_more.setVisibility(View.VISIBLE);
                            isNoDta = false;//或许还有数据，有上拉加载的必要性

                        }
                    } else { //手动点击加载也应该禁止掉，设置不可点击了
                        pb.setVisibility(View.GONE);
                        tv_more.setVisibility(View.VISIBLE);
                        tv_more.setText("没有更多数据了。。。");
                        isNoDta = true;//没有数据，不要上拉加载了
                        //点击事件也应禁止
                        tv_more.setEnabled(false);

                    }
                    break;
                case 2:
                    isLoop = true;//防止是触摸Vpager 区域下拉刷新的
                    swipe_layout.setRefreshing(false);
                    swipe_layout.forbidenSlide(false); //恢复可以滑动
                    break;

            }
        }
    };
    private GridView igv_innerGridView;
    private List<Integer> girdViewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//控件
        initData();//数据
        initListener();//监听事件

        if (Build.VERSION.SDK_INT >= 23) {
            checkAppPermission();
        }

    }

    /*//使用了热修复
      //https://blog.csdn.net/hq942845204/article/details/81044158
    * */
    private void fix() {
        try {
            String dexPath = Environment.getExternalStorageDirectory() + "/classes2.dex"; //需要读取权限
            HotFix.patch(this, dexPath, "com.shenzhen.scrollview_inner_slide");
            Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "修复失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void checkAppPermission() {
        //2、创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
        List<String> mPermissionList = new ArrayList<>();
        mPermissionList.clear();//清空没有通过的权限
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限 } }
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {
            //有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
        }
    }


    private void initView() {
        nestedScrollview = findViewById(R.id.nested_Scrollview);
        swipe_layout = findViewById(R.id.swipe_layout);
        tab = findViewById(R.id.tab);//标签
        vpager_fragment = findViewById(R.id.vpager_fragment);

        /*横向
         * */
        rev2 = findViewById(R.id.rev2);

        /*纵向*/
        rev1 = findViewById(R.id.rev1);
        rev1.setNestedScrollingEnabled(false); //放弃自己的滑动交给外部处理


        /*ViewPager*/
        vp = findViewById(R.id.vp);
        //指示器的容器动态填充
        liner_indicate = findViewById(R.id.liner_indicate);


        list_item = findViewById(R.id.list_View);
        // list_item.setNestedScrollingEnabled(false);

        igv_innerGridView = findViewById(R.id.Igv_GridView);

    }

    /*指示器*/
    private void indecateCount(LinearLayout liner_indicate) {
        //移动的原点
        img_blue_move = findViewById(R.id.img_blue);
        //1 个数和vp个数一样
        for (int i = 0; i < viewpagerpicture.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.shape_yellow);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i >= 1) { //从第二个小圆点开始设置左侧边距
                params.leftMargin = DensityUtil.dip2px(this, 10);
            }
            imageView.setLayoutParams(params);
            liner_indicate.addView(imageView);
        }
        //利用视图树观察者  计算点之间距离
        ViewTreeObserver viewTreeObserver = liner_indicate.getViewTreeObserver();
        ViewTreeListener treeListener = new ViewTreeListener(liner_indicate);
        viewTreeObserver.addOnPreDrawListener(treeListener);
    }

    public class ViewTreeListener implements ViewTreeObserver.OnPreDrawListener {
        private LinearLayout liner_indicate;

        public ViewTreeListener(LinearLayout liner_indicate) {
            this.liner_indicate = liner_indicate;
        }

        @Override
        public boolean onPreDraw() {
            int pointSecond = liner_indicate.getChildAt(1).getLeft();//获取线性布局中的第二个小圆点
            int pointFirst = liner_indicate.getChildAt(0).getLeft();//获取线性布局中的第一个小圆点
            //原点边距距离
            pointDis = pointSecond - pointFirst;

            Log.i("圆点间距", "showButtomDialog: " + pointDis);
            liner_indicate.getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        }
    }


    /*
    播放下一个
    * */
    private void nextItem() {
        currentItem++;
        vp.setCurrentItem(currentItem);

    }

    /*数据*/
    private void initData() {
        listpicture = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listpicture.add(R.mipmap.pic6);
            listpicture.add(R.mipmap.pic2);
            listpicture.add(R.mipmap.pic4);
            listpicture.add(R.mipmap.pic5);
        }

        //viewpager 添加数据
        viewpagerpicture = new ArrayList<>();
        viewpagerpicture.add(R.mipmap.pic2);
        viewpagerpicture.add(R.mipmap.pic3);
        viewpagerpicture.add(R.mipmap.pic4);
        viewpagerpicture.add(R.mipmap.pic5);
        viewpagerpicture.add(R.mipmap.pic6);
        viewpagerpicture.add(R.mipmap.pic8);
        indecateCount(liner_indicate);//指示器

        /*横向 单行*/
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置Manager 和横向 两行
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        rev2.setLayoutManager(manager);
        rev2.setAdapter(new FirstHorizatalAdapter(this, listpicture));

        /*纵行 单列 */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置Manager 和纵向 两行
        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rev1.setLayoutManager(manager2);
        rev1.setAdapter(new FirstHorizatalAdapter(this, listpicture));

        /*
         * ViewPager*/
        vp.setAdapter(new BannerPager(MainActivity.this, viewpagerpicture));
        vp.setCurrentItem(0);
        //控制ViewPager 切换速度的 但是影响手动滑动速度
      /* try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(vp.getContext(), new AccelerateInterpolator());
            field.set(vp, scroller);
            //切换速度,
            scroller.setmDuration(800);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/

        try {  //不影响向手动滑动
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            Scroller scroller = new Scroller(this, (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    super.startScroll(startX, startY, dx, dy, duration * 10);    // 这里是关键，将duration变长或变短

                }
            };
            scrollerField.set(vp, scroller);
        } catch (NoSuchFieldException e) {
            // Do nothing.
        } catch (IllegalAccessException e) {
            // Do nothing.
        }


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("", "runtask " + currentItem);
                if (isLoop) {
                    runOnUiThread(new Runnable() {//主线程
                        @Override
                        public void run() {
                            nextItem();
                        }
                    });
                }
            }
        }, 3000, 3000);

        /*纵向listView*/
        /*添加叫布局*/
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.foot, null);
        tv_more = view.findViewById(R.id.tv_more);
        tv_more.setOnClickListener(this);
        pb = view.findViewById(R.id.pb);
        listadapter = new ListTestAdapter(this, num);
        list_item.setAdapter(listadapter);
        // view.setPadding(0, AppInfoUtil.getScreenHeight(MainActivity.this),AppInfoUtil.getScreenWidth(MainActivity.this),
        //  AppInfoUtil.getScreenHeight(MainActivity.this)+view.getMeasuredHeight());
        list_item.addFooterView(view);

        girdViewList = new ArrayList<>();
        girdViewList.clear();
        for (int i = 0; i <9 ; i++) {
            girdViewList.add(R.mipmap.pic6);
        }
        InnGridViewAdapter gridViewAdapter = new InnGridViewAdapter(this,girdViewList);
        igv_innerGridView.setAdapter(gridViewAdapter);
        //Viewpager+Fragment
        items = new ArrayList<>();
        items.add(new Pair<String, Fragment>("第一个", MyFragment.getInstance(0)));
        items.add(new Pair<String, Fragment>("第二个", MyFragment.getInstance(1)));
        items.add(new Pair<String, Fragment>("第三个", MyFragment.getInstance(2)));
        items.add(new Pair<String, Fragment>("第四个", MyFragment.getInstance(3)));
        items.add(new Pair<String, Fragment>("第五个", MyFragment.getInstance(4)));
        items.add(new Pair<String, Fragment>("第六个", MyFragment.getInstance(5)));
        items.add(new Pair<String, Fragment>("第七个", MyFragment.getInstance(2)));
        items.add(new Pair<String, Fragment>("第八个", MyFragment.getInstance(3)));
        items.add(new Pair<String, Fragment>("第九个", MyFragment.getInstance(4)));
        items.add(new Pair<String, Fragment>("第十个", MyFragment.getInstance(5)));

        vpager_fragment.setAdapter(new MainAdapter(this.getSupportFragmentManager()));
        tab.setupWithViewPager(vpager_fragment);
        vpager_fragment.setOffscreenPageLimit(items.size());
        vpager_fragment.setCurrentItem(0);
        vpager_fragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.i(TAG, "Scrolled:= " + i + " ,float :" + v + ", i1 :" + i1);
            }

            @Override
            public void onPageSelected(int position) {
                currentposition = position;
                Log.i(TAG, "Selected。。:= " + position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

                Log.i(TAG, "StateChanged。。:= " + i);
            }
        });

    }

    /*监听事件*/
    private void initListener() {
        /*SwipeLayout*/
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "下拉刷新。。。");
                // swipe_layout.setEnabled(false);
                swipe_layout.forbidenSlide(true);
                //延时 假装加载数据
                handler.sendEmptyMessageDelayed(2, 3000);
            }
        });
        /*NestedScrollview
         * */
        nestedScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestscView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) { // 向下滑动
                    Log.i(TAG, "向上滑");
                }
                if (scrollY < oldScrollY) { // 向上滑动
                    Log.i(TAG, "向下滑");
                }
                if (scrollY == 0) { // 顶部
                    Log.i(TAG, "顶部");
                }
                if (scrollY == (nestscView.getChildAt(0).getMeasuredHeight() - nestscView.getMeasuredHeight())) { // 上拉刷新实现
                    Log.i(TAG, "上拉加载更多");
                    tv_more.setEnabled(true);//
                    fix();//测试热修复
                    if (!isNoDta) {
                        num += 10;//模拟数据
                        tv_more.setVisibility(View.GONE);
                        pb.setVisibility(View.VISIBLE);
                        handler.sendEmptyMessageDelayed(1, 3000);
                    }


                }

            }
        });


        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                position = position % viewpagerpicture.size();
                float leftMargin;
                //更新小白点的移动的距离
                if (position == viewpagerpicture.size() - 1) {//最后一个不向后移动
                    leftMargin = position * pointDis;
                } else {
                    leftMargin = positionOffset * (float) pointDis + position * pointDis;
                }

                Log.i("小白点移动的距离", position + " : onPageScrolled: " + leftMargin + "-------" + positionOffset);

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img_blue_move.getLayoutParams();
                layoutParams.leftMargin = (int) leftMargin;
                img_blue_move.setLayoutParams(layoutParams);


            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;//一定要加上 否则容易出现 input  dispatch timeout  ANR
                Log.i("Pager ", "onPageSelected " + currentItem % 6);
            }

            @Override
            public void onPageScrollStateChanged(int position) {
                Log.i("Pager ", "PageScrollStateChanged " + position);
            }
        });

        vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();
                        isLoop = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLoop = true;
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_more:
                tv_more.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);
                handler.sendEmptyMessageDelayed(1, 3000);
                break;
        }

    }




    private void test() {
        TestCaculate testCaculate = new TestCaculate();
        testCaculate.caculate(MainActivity.this);

        TestbBug  tbug = new TestbBug();
        tbug.bug(this);
    }

//    public void setListViewHeightBasedOnChildren(ListView listView) {
//        // 获取ListView对应的Adapter
//        ListTestAdapter listAdapter = (ListTestAdapter) listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//
//        int totalHeight = 0;
//        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
//            // listAdapter.getCount()返回数据项的数目
//            View listItem = listAdapter.getView(i, null, listView);
//            // 计算子项View 的宽高
//            listItem.measure(0, 0);
//            // 统计所有子项的总高度
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        // listView.getDividerHeight()获取子项间分隔符占用的高度
//        // params.height最后得到整个ListView完整显示需要的高度
//        listView.setLayoutParams(params);
//    }


    private class MainAdapter extends FragmentPagerAdapter {

        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override //销毁数据
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
            //不经过合同交易 而获取外部技术的手段
        }

        @Override
        public Fragment getItem(int position) {

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;
        //有权限没有通过
        if (REQUEST_CODE == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }

            }
        }
        //如果有权限没有被允许
        if (hasPermissionDismiss) {
            Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            // showPermissionDialog();
            //跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
        }
    }


}
