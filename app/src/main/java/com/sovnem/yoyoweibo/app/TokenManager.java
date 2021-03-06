package com.sovnem.yoyoweibo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * token管理
 * Created by sovnem on 15/12/30.
 */
public class TokenManager {
    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";

    private static final String KEY_UID = "uid";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static String TOKEN;//缓存的token

    /**
     * 保存 Token 对象到 SharedPreferences。
     *
     * @param context 应用程序上下文环境
     * @param token   Token 对象
     */
    public static void writeAccessToken(Context context, Oauth2AccessToken token) {
        if (null == context || null == token) {
            return;
        }
        if (!TextUtils.isEmpty(token.getToken())) {
            TOKEN = token.getToken();
        }
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_UID, token.getUid());
        editor.putString(KEY_ACCESS_TOKEN, token.getToken());
        editor.putString(KEY_REFRESH_TOKEN, token.getRefreshToken());
        editor.putLong(KEY_EXPIRES_IN, token.getExpiresTime());
        editor.commit();
    }

    /**
     * 从 SharedPreferences 读取 Token 信息。
     *
     * @param context 应用程序上下文环境
     * @return 返回 Token 对象
     */
    public static Oauth2AccessToken readAccessToken(Context context) {
        if (null == context) {
            return null;
        }


        Oauth2AccessToken token = new Oauth2AccessToken();
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        token.setUid(pref.getString(KEY_UID, ""));
        token.setToken(pref.getString(KEY_ACCESS_TOKEN, ""));
        token.setRefreshToken(pref.getString(KEY_REFRESH_TOKEN, ""));
        token.setExpiresTime(pref.getLong(KEY_EXPIRES_IN, 0));

        return token;
    }

    /**
     * token是不是存在
     * 是不是已经登陆
     *
     * @param context
     * @return
     */
    public static boolean hasLogined(Context context) {
        if (!TextUtils.isEmpty(TOKEN)) return true;
        TOKEN = readAccessToken(context).getToken();
        return !TextUtils.isEmpty(TOKEN);
    }

    /**
     * 获取uid
     *
     * @param context
     * @return
     */
    public static String getUid(Context context) {
        Oauth2AccessToken token = readAccessToken(context);
        return token.getUid();
    }

    public static String getToken(Context context) {
        if (TextUtils.isEmpty(TOKEN)) {
            TOKEN = readAccessToken(context).getToken();
        }
        return TOKEN;
    }

    /**
     * 清空 SharedPreferences 中 Token信息。
     *
     * @param context 应用程序上下文环境
     */
    public static void clear(Context context) {
        if (null == context) {
            return;
        }

        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}