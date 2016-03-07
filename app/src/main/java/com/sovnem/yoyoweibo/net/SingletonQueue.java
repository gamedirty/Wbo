package com.sovnem.yoyoweibo.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * requestQueue管理类
 * 单例
 * Created by sovnem on 16/3/6.
 */
public class SingletonQueue {
    private static SingletonQueue instance;
    private RequestQueue requestQueue;

    private SingletonQueue(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized SingletonQueue getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonQueue(context);
        }
        return instance;
    }

    public void addToQueue(Request request) {
        requestQueue.add(request);
    }

    public void addToQueueWithTag(Request request, String tag) {
        request.setTag(tag);
        addToQueue(request);
    }

    public void cancleRequest(String tag) {
        requestQueue.cancelAll(tag);
    }
}
