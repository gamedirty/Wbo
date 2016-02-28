package com.sovnem.yoyoweibo.model;

import android.content.Context;

import com.sovnem.data.biz.UserManager;
import com.sovnem.data.net.RequestListener;

/**
 * user信息内容管理者
 * Created by sovnem on 16/2/28.
 */
public class UserProvider {
    public static void getUserInfomation(Context context, String uid, String screenName, RequestListener listener) {
        UserManager userManager = new UserManager(context);
        userManager.getUserInfo(uid, screenName, listener);
    }
}
