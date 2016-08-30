package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamedirty.bean.Status;
import com.sovnem.yoyoweibo.imvp.presener.ITimelinePresener;
import com.sovnem.yoyoweibo.imvp.view.ITimelineView;
import com.sovnem.yoyoweibo.presener.TimelinePresener;

import java.util.Collection;

/**
 * @author zjh
 * @description
 * @date 16/8/30.
 */
public class MainFragment extends BaseFragment implements ITimelineView {
    private ITimelinePresener iTimelinePresener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iTimelinePresener = new TimelinePresener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showInitLoading() {

    }

    @Override
    public void showLoadmore() {

    }

    @Override
    public void stopInitLoading() {

    }

    @Override
    public void stopLoadingmore() {

    }

    @Override
    public void showData(Collection<Status> statuses) {

    }

    @Override
    public void addData(Collection<Status> statuses) {

    }

    @Override
    public void showNomoreData() {

    }

    @Override
    public void initLoadError() {

    }

    @Override
    public void loadmoreError() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }
}
