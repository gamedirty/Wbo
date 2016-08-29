package com.sovnem.yoyoweibo.mvp.view;

/**
 * Created by sovnem on 16/8/29,23:16.
 */
public interface ITimelineView {
    void showInitLoading();

    void showLoadmore();

    void stopInitLoading();

    void stopLoadingmore();
}
