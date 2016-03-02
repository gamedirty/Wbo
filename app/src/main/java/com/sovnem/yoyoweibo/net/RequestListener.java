package com.sovnem.yoyoweibo.net;

/**
 * Created by 赵军辉 on 2016/2/23.
 */
public interface RequestListener {
    /**
     * 请求成功
     *
     * @param s 结果
     */
    public void onSuccess(String s);

    /**
     * 请求失败 服务器错误
     *
     * @param failMsg
     */
    public void onFailed(String failMsg);

    /**
     * 无网络连接
     */
    public void onNetError();
}
