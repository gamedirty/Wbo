package com.sovnem.yoyoweibo.model;

import android.content.Context;

import com.sovnem.data.biz.WeiboManager;
import com.sovnem.data.standard.RequestListener;

/**
 * a provider of anything about weibo
 * like get weibo list,send a weibo message,get comments of a weibo message .etc
 * Created by sovnem on 16/1/2.
 */
public class WeiboProvider {
    private static final String REQUEST_PUBLIC_TIMELINE = "https://api.weibo.com/2/statuses/public_timeline.json";

    /**
     * 获取公共微博
     *
     * @param context
     * @param count           一页的数量
     * @param page            页数
     * @param requestListener
     */
    public static void getPublicWeibos(Context context, int count, int page, RequestListener<String> requestListener) {
        WeiboManager wm = new WeiboManager(context);
        wm.getPublicWeibos(count, page, requestListener);
    }

    /**
     * 获取关注的人的微博
     *
     * @param context
     * @param count
     * @param page
     * @param requestListener
     */
    public static void getFriendsWeibos(Context context, int count, int page, RequestListener<String> requestListener) {
        WeiboManager wm = new WeiboManager(context);
        wm.getFriendsTimeline(count, page, requestListener);
    }
}
