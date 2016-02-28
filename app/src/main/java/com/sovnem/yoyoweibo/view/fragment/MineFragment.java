package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sovnem.data.bean.User;
import com.sovnem.data.biz.TokenManager;
import com.sovnem.data.net.RequestListener;
import com.sovnem.data.utils.L;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.model.UserProvider;

/**
 * 我的
 * Created by 赵军辉 on 2015/12/31.
 */
public class MineFragment extends BaseFragment {
    public static final String TITLE = "我的";
    private SwipeRefreshLayout wrl;

    @Override
    public void setHead(TextView title) {
        super.setHead(title);
        title.setText(TITLE);
    }

    @Override
    public void initViews() {
        wrl = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh_mine_srl);
    }

    @Override
    public void initData() {
        wrl.post(new Runnable() {
            @Override
            public void run() {
                wrl.setRefreshing(true);
                L.i("获取用户信息");
                UserProvider.getUserInfomation(getActivity(), TokenManager.getUid(getActivity()), "", new RequestListener() {
                    @Override
                    public void onSuccess(String s) {
                        wrl.setRefreshing(false);
                        User user = new Gson().fromJson(s, User.class);
                        L.i("获取到的用户信息:" + user.toString());
                    }

                    @Override
                    public void onFailed(String failMsg) {
                        wrl.setRefreshing(false);
                    }

                    @Override
                    public void onNetError() {
                        wrl.setRefreshing(false);
                    }
                });
            }
        });
    }

    public static MineFragment getInstance() {
        MineFragment ff = new MineFragment();
        return ff;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, null);
    }
}
