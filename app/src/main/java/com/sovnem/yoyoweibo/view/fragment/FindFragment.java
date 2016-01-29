package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sovnem.yoyoweibo.R;

/**
 * 发现微博 推荐微博
 * Created by 赵军辉 on 2015/12/31.
 */
public class FindFragment extends BaseFragment {
    private static final String TITLE = "发现";

    public static FindFragment getInstance() {
        FindFragment ff = new FindFragment();
        return ff;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, null);
    }

    @Override
    public void setHead(TextView title) {
        super.setHead(title);
        title.setText(TITLE);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }
}
