package com.shenzhen.eventbus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.eventbus.R;
import com.shenzhen.eventbus.SecondActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FirstFragment extends Fragment {

    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,null);
        EventBus.getDefault().register(this);
        tv = view.findViewById(R.id.frg_tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });
        return view;
    }

    @Subscribe(threadMode = ThreadMode.POSTING,priority = 13)
    public void Event22(com.eventbus.eventbus.EventTest event) {
        String msg = "onEventMainThread收到了消息：666...6不完" + event.getmMsg();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        EventBus.getDefault().cancelEventDelivery(event) ; //拦截了
        tv.setText(msg);
        Log.i("TAG",msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}