package com.shenzhen.scrollview_inner_slide;

import android.app.Application;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Date;

public class ScrollViewApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.currentThread().setUncaughtExceptionHandler(new MyexceptionHandler());
    }


    private class MyexceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override//等发现了为捕获的异常的时候调用的方法；
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.e("MyexceptionHandler", "......程序发现了异常，被哥们捕获了" + ex);
            StringBuffer sb = new StringBuffer();
            Date date = new Date();
            //格式化时间
            String time = DateFormat.getInstance().format(date);
            sb.append("Time:");
            sb.append(time + "\n");//当前的系统时间；
            Field[] fields = Build.class.getDeclaredFields();
           /* for(Field field : fields){
                try {
                   String name = field.getName();
                    String value =  (String)field.get(null);
                    sb.append(name+"="+value+"\n"); //追加信息；
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/

            //错误日志, 把异常写到文件中；
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            sb.append(sw.toString());//追加；

            try {  //需要添加读写权限
                File filedir = new File(Environment.getExternalStorageDirectory().getPath()+"/Robort_error"); //先创建文件夹
              if (!filedir.exists()) {
                  filedir.mkdir();
                }
                //在创建文件
                File file = new File(Environment.getExternalStorageDirectory().getPath()+"/Robort_error",System.currentTimeMillis()+".txt");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(sb.toString().getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MyexceptionHandler", "......程序发现了异常，被哥们捕获了:" + e.getMessage());
            }

//           try {
//            File mCache = new File(Environment.getExternalStorageDirectory().getPath()+"/", "Robort_error"+System.currentTimeMillis()+ ".txt");
//            FileOutputStream fos = new FileOutputStream(mCache,false);//创建指定文件; false 代表每次重新写入数据
//                //创建文件写入流
//                fos.write(sb.toString().getBytes("gbk"));
//                fos.flush();
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Log.e("MyexceptionHandler", "......程序发现了异常，被哥们捕获了:" + e.getMessage());
//            }//琵琶

       /* android.os.Process.killProcess(android.os.Process.myPid());
          Log.e("MyexceptionHandler", "......启动自杀方式，再次激活程序");*/
        }
    }
}
