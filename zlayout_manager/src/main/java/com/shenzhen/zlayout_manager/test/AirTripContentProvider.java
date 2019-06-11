package com.shenzhen.zlayout_manager.test;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class AirTripContentProvider extends ContentProvider {
    private String TAG = "AirTripContentProvider";

    public static final int SUCCESS = 1;
    /**
     * ,检查uri的规则,如果uri匹配失败 返回-1
     */
    static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        mUriMatcher.addURI("cn.com.pateo.cpsp.service.airtrip.IContentProvider", "city", SUCCESS);//匹配规则
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String dbPath = "/data/data/" + getContext().getPackageName() + "/databases/" + "city.db";
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            Log.d(TAG, "query 查询数据");
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
            return db.query("city", projection, selection, selectionArgs, null, null, sortOrder);
        }
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String dbPath = "/data/data/" + getContext().getPackageName() + "/databases/" + "city.db";
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            System.out.println("insert 添加数据");
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

            db.insert("city", null, values);

        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String dbPath = "/data/data/" + getContext().getPackageName() + "/databases/" + "city.db";

        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            Log.d(TAG, "delete 删除数据");
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            db.delete("city", selection, selectionArgs);
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String dbPath = "/data/data/" + getContext().getPackageName() + "/databases/" + "city.db";
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            Log.d(TAG, "update 更新数据");
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            db.update("city", values, selection, selectionArgs);

        }
        return 0;
    }
}
