package com.sovnem.yoyoweibo.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sovnem.data.DataConstants;
import com.sovnem.data.biz.TokenManager;

/**
 * 初始化application
 * Created by sovnem on 15/12/30.
 */
public class YoApp extends Application {
    private static final String TAG = "yoyoweibo";
    public static boolean hasLogin;
    private static YoApp instance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        Oauth2AccessToken tokens = TokenManager.readAccessToken(this);
        DataConstants.TOKEN = tokens.getToken();
        hasLogin = !TextUtils.isEmpty(DataConstants.TOKEN);
        instance = this;
    }

    public static YoApp getInstance() {
        return instance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
