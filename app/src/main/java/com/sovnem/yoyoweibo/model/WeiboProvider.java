package com.sovnem.yoyoweibo.model;

import android.content.Context;

import com.sina.weibo.sdk.net.RequestListener;
import com.sovnem.data.biz.WeiboManager;

/**
 * a provider of anything about weibo
 * like get weibo list,send a weibo message,get comments of a weibo message .etc
 * Created by sovnem on 16/1/2.
 */
public class WeiboProvider {
    private static final String REQUEST_PUBLIC_TIMELINE = "https://api.weibo.com/2/statuses/public_timeline.json";


    /**
     * 获取关注的人的微博
     * <p/>
     * 先看数据库里是不是有需要的内容，如果数据库没有，从网络获取，返回并把获取到的内容存入数据库
     *
     * @param context
     * @param requestListener
     */
    public static void getFriendsWeibos(Context context, RequestListener requestListener) {
        WeiboManager wm = new WeiboManager(context);
        wm.getFriendTimeline(0, 0, requestListener);
    }

    public static void getFriendsWeibosAfter(String newest, Context context, RequestListener requestListener) {
        WeiboManager wm = new WeiboManager(context);
        wm.getFriendTimelineAfter(Long.parseLong(newest), requestListener);
    }


}
