package com.shenzhen.retrofit.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lx on 2017/11/24.
 */
/*
* 流转化为字符的工具类*/
public class InputStreamTools {
    /*
    * 读取流的工具类，*/
  public static String readStream(InputStream is) throws IOException {
      ByteArrayOutputStream baos =  new ByteArrayOutputStream();
      byte []  buff = new byte[1024];
      int len = -1;
      while ((len = is.read(buff))!= -1){
             baos.write(buff, 0,len);
      }
        is.close();
   return baos.toString();
   }

}
