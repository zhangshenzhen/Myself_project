package com.shenzhen.xutilsdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.shenzhen.xutilsdemo.db.MydatabaseOphelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.com.pateo.cpsp.service.R;

public class DbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actiity_db);

        Log.d("", "当前时间" + System.currentTimeMillis());
    }

    public void createDB(View view) {
        //用集合向Person表中插入多条数据
//        ArrayList<Person> personList = new ArrayList<>();
//        personList.add(new Person("张三"));
//        personList.add(new Person("李四"));
//        personList.add(new Person("赵六"));
//
//        //db.save()方法不仅可以插入单个对象，还能插入集合
//        try {
//            DaoConfig.getDb().save(personList);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }

        //插入增加
        MydatabaseOphelper ophelper = new MydatabaseOphelper(this);
        SQLiteDatabase database = ophelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "shenzhen");
        values.put("money", "30000");
        database.insert("person", null, values);

        //查询
        MydatabaseOphelper helper = new MydatabaseOphelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor cursor = db.rawQuery("select * from person where name ", new String[]{"zhangshan"});
        Cursor cursor = db.rawQuery("select * from person ", null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String money = cursor.getString(2);
            System.out.println(" 测试数据库 id :" + id + " name" + name + "----" + "money:" + money);
        }
        cursor.close();
    }

    //删除
    public void delete(View view) {
        MydatabaseOphelper ophelper = new MydatabaseOphelper(this);
        SQLiteDatabase database = ophelper.getWritableDatabase();
        database.delete("person", "name=?", new String[]{"zhangshan"});

        MydatabaseOphelper helper = new MydatabaseOphelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor cursor = db.rawQuery("select * from person where name ", new String[]{"zhangshan"});
        Cursor cursor = db.rawQuery("select * from person ", null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String money = cursor.getString(2);
            System.out.println(" 测试数据库 id :" + id + " name" + name + "----" + "money:" + money);
        }
        cursor.close();
    }

    //改

    public void update(View view) {
        MydatabaseOphelper ophelper = new MydatabaseOphelper(this);
        SQLiteDatabase database = ophelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("money", "999");
        database.update("person", values, "name=?", new String[]{"lisi"});
        MydatabaseOphelper helper = new MydatabaseOphelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor cursor = db.rawQuery("select * from person where name ", new String[]{"zhangshan"});
        Cursor cursor = db.rawQuery("select * from person ", null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String money = cursor.getString(2);
            System.out.println(" 测试数据库 id :" + id + " name" + name + "----" + "money:" + money);
        }
        cursor.close();
    }

    //条件查询
    public void query(View view) {
        MydatabaseOphelper helper = new MydatabaseOphelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor cursor = db.rawQuery("select * from person where name ", new String[]{"zhangshan"});
        Cursor cursor = db.query("person", null, "money=?", new String[]{"30000"}, null, null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String money = cursor.getString(2);
            System.out.println(" 测试数据库 id :" + id + " name" + name + "----" + "money:" + money);
        }
        String dbPath = "/data/data/" + getPackageName()
                + "/databases/" + "test.db";

        File file = new File(dbPath);
        if (file.exists()) {
            Toast.makeText(this, "存在", Toast.LENGTH_SHORT).show();

            //获取只读数据库+
            SQLiteDatabase sql = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);

            //Cursor cursor2 = db.query("person", null, "money=?", new String[]{"30000"}, null, null, null);
            Cursor cursor2 = db.rawQuery("select * from person ", null);

            while (cursor2.moveToNext()) {
                String id = cursor2.getString(0);
                String name = cursor2.getString(1);
                String money = cursor2.getString(2);
                System.out.println(" 测试数据库存在 id :" + id + " name" + name + "----" + "money:" + money);
            }
            cursor.close();
            cursor2.close();

        }

    }


    public void readdb(View view) {
    String dbPath = "/data/data/" + this.getPackageName()
            + "/databases/" + "city.db";
        final File file = new File(dbPath);
        if (file.exists() && file.length() > 0) {
            handler.sendEmptyMessage(1);
        } else {

            new Thread() {
                @Override
                public void run() {
                    super.run();

                    try {
                        InputStream is = getAssets().open("city.db");
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);

                        }
                        fos.close();
                        is.close();
                        handler.sendEmptyMessage(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            // 读取数据库

        }

    }
   public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String dbPath = "/data/data/" + DbActivity.this.getPackageName()
                    + "/databases/" + "city.db";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);

            //Cursor cursor = db.rawQuery("select * from city", null); //查询所有

            //条件查询
          // Cursor cursor = db.query("city", null, "name=?", new String[]{"南京"}, null, null, null);
//          while (cursor.moveToNext()){
//            String cityName = cursor.getString(0);
//            String lng = cursor.getString(1);
//            String lat = cursor.getString(2);
//            System.out.println(" 测试数据库存在 "+cursor.getString(3)+"  cityName :" + cityName + " lng :" + lng + "----" + "lat :" + lat +"jianping :"+cursor.getString(4)+" ,jianping_firstchar :"+cursor.getString(5));
//
//          }

            //条件查询带单个属性
            Cursor cursor = db.rawQuery("select lon from city where name=?",new String[]{"北京"});
           if (cursor.moveToNext()){
           System.out.println(" 测试数据库存在lon "+cursor.getString(0));
           }else {
               System.out.println(" 测试数据库lonbu存在 ");

           }
           cursor.close();
            Cursor cursor2 = db.rawQuery("select lat from city where name=?",new String[]{"北京"});
            if (cursor2.moveToNext()){
                System.out.println(" 测试数据库存在lat "+cursor2.getString(0));
            }else {
                System.out.println(" 测试数据库latbu存在 ");

            }
            cursor2.close();
        }

   };

}
