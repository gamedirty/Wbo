package com.sovnem.yoyoweibo.net;

import java.io.Serializable;

public class HttpCacheBean implements Serializable {
    long cacheTime;
    String cacheBody;

    public HttpCacheBean(long cacheTime, String cacheBody) {
        this.cacheTime = cacheTime;
        this.cacheBody = cacheBody;
    }
}