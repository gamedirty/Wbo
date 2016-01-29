package com.sovnem.data.biz;

import android.content.Context;

import com.sovnem.data.DataConstants;
import com.sovnem.data.standard.RequestListener;

import java.util.HashMap;

/**
 * a manager of weibo messages,here is all the methods about weibo you need
 * Created by sovnem on 16/1/2.
 */
public class WeiboManager extends BaseManager {


    public WeiboManager(Context context) {
        super(context);
    }

    /**
     * 获取公共微博
     *
     * @param count           一页的数量
     * @param page            页数
     * @param requestListener 回调接口
     */
    public void getPublicWeibos(int count, int page, RequestListener<String> requestListener) {
        HashMap params = new HashMap();
        params.put("count", 3 + "");
        params.put("page", 1 + "");
        doGetRequest(params, DataConstants.getPublic_timeline, requestListener);
    }

    /**
     * 获取用户及其关注用户的最新微博
     * 从sinceId到maxId之间的所有微博
     *
     * @param count           单页返回的记录条数，最大不超过100，默认为20。
     * @param page            返回结果的页码，默认为1。
     * @param sinceId         起始id
     * @param maxId           终止id
     * @param feature         过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     * @param trimUser        返回值中user字段开关，0：返回完整user字段、1：user字段仅返回user_id，默认为0。
     * @param requestListener
     */
    public void getFriendsTimeline(int count, int page, int sinceId, int maxId, int feature, int trimUser, RequestListener<String> requestListener) {
        HashMap params = new HashMap();
        params.put("since_id", sinceId + "");
        params.put("max_id", maxId + "");
        params.put("count", count + "");
        params.put("page", page + "");
        params.put("feature", feature + "");
        params.put("trim_user", trimUser + "");
        doGetRequest(params, DataConstants.getFriends_timeline, requestListener);
    }

    /**
     * 获取用户及其关注用户的最新微博
     * 从sinceId到maxId之间的所有微博
     *
     * @param count           单页返回的记录条数，最大不超过100，默认为20。
     * @param page            返回结果的页码，默认为1。
     * @param feature         过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     * @param requestListener
     */
    public void getFriendsTimeline(int count, int page, int feature, RequestListener<String> requestListener) {
        HashMap params = new HashMap();
        params.put("count", count + "");
        params.put("page", page + "");
        params.put("feature", feature + "");
        doGetRequest(params, DataConstants.getFriends_timeline, requestListener);
    }

    /**
     * 获取用户及其关注用户的最新微博
     * 从sinceId到maxId之间的所有微博
     *
     * @param count           单页返回的记录条数，最大不超过100，默认为20。
     * @param page            返回结果的页码，默认为1。
     * @param requestListener
     */
    public void getFriendsTimeline(int count, int page, RequestListener<String> requestListener) {
        HashMap params = new HashMap();
        params.put("count", count + "");
        params.put("page", page + "");
        doGetRequest(params, DataConstants.getFriends_timeline, requestListener);
    }

}