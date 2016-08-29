package com.sovnem.yoyoweibo.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.squareup.leakcanary.LeakCanary;

/**
 * 初始化application
 * Created by sovnem on 15/12/30.
 */
public class YoApp extends Application {
    private static final String TAG = "yoyoweibo";
    private static YoApp instance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LeakCanary.install(this);
    }

    public static YoApp getInstance() {

        return instance;
    }

    public boolean hasLogin(){
        return TokenManager.hasLogined(this);
    }
}
