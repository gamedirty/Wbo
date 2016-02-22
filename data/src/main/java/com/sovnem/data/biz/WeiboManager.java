package com.sovnem.data.biz;

import android.content.Context;

import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;
import com.sovnem.data.DataConstants;

/**
 * a manager of weibo messages,here is all the methods about weibo you need
 * Created by sovnem on 16/1/2.
 */
public class WeiboManager extends BaseManager {

    private StatusesAPI statusesAPI;

    public WeiboManager(Context context) {
        super(context);
        statusesAPI = new StatusesAPI(context, DataConstants.APP_KEY, TokenManager.readAccessToken(context));
    }

    public void getFriendTimeline(long since, long max, RequestListener listener) {
        statusesAPI.friendsTimeline(since, max, 20, 1, false, 0, false, listener);



    }

    public void getFriendTimelineBefore(long max, RequestListener listener) {
        statusesAPI.friendsTimeline(0, max, 20, 1, false, 0, false, listener);
    }

    public void getFriendTimelineAfter(long since, RequestListener listener) {
        statusesAPI.friendsTimeline(since, 0, 20, 1, false, 0, false, listener);
    }


}