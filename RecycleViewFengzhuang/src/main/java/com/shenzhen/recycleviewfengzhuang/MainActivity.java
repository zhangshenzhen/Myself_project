package com.shenzhen.recycleviewfengzhuang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.shenzhen.recycleviewfengzhuang.bean.UserPersonBean;
import com.shenzhen.recycleviewfengzhuang.fengzhaung.BaseRecycleViewAdapter;
import com.shenzhen.recycleviewfengzhuang.fengzhaung.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseRecycleViewAdapter.OnItemClickListner, BaseRecycleViewAdapter.OnItemLongClickListner {

    private RecyclerView recyclerView;
    private List<UserPersonBean> list;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();

        for (int i = 0; i < 180; i++) {
            list.add(new UserPersonBean("张三" + i, "26"));
        }

        recyclerView = findViewById(R.id.rcv);
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserAdapter(MainActivity.this, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(this);//设置监听
        adapter.setOnItemLongClickListner(this);
    }


    @Override
    public void onItemClickListner(View v, int position) {

        switch (v.getId()) {
            case R.id.img:
                Toast.makeText(MainActivity.this, list.get(position).getName() + ":" + list.get(position).getAge() + "头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv:
                Toast.makeText(MainActivity.this, list.get(position).getName() + ":" + list.get(position).getAge() + "名称", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    @Override
    public void onItemLongClickListner(View v, int position) {
        switch (v.getId()) {
            case R.id.img:
                Toast.makeText(MainActivity.this, list.get(position).getName() + "^_^" + list.get(position).getAge() + "头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv:
                Toast.makeText(MainActivity.this, list.get(position).getName() + "^_^" + list.get(position).getAge() + "名称", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
