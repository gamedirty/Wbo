package com.sovnem.yoyoweibo.app;

import android.app.Application;
import android.text.TextUtils;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sovnem.data.DataConstants;
import com.sovnem.data.biz.TokenManager;

/**
 * 初始化application
 * Created by sovnem on 15/12/30.
 */
public class WoodApplication extends Application {
    public static boolean hasLogin;
    private static WoodApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Oauth2AccessToken tokens = TokenManager.readAccessToken(this);
        DataConstants.TOKEN = tokens.getToken();
        hasLogin = !TextUtils.isEmpty(DataConstants.TOKEN);
        instance = this;
    }

    /**
     * @return
     */
    public static WoodApplication getInstance() {
        return instance;
    }
}
