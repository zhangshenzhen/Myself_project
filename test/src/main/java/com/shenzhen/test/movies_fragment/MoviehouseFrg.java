package com.shenzhen.test.movies_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenzhen.test.R;

public  class MoviehouseFrg extends Fragment {

    private Context context;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_movie_house, container, false);
        // ButterKnife.bind(this, view);
        initview(view);
        return view;
    }

    private void initview(View view) {

        TextView tv_typle =view.findViewById(R.id.tv_typle);
        tv_typle.setText("电影院");
    }
 }
