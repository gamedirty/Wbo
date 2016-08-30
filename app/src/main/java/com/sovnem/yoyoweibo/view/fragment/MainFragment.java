package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sovnem.yoyoweibo.imvp.view.ITimelineView;

/**
 * @author zjh
 * @description
 * @date 16/8/30.
 */
public class MainFragment extends Fragment implements ITimelineView{
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
}
