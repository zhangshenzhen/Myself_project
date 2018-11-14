package com.shenzhen.coordinatorlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MyFragment extends Fragment{


    private int fp = -1;
    private View view;
    private String TAG = "TAGD";
    private ImageView img;
    private MainActivity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"Selected:= "+"Fragment");
        view = inflater.inflate(R.layout.item_fragment, null);
        img = view.findViewById(R.id.img);
        if (activity.isShow){
            init();
        }
        activity.isShow = true;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //这里可以去下预加载数据的问题，
        //fragment对用户可见，isVisibleToUser为true，
        // 不可见isVisibleToUser为false。对应于viewpager，当前pager，非当前pager

    }

    /*这里获取Activity中的数据*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        fp = activity.fp;
        Log.i(TAG,".........."+fp);
    }

    private void init() {

        if(fp==1){
            img.setImageResource(R.mipmap.pic3);
        }else if (fp==2){
            img.setImageResource(R.mipmap.pic4);
        }else if (fp==3){
            img.setImageResource(R.mipmap.pic5);
        }else if (fp==4){
            img.setImageResource(R.mipmap.pic6);
        }else if (fp==5){
            img.setImageResource(R.mipmap.pic2);

        }

    }
}
