package com.sovnem.yoyoweibo.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sovnem.data.standard.RequestListener;
import com.sovnem.data.utils.L;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.model.WeiboProvider;
import com.sovnem.yoyoweibo.view.adapter.StatusAdapter;

public class TimelineActivity extends BaseActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        initView();
        initData();
    }

    private void initData() {
        WeiboProvider.getFriendsWeibos(this, 50, 1, new RequestListener<String>() {
            @Override
            public void onRequestSuccess(String s) {
                showData(s);
            }

            @Override
            public void onRequestFail(String msg) {
            }

            @Override
            public void onNetError() {
                L.i("出错了 我操");
            }
        });
    }

    private void showData(String s) {
        StatusList statuss = StatusList.parse(s);
        StatusAdapter adapter = new StatusAdapter(this, statuss.statusList);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_timeline);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
