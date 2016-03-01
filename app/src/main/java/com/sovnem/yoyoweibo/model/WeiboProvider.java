package com.sovnem.yoyoweibo.model;

import android.content.Context;

import com.sovnem.data.biz.WeiboManager;
import com.sovnem.data.net.RequestListener;

/**
 * a provider of anything about weibo
 * like get weibo list,send a weibo message,get comments of a weibo message .etc
 * Created by sovnem on 16/1/2.
 */
public class WeiboProvider {
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

    /**
     * 获取最新的微博  即比指定的id小的微博
     *
     * @param newest
     * @param context
     * @param requestListener
     */
    public static void getFriendsWeibosAfter(String newest, Context context, RequestListener requestListener) {
        WeiboManager wm = new WeiboManager(context);
        wm.getFriendTimelineAfter(Long.parseLong(newest), requestListener);
    }

    /**
     * 获取指定id之前的微博
     *
     * @param max
     * @param context
     * @param page
     * @param requestListener
     */
    public static void getFriendsWeibosBefore(String max, Context context, int page, RequestListener requestListener) {
        WeiboManager wm = new WeiboManager(context);
        wm.getFriendTimelineBefore(Long.parseLong(max), page, requestListener);
    }

}
