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
    private String Tag = "TAGD";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_fragment, null);
        init(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    /*这里获取Activity中的数据*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity) context;
        fp = mainActivity.fp;
        Log.i(Tag,".........."+fp);
    }

    private void init(View view) {
        ImageView img = view.findViewById(R.id.img);
        if(fp==1){
            img.setImageResource(R.mipmap.pic3);
        }else if (fp==2){
            img.setImageResource(R.mipmap.pic4);
        }else if (fp==3){
            img.setImageResource(R.mipmap.pic5);
        }else if (fp==4){
            img.setImageResource(R.mipmap.pic6);
        }else if (fp==5){
            img.setImageResource(R.mipmap.pic10);
        }


    }
}
