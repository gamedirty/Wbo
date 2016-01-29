package com.sovnem.data.standard;

/**
 * a custom callback for net requests
 * Created by sovnem on 16/1/2.
 */
public interface RequestListener<T> {
    /**
     * 请求成功
     *
     * @param t
     */
    public void onRequestSuccess(T t);

    /**
     * 请求失败
     */
    public void onRequestFail(String msg);

    /**
     * 网络错误
     */
    public void onNetError();

}
