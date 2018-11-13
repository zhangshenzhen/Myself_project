package com.draghelper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.draghelper.adapter.MyDragHelperAdapter;
import com.draghelper.bean.Item_Bean;
import com.draghelper.listener.OnQQDragListener;

import java.util.ArrayList;
import java.util.List;

public class QQItemDragHelperActivity extends Activity {
    private LinearLayout linearLayout;
    private TextView textView;

    private ListView listView;
    private List<Item_Bean> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragerhelper);

        initView();
     }

    void initView() {
        listView = (ListView) findViewById(R.id.listView);
        datas = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        for (int i = 0; i < 20; i++) {
            Item_Bean chatter = new Item_Bean(bitmap, "好友", "你好", "昨天");
            datas.add(chatter);
        }

        MyDragHelperAdapter adapter = new MyDragHelperAdapter(this, datas);
        listView.setAdapter(adapter);

    }




}
