package com.sovnem.data.net;

/**
 * Created by 赵军辉 on 2016/2/23.
 */
public interface RequestListener {
    public void onSuccess(String s);

    public void onFailed(String failMsg);

    public void onNetError();
}
