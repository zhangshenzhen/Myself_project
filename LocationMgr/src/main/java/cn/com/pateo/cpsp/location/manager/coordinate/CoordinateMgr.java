package cn.com.pateo.cpsp.location.manager.coordinate;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import cn.com.pateo.cpsp.location.manager.utils.StringUtil;

/*
 * 获取位置信息*/
public class CoordinateMgr {
    private static CoordinateMgr mInstance;
    private String lon;
    private String lat;

    /**
     * 获取单例
     *
     * @return 单例
     */
    public static CoordinateMgr getInstance() {
        if (null == mInstance) {
            mInstance = new CoordinateMgr();
        }
        return mInstance;
    }


    /*
     * 获取坐标经纬度
     * 返回值为字符串数组*/
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
            if (cursor.moveToNext()) {
                //经度
                lon = cursor.getString(0);
                //维度
                lat = cursor.getString(1);
            }
            cursor.close();
            String[] locaArr = {lon, lat};//反会字符数组
            return locaArr;
        }
        return null;
    }

}
