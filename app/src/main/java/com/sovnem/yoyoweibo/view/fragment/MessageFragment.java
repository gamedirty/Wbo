package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sovnem.yoyoweibo.R;

/**
 * 消息相关fragment
 * Created by 赵军辉 on 2015/12/31.
 */
public class MessageFragment extends BaseFragment {
    public static final String TITLE = "消息";

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

    public static MessageFragment getInstance() {
        MessageFragment ff = new MessageFragment();
        return ff;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, null);
    }
}
