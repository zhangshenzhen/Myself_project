package com.shenzhen.test.movies_fragment;

import android.view.View;
import android.widget.TextView;

import com.shenzhen.test.R;

public  class MoviehouseFrg extends BaseFragment {




    @Override
    public int initLayout() {
        return R.layout.fragment_movie_house;
    }


    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

        TextView tv_typle =view.findViewById(R.id.tv_typle);
        tv_typle.setText("电影院");
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindListener() {

    }

    @Override
    public void onClickEvent(View v) {

    }
}
