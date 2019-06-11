package com.shenzhen.zlayout_manager.test;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/*
 * 获取位置信息*/
public class LocationMgr {
    private static LocationMgr mInstance;
    private String lon;
    private String lat;

    /**
     * 获取单例
     *
     * @return 单例
     */
    public static LocationMgr getInstance() {
        if (null == mInstance) {
            mInstance = new LocationMgr();
        }
        return mInstance;
    }


    public String[] getCityLngAndLat(Context context, String name) {
        int lastindex = 0;
        String lon = "";
        String lat = "";

        if (StringUtil.isNotEmpty(name)) {
            if (name.trim().endsWith("市")) {
                lastindex = name.indexOf("市");
                name = name.substring(0, lastindex);
            }
            ContentResolver resolver = context.getContentResolver();
            Uri uri = Uri.parse("content://cn.com.pateo.cpsp.service.airtrip.IContentProvider/city");
            Cursor cursor = resolver.query(uri, new String[]{"lon", "lat"}, "name=?", new String[]{name}, null);

            if (cursor != null && cursor.moveToNext()) {
                lon = cursor.getString(0);  //经度
                lat = cursor.getString(1); //维度
                cursor.close();
            }
            String[] locaArr = {lon, lat};//反会字符数组
            return locaArr;
        }
        return null;
    }

}
