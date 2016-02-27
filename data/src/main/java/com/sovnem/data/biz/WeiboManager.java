package com.sovnem.data.biz;

import android.content.Context;

import com.sovnem.data.DataConstants;
import com.sovnem.data.net.RequestListener;

import java.util.HashMap;

/**
 * a manager of weibo messages,here is all the methods about weibo's messages you need
 * Created by sovnem on 16/1/2.
 */
public class WeiboManager extends BaseManager {


    public WeiboManager(Context context) {
        super(context);
    }

    /**
     * 全参数请求方式
     *
     * @param since
     * @param max
     * @param page
     * @param listener
     */
    public void getFriendTimeline(long since, long max, int page, RequestListener listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("since_id", "" + since);
        params.put("max_id", "" + max);
        params.put("page", "" + page);
        String baseUrl = DataConstants.getFriends_timeline;
        doGetRequest(params, baseUrl, listener);
    }

    /**
     * 默认
     *
     * @param since
     * @param max
     * @param listener
     */
    public void getFriendTimeline(long since, long max, RequestListener listener) {
        getFriendTimeline(since, max, 1, listener);
    }


    public void getFriendTimelineBefore(long max, int page, RequestListener listener) {
        getFriendTimeline(0, max, page, listener);
    }

    public void getFriendTimelineAfter(long since, RequestListener listener) {
    }


}