package com.shenzhen.test.movies_fragment;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenzhen.test.R;
import com.shenzhen.test.movies_activity.MovieSearchActivity;
import com.shenzhen.test.utils.NetWorkUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieFrag extends BaseFragment {
    private String TAG = "MovieFrag";
    public TabLayout tab;
    public ViewPager vpager_fragment;
    private List<Pair<String, Fragment>> items;
    public RelativeLayout rel_title;
    public LinearLayout liner_pager;
    public LinearLayout liner_error;
    public TextView tv_error_remind;
    public boolean errorAgin = false;
    public TextView tv_search;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        context = getActivity();
//        view = inflater.inflate(R.layout.fragment_movie2, container, false);
//       // ButterKnife.bind(this, view);
//       // initview(view);
//        return view;
//    }

    @Override
    public int initLayout() {
        return R.layout.fragment_movie2;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

        tab = view.findViewById(R.id.tab);
        vpager_fragment = view.findViewById(R.id.vpager_fragment);

        tv_search = view.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);

        rel_title = view.findViewById(R.id.rel_title);
        liner_pager = view.findViewById(R.id.liner_pager);
        liner_error = view.findViewById(R.id.lin_error);
        tv_error_remind = view.findViewById(R.id.tv_error_remind);
        tv_error_remind.setOnClickListener(this);
    }

    /*
     * 这里获取网络数据，如果网络异常就显示异常的界面
     * */
    @Override
    public void initData() {
        items = new ArrayList<>();
        items.clear();
        items.add(new Pair<String, Fragment>("正在热映", HotShowFragment.getInstance(0)));
        items.add(new Pair<String, Fragment>("即将上映", HotShowFragment.getInstance(1)));

        vpager_fragment.setAdapter(new MainAdapter(this.getChildFragmentManager()));
        tab.setupWithViewPager(vpager_fragment);
        vpager_fragment.setOffscreenPageLimit(items.size());
        vpager_fragment.setCurrentItem(0);

        //处理显示正常或者异常的界面
        if (NetWorkUtils.isNetworkAvailable(getActivity())) {
            rel_title.setVisibility(View.VISIBLE);
            liner_pager.setVisibility(View.VISIBLE);
            liner_error.setVisibility(View.GONE);
        } else {
            rel_title.setVisibility(View.GONE);
            liner_pager.setVisibility(View.GONE);
            liner_error.setVisibility(View.VISIBLE);
            if (errorAgin) {
                tv_error_remind.setText("设置");
                tv_error_remind.setTextSize(16);
            }
        }

    }

    @Override
    public void bindListener() {


    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.tv_error_remind:
                if(errorAgin){
                    Intent intent =  new Intent(Settings.ACTION_SETTINGS);
                    getActivity().startActivity(intent);
                    tv_error_remind.setText("点击重试");
                    errorAgin = false;
                    return;
                }
                Log.d(TAG, "重试");
                errorAgin = true;
                initData();
                break;
            case  R.id.tv_search:
                Intent intent =  new Intent(getActivity(), MovieSearchActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }


    private class MainAdapter extends FragmentPagerAdapter {

        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override //销毁数据
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);

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


}
