package com.shenzhen.cachedemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络缓存的工具类
 *
 * @author ZHY
 */
public class NetCacheUtils {

    private LocalCacheUtils localCacheUtils;
    private MemoryCacheUtils memoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils localCacheUtils,MemoryCacheUtils memoryCacheUtils) {
         /*localCacheUtils = new LocalCacheUtils();
         memoryCacheUtils = new MemoryCacheUtils();*/
         this.localCacheUtils = localCacheUtils;
         this.memoryCacheUtils = memoryCacheUtils;
    }

    /**
     * 从网络下载图片
     *
     * @param ivPic
     * @param url
     */
    public void getBitmapFromNet(ImageView ivPic, String url) {
        // 访问网络的操作一定要在子线程中进行，采用异步任务实现
        MyAsyncTask task = new MyAsyncTask();
        task.execute(ivPic, url);

    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ivPic.setTag(url);//
        }
    };


    /**
     * 第一个泛型--异步任务执行的时候，通过execute传过来的参数； 第二个泛型--更新进度； 第三个泛型--异步任务执行以后返回的结果
     *
     * @author ZHY
     */
    private ImageView ivPic;
    private String url;

    private class MyAsyncTask extends AsyncTask<Object, Void, Bitmap> {


        // 耗时任务执行之前 --主线程
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 后台执行的任务
        @Override
        protected Bitmap doInBackground(Object... params) {
            // 执行异步任务的时候，将URL传过来
            ivPic = (ImageView) params[0];
            url = (String) params[1];
            Bitmap bitmap = downloadBitmap(url);
            // 为了保证ImageView控件和URL一一对应，给ImageView设定一个标记
            Message message = Message.obtain();
            message.obj = url;
            // handler.sendMessageDelayed(message,0);

            ivPic.setTag(url);// 关联ivPic和URL Handler移动到主线程

            return bitmap;
        }

        // 更新进度 --主线程
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        // 耗时任务执行之后--主线程
        @Override
        protected void onPostExecute(Bitmap result) {
            if(result==null){
               return;
            }
            String mCurrentUrl = (String) ivPic.getTag();
            if (url.equals(mCurrentUrl)) {
                ivPic.setImageBitmap(result);
                System.out.println("从网络获取图片");
                // 从网络加载完之后，将图片保存到本地SD卡一份，
                localCacheUtils.setBitmap2Local(url, result);
                // 从网络加载完之后，将图片，保存到内存中一份
                memoryCacheUtils.setBitmap2Memory(url, result);

            }
        }
    }

    /**
     * 下载网络图片
     *
     * @param url
     * @return
     */
    private Bitmap downloadBitmap(String url) {
        HttpURLConnection conn = null;
        try {
            URL mURL = new URL(url);
            // 打开HttpURLConnection连接
            conn = (HttpURLConnection) mURL.openConnection();
            // 设置参数
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            // 开启连接
            conn.connect();

            // 获得响应码
            int code = conn.getResponseCode();
            if (code == 200) {
                // 相应成功,获得网络返回来的输入流
                InputStream is = conn.getInputStream();

                // 图片的输入流获取成功之后，设置图片的压缩参数,将图片进行压缩
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;// 将图片的宽高都压缩为原来的一半,在开发中此参数需要根据图片展示的大小来确定,否则可能展示的不正常
                options.inPreferredConfig = Bitmap.Config.RGB_565;// 这个压缩的最小

                // Bitmap bitmap = BitmapFactory.decodeStream(is);
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);// 经过压缩的图片

                return bitmap;
            }

        } catch (Exception e) {
            Log.i("连接异常：" + e.getMessage(), "");
            // 断开连接
            conn.disconnect();
            e.printStackTrace();
        } finally {
            // 断开连接
            //conn.disconnect();
        }

        return null;
    }
}
