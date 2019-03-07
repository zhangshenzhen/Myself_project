package com.shenzhen.retrofit.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class DisCacheUtils {

    private static StringBuilder sb;

    /* @pramr
     key 作为文件名
      json 作为存储的文件内容
     *
     * */

    public static void setData(Context context, String key, String json) throws FileNotFoundException {
        //context.getCacheDir(); ///data/user/0/com.shenzhen.retrofit/cache
        File mCache = new File(Environment.getExternalStorageDirectory().getPath(), key);
        if (mCache.exists()) {
            mCache.delete();
        }
         FileOutputStream fos = new FileOutputStream(mCache,false);//创建指定文件; false 代表每次重新写入数据
      try {
            //创建文件写入流
            fos.write(json.getBytes("gbk"));
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

     /* try {
            BufferedWriter   writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(json);
           // writer.write(json.getBytes("gbk").toString());
            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            */

    }

    /*key 作为读取的文件名
     * */
    public  static String getData(Context context, String key) {
        File mReadCache = new File(Environment.getExternalStorageDirectory().getPath(), key);
        if (mReadCache.exists()) {
            try {
                sb = new StringBuilder();
               // BufferedReader reader = new BufferedReader(new FileReader(mReadCache));
                //解决读取乱码
                InputStreamReader isr = new InputStreamReader(new FileInputStream(mReadCache), "gbk");
                BufferedReader reader = new BufferedReader(isr);
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                return new String(sb.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }


}
