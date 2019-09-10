package com.shenzhen.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {

//    public static boolean isNetWorkAvailble(Context context){
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (cm==null){
//            return false;
//        }else {
//            NetworkInfo[] infos = cm.getAllNetworkInfo();
//            if(infos != null){
//                for (NetworkInfo net:infos) {
//                    if(net.getState() ==NetworkInfo.State.CONNECTED){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }



    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }
}

