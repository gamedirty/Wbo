package com.sovnem.yoyoweibo.net;

import android.util.LruCache;

/**
 * Created by sovnem on 16/3/7.
 */
public class HttpCache {
    private static final int CACHE_TIME_DURATION = 5 * 1000 * 60;
    private static LruCache<String, HttpCacheBean> lruCache = new LruCache<>(100);

    public HttpCache() {
    }

    public static void put(String key, String cacheContent) {
        lruCache.put(key, new HttpCacheBean(System.currentTimeMillis(), cacheContent));
    }

    public static String get(String key) {
        HttpCacheBean cache = lruCache.get(key);
        if (null != cache)
            if (System.currentTimeMillis() - cache.cacheTime <= CACHE_TIME_DURATION) {
                return cache.cacheBody;
            }
        return null;
    }
}
