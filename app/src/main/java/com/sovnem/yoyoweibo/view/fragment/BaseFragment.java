package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sovnem.yoyoweibo.R;

/**
 * fragment基类
 * 控制fragment的特征
 * Created by 赵军辉 on 2015/12/31.
 */
public abstract class BaseFragment extends Fragment {
    private View head;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initHead();
        initViews();
        initData();
    }

    private void initHead() {
        head = getView().findViewById(R.id.head);
        if (head == null) {
            try {
                throw new Exception("fragment的布局应该包含名为head的布局");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Toolbar toolbar = (Toolbar) head;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        TextView title = (TextView) head.findViewById(R.id.textview_head_middletext);
        setHead(title);

    }

    public void setHead(TextView title) {

    }

    public abstract void initViews();

    public abstract void initData();


}
