package com.sovnem.data.biz;

import android.content.Context;

import com.sovnem.data.DataConstants;
import com.sovnem.data.net.RequestListener;
import com.sovnem.data.utils.L;

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
     * 设置微博获取 相关的基础配置参数
     *
     * @param params
     */
    protected void initTimelineGetParams(HashMap params) {
        addTokenTo(params);
        params.put("source", "" + DataConstants.APP_KEY);
        params.put("count", DataConstants.PAGE_COUNT);
        params.put("base_app", 0);
        params.put("feature", 0);//过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
        params.put("trim_user", isWifi() ? 0 : 1);//如果是WiFi返回user的完整字段，否则只返回userid
        L.i("token:" + DataConstants.TOKEN);
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
        initTimelineGetParams(params);
        String baseUrl = DataConstants.getFriends_timeline;
        doGetRequest(params, baseUrl, listener);
    }

    /**
     * 全参数请求方式
     *
     * @param since
     * @param max
     * @param page
     * @param listener
     */
    public void getPublicTimeline(long since, long max, int page, RequestListener listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("since_id", "" + since);
        params.put("max_id", "" + max);
        params.put("page", "" + page);
        initTimelineGetParams(params);
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

    /**
     * 默认
     *
     * @param since
     * @param max
     * @param listener
     */
    public void getPublicTimeline(long since, long max, RequestListener listener) {
        getFriendTimeline(since, max, 1, listener);
    }


    public void getFriendTimelineBefore(long max, int page, RequestListener listener) {
        getFriendTimeline(0, max, page, listener);
    }

    public void getFriendTimelineAfter(long since, RequestListener listener) {
        getFriendTimeline(since, 0, 1, listener);
    }


    public void getTimelineOfUser() {
    }

}