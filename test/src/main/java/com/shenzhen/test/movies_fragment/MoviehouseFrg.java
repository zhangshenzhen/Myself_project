package com.shenzhen.test.movies_fragment;

import android.view.View;
import android.widget.TextView;

import com.shenzhen.test.R;

public  class MoviehouseFrg extends BaseFragment {


//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        context = getActivity();
//        view = inflater.inflate(R.layout.fragment_movie_house, container, false);
//
//        return view;
//    }

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
