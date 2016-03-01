package com.sovnem.yoyoweibo.model;

import android.content.Context;

import com.sovnem.data.biz.TokenManager;
import com.sovnem.data.biz.UserManager;
import com.sovnem.data.net.RequestListener;

/**
 * user信息内容管理者
 * Created by sovnem on 16/2/28.
 */
public class UserProvider {
    /**
     * 根据uid或者昵称获取用户的信息
     *
     * @param context
     * @param uid        用户id
     * @param screenName 昵称
     * @param listener
     */
    private static void getUserInfomation(Context context, String uid, String screenName, RequestListener listener) {
        UserManager userManager = new UserManager(context);
        userManager.getUserInfo(uid, screenName, listener);
    }

    /**
     * 根据uid获取用户信息
     *
     * @param context
     * @param uid
     * @param listener
     */
    public static void getUserInfomationByUid(Context context, String uid, RequestListener listener) {
        getUserInfomation(context, uid, "", listener);
    }

    /**
     * 根据昵称获取用户信息
     *
     * @param context
     * @param nickname
     * @param listener
     */
    public static void getUserInformationByNickname(Context context, String nickname, RequestListener listener) {
        getUserInfomation(context, "", nickname, listener);
    }


    /**
     * 获取当前登录用户的用户信息
     *
     * @param context
     * @param listener
     */
    public static void getCurrentUserInfomation(Context context, RequestListener listener) {
        getUserInfomationByUid(context, TokenManager.getUid(context), listener);
    }
}
