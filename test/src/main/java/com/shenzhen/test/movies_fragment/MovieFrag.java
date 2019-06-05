package com.shenzhen.test.movies_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenzhen.test.R;

import java.util.ArrayList;
import java.util.List;

public  class MovieFrag extends BaseFragment {
private Context context;
private View view;
    public TextView tv_hotshow;
    public TextView tv_soon;
    public TabLayout tab;
    public ViewPager vpager_fragment;
    private List<Pair<String, Fragment>> items;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_movie2, container, false);
       // ButterKnife.bind(this, view);
       // initview(view);
        return view;
    }

    private void initview(View view) {

    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
//        tv_hotshow = view.findViewById(R.id.tv_hot);
//        tv_soon = view.findViewById(R.id.tv_soon);
        tab = view.findViewById(R.id.tab);
        vpager_fragment = view.findViewById(R.id.vpager_fragment);

    }

    @Override
    public void afterInitView() {
        items = new ArrayList<>();
        items.add(new Pair<String, Fragment>("正在热映", HotShowFragment.getInstance(0)));
        items.add(new Pair<String, Fragment>("即将上映", HotShowFragment.getInstance(1)));

        vpager_fragment.setAdapter(new MainAdapter(this.getChildFragmentManager()));
        tab.setupWithViewPager(vpager_fragment);
        vpager_fragment.setOffscreenPageLimit(items.size());
        vpager_fragment.setCurrentItem(0);
    }

    @Override
    public void bindListener() {
//        tv_hotshow.setOnClickListener(this);
//        tv_soon.setOnClickListener(this);


    }

    @Override
    public void onClickEvent(View v) {

    }


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
}
