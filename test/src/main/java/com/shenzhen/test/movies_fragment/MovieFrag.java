package com.shenzhen.test.movies_fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shenzhen.test.R;

import java.util.ArrayList;
import java.util.List;

public  class MovieFrag extends BaseFragment {

    public TabLayout tab;
    public ViewPager vpager_fragment;
    private List<Pair<String, Fragment>> items;
    public RelativeLayout rel_title;
    public LinearLayout liner_pager;
    public LinearLayout liner_error;

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

        rel_title = view.findViewById(R.id.rel_title);
        liner_pager = view.findViewById(R.id.liner_pager);
        liner_error = view.findViewById(R.id.lin_error);

    }

    /*
    * 这里获取网络数据，如果网络异常就显示异常的界面
    * */
    @Override
    public void initData() {
        items = new ArrayList<>();
        items.add(new Pair<String, Fragment>("正在热映", HotShowFragment.getInstance(0)));
        items.add(new Pair<String, Fragment>("即将上映", HotShowFragment.getInstance(1)));

        vpager_fragment.setAdapter(new MainAdapter(this.getChildFragmentManager()));
        tab.setupWithViewPager(vpager_fragment);
        vpager_fragment.setOffscreenPageLimit(items.size());
        vpager_fragment.setCurrentItem(0);

        //处理显示正常或者异常的界面
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
