package com.sovnem.yoyoweibo.imvp.view;

import com.gamedirty.bean.Status;

import java.util.Collection;

/**
 * Created by sovnem on 16/8/29,23:16.
 */
public interface ITimelineView {
    void showInitLoading();

    void showLoadmore();

    void stopInitLoading();

    void stopLoadingmore();

    void showData(Collection<Status> statuses);

    void addData(Collection<Status> statuses);

    void showNomoreData();

    void initLoadError();

    void loadmoreError();


}
