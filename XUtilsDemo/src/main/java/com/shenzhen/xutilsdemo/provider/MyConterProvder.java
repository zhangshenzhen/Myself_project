package com.shenzhen.xutilsdemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyConterProvder extends ContentProvider {


    public static final int SUCCESS = 1;
    /**
     * 创建一个保安,检查uri的规则,如果uri匹配失败 返回-1
     */
    static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        mUriMatcher.addURI("com.shenzhen.db", "person", SUCCESS);//匹配规则
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String dbPath = "/data/data/" + getContext().getPackageName()+ "/databases/" + "test.db";

        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            System.out.println("delete 删除数据");
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
//            MydatabaseOphelper helper = new MydatabaseOphelper(getContext());
//            SQLiteDatabase db = helper.getWritableDatabase();
            db.delete("person", selection, selectionArgs);
            //利用内容提供者的解析器,通知内容观察者数据发生了变化
            // getContext().getContentResolver().notifyChange(uri, null);
        } else {
            throw new IllegalArgumentException("口令 不正确");
        }
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }



    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String dbPath = "/data/data/" + getContext().getPackageName() + "/databases/" + "test.db";
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            System.out.println("insert 添加数据");
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
//            MydatabaseOphelper helper = new MydatabaseOphelper(getContext());
//            SQLiteDatabase db = helper.getWritableDatabase();
            db.insert("person", null, values);
            //利用内容提供者的解析器,通知内容观察者数据发生了变化
            // getContext().getContentResolver().notifyChange(uri, null);
        } else {
            throw new IllegalArgumentException("口令 不正确");
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String dbPath = "/data/data/" + getContext().getPackageName()+ "/databases/" + "test.db";
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            System.out.println("query 查询数据");
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
            // MydatabaseOphelper helper = new MydatabaseOphelper(getContext());
            // SQLiteDatabase db = helper.getReadableDatabase();
            return db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
        } else {
            throw new IllegalArgumentException("口令 不正确");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        String dbPath = "/data/data/" + getContext().getPackageName()+ "/databases/" + "test.db";
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            System.out.println("update 更新数据");
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
//            MydatabaseOphelper helper = new MydatabaseOphelper(getContext());
//            SQLiteDatabase db = helper.getWritableDatabase();
            db.update("person", values, selection, selectionArgs);
            //利用内容提供者的解析器,通知内容观察者数据发生了变化
            // getContext().getContentResolver().notifyChange(uri, null);
        } else {
            throw new IllegalArgumentException("口令 不正确");
        }
        return 0;
    }

}
