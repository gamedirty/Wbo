package com.sovnem.yoyoweibo.net;

/**
 * Created by sovnem on 16/3/6.
 */
public interface RequestListener {
    public void onRequestSuccess(String result);

    public void onRequestError(String errMsg);

    public void onNetError();
}
