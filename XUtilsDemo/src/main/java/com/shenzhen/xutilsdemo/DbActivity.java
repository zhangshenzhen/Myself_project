package com.shenzhen.xutilsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.shenzhen.xutilsdemo.db.DaoConfig;
import com.shenzhen.xutilsdemo.db.Person;

import org.xutils.ex.DbException;

import java.util.ArrayList;

public class DbActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actiity_db);

        Log.d("","当前时间"+System.currentTimeMillis());
    }

    public void createDB(View view){
        //用集合向Person表中插入多条数据
        ArrayList<Person> person = new ArrayList<>();
        person.add(new Person("张三"));
        person.add(new Person("李四"));
        person.add(new Person("赵六"));

        //db.save()方法不仅可以插入单个对象，还能插入集合
        try {
            DaoConfig.getDb().save(person);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
}
