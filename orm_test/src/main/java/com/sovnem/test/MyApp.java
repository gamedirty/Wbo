package com.sovnem.test;

import android.app.Application;

import org.xutils.x;

/**
 * Created by sovnem on 16/3/8.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
