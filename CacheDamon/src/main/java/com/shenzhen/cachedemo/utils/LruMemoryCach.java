package com.shenzhen.cachedemo.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class LruMemoryCach {


        private LruCache<String, Bitmap> mMemoryCache;
        public LruMemoryCach() {
            int maxMemory = (int) Runtime.getRuntime().maxMemory() / 10;
            mMemoryCache = new LruCache<String, Bitmap>(maxMemory) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        /**
         * @return void
         * @Title: addJsonToMemoryCache
         * @Description: TODO 添加json内存
         */
        public void addJsonToMemoryCache(String key, Bitmap bitmap) {
            if (mMemoryCache == null) {
                return;
            }
            if ("".equals(key) || key == null) {
                return;
            }

            if (getJsonFromMemCache(key) == null && bitmap != null) {
                mMemoryCache.put(key, bitmap);
            }
        }

        /**
         * 从内存缓存中获取一个Bitmap
         *
         * @param key
         * @return
         */
        public Bitmap getJsonFromMemCache(String key) {
            if (mMemoryCache == null) {
                return null;
            }
            return mMemoryCache.get(key);
        }


}
