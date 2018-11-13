package com.shenzhen.viewgroup_adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.shenzhen.viewgroup_adapter.recycle_fz.BaseRecycleViewAdapter;
import com.shenzhen.viewgroup_adapter.recycle_fz.UserAdapter2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements BaseRecycleViewAdapter.OnItemClickListner , BaseRecycleViewAdapter.OnItemLongClickListner{

    private RecyclerView recyclerView;
    private List<UserPersonBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();

        for (int i = 0; i < 180; i++) {
            list.add(new UserPersonBean("张三"+i,"26"));
        }

        recyclerView = findViewById(R.id.rcv);
        //设置布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        UserAdapter2 adapter = new UserAdapter2(MainActivity.this,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(this);
        adapter.setOnItemLongClickListner(this);
    }

    @Override
    public void onItemClickListner(View v, int position) {
        Toast.makeText(MainActivity.this, list.get(position).getName() + ":" + list.get(position).getAge(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClickListner(View v, int position) {
        Toast.makeText(MainActivity.this, list.get(position).getName() + "^_^ :" + list.get(position).getAge(),Toast.LENGTH_SHORT).show();

    }
}
