package com.shenzhen.recycleview_coupousmoretyple;

import android.app.Application;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Date;

public class XUtilsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); //是否输出debug日志，开启debug会影响性能。

        Thread.currentThread().setUncaughtExceptionHandler(new MyexceptionHandler());
    }


    private class MyexceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override//等发现了为捕获的异常的时候调用的方法；
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.e("MyexceptionHandler", "......程序发现了异常，被哥们捕获了"+ex);
            StringBuffer sb = new StringBuffer();
            Date date = new Date();
            //格式化时间
            String time = DateFormat.getInstance().format(date);
            sb.append("Time:");
            sb.append(time+"\n");//当前的系统时间；
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
                File file = new File(Environment.getExternalStorageDirectory().getPath()+"/Download","Robort_error"+time+".txt");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(sb.toString().getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
       /* android.os.Process.killProcess(android.os.Process.myPid());
        Log.e("MyexceptionHandler", "......启动自杀方式，再次激活程序");*/
        }
    }
}
