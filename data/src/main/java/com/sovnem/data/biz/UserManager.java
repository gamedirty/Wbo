package com.sovnem.data.biz;

import android.content.Context;

import com.sovnem.data.DataConstants;
import com.sovnem.data.net.RequestListener;

import java.util.HashMap;

/**
 * manage user's infomation
 * Created by sovnem on 16/1/2.
 */
public class UserManager extends BaseManager {
    public UserManager(Context context) {
        super(context);
    }

    /**
     * obtain user's infomation by uid or nickname{@params screenName}
     *
     * @param uid
     * @param screenName
     * @param listener
     */
    public void getUserInfo(String uid, String screenName, RequestListener listener) {
        HashMap<String, String> params = new HashMap<>();
        addTokenTo(params);
        params.put("uid", "" + uid);
        params.put("screen_name", screenName);
        String url = DataConstants.getUserinfo;
        doGetRequest(params, url, listener);
    }
}
