package com.shenzhen.scrollview_inner_slide.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.shenzhen.scrollview_inner_slide.MainActivity;
import com.shenzhen.scrollview_inner_slide.R;

public class MyFragment extends Fragment {


    private int fp = -1;
    private View view;
    private String TAG = "TAGD";
    private ImageView img;
    private MainActivity activity;
    private int position; //作为每个Fragment 对象中的 position

    public static MyFragment getInstance(int index) {
        MyFragment fragment = new MyFragment();
        fragment.position = index;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "SelectedFragment:= "  + position);
        view = inflater.inflate(R.layout.item_fragment, null);
        img = view.findViewById(R.id.img);
        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
       // init();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //init();
        }
        //这里可以去下预加载数据的问题，
        //fragment对用户可见，isVisibleToUser为true，
        // 不可见isVisibleToUser为false。对应于viewpager，当前pager，非当前pager

    }

    /*这里获取Activity中的数据*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;

    }

    private void init() {
        if (position == 0) {
            img.setImageResource(R.mipmap.pic8);
        } else if (position == 1) {
            img.setImageResource(R.mipmap.pic3);
        } else if (position == 2) {
            img.setImageResource(R.mipmap.pic4);
        } else if (position == 3) {
            img.setImageResource(R.mipmap.pic5);
        } else if (position == 4) {
            img.setImageResource(R.mipmap.pic6);
        } else if (position == 5) {
            img.setImageResource(R.mipmap.pic2);

        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "这是Viewpager第："+ position+" 位置Fragment", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
