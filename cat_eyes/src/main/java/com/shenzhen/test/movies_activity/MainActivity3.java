package com.shenzhen.test.movies_activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shenzhen.test.R;
import com.shenzhen.test.movies_fragment.MovieFrag;
import com.shenzhen.test.movies_fragment.MovieOrderFrg;
import com.shenzhen.test.movies_fragment.MoviehouseFrg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final String TAG = "MainActivity.class";
    private ViewPager viewpagr;
    public LinearLayout movie;
    public LinearLayout movie_house;
    public LinearLayout movie_order;
    public FragmentTransaction transaction;
    public FragmentManager fragmentManager;
    public MovieFrag newHomeFrag;
    public MoviehouseFrg moviehouseFrg;
    public MovieOrderFrg movieOrderFrg;
    public Intent intent;
    public String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET};
    private int mRequestCode = 100;
    public ImageView img_back;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cateyes);
//
//        initView()
//        initafter();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cateyes;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }


    @Override
    protected void initView() {
        movie = findViewById(R.id.movie);
        movie_house = findViewById(R.id.movie_house);
        movie_order = findViewById(R.id.movie_order);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(this);

        movie.setOnClickListener(this);
        movie_house.setOnClickListener(this);
        movie_order.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initafter();
    }

    @Override
    protected void initBindData() {
        checkAppPermission();
    }

    private void initafter() {

        fragmentManager = getSupportFragmentManager();

        setTabSelection(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie:
                setTabSelection(0);
                break;
            case R.id.movie_house:
                setTabSelection(1);
                //开启另一个应用的activity 或者service
//                intent = new Intent(Intent.ACTION_MAIN);
//                 intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                 ComponentName cn = new ComponentName("com.shenzhen.zlayout_manager", "com.shenzhen.zlayout_manager.MyService");
//                // ComponentName cn = new ComponentName("cn.com.pateo.cpsp.service", "cn.com.pateo.cpsp.service.CopyAssetService");
//                intent.setComponent(cn);//cn.com.pateo.cpsp.service
//                //startActivity(intent);
//                startService(intent);
                // stopService(intent);
                break;
            case R.id.movie_order:
                setTabSelection(2);
                break;
            case R.id.img_back:
                finish();
                break;

        }
    }


    public void setTabSelection(int index) {
        //重置按钮
        resetBtn();
        transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                movie.setBackgroundColor(Color.GRAY);
                if (newHomeFrag == null) {
                    newHomeFrag = new MovieFrag();
                    transaction.replace(R.id.id_content, newHomeFrag);
                } else {
                    transaction.show(newHomeFrag);
                }
                //  TrackHelper.track().event(TrackCommon.ViewTrackCategroy, TrackCommon.ViewTrackPagesMainTab).name("首页").value(1f).with(getTracker());
                break;
            case 1:
//                tv_main_vip_jie.setTextColor(Color.parseColor("#52b8b8"));
//                tv_main_vip_jie.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.store, 0, 0);
                movie_house.setBackgroundColor(Color.GRAY);
                if (moviehouseFrg == null) {
                    moviehouseFrg = new MoviehouseFrg();
                    transaction.add(R.id.id_content, moviehouseFrg);
                } else {
                    transaction.show(moviehouseFrg);
                }
                // TrackHelper.track().event(TrackCommon.ViewTrackCategroy, TrackCommon.ViewTrackPagesMerchatTab).name("铺子").value(1f).with(getTracker());
                break;
            case 2:
                movie_order.setBackgroundColor(Color.GRAY);
                if (movieOrderFrg == null) {
                    movieOrderFrg = new MovieOrderFrg();
                    transaction.add(R.id.id_content, movieOrderFrg);
                } else {
                    transaction.show(movieOrderFrg);
                }
                break;

        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 清楚掉所有选中状态
     */
    private void resetBtn() {

        movie.setBackgroundColor(Color.WHITE);
        movie_house.setBackgroundColor(Color.WHITE);
        movie_order.setBackgroundColor(Color.WHITE);

    }

    /**
     * 将所有的Fragment都置为隐藏状态
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (newHomeFrag != null) {
            transaction.hide(newHomeFrag);
        }
        if (moviehouseFrg != null) {
            transaction.hide(moviehouseFrg);
        }
        if (movieOrderFrg != null) {
            transaction.hide(movieOrderFrg);
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
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        } else { //说明权限都已经通过，可以做你想做的事情去 }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}

