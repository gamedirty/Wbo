package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sovnem.data.standard.RequestListener;
import com.sovnem.data.utils.L;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.model.WeiboProvider;
import com.sovnem.yoyoweibo.view.adapter.StatussAdapter;

/**
 * 首页fragment
 * Created by 赵军辉 on 2015/12/31.
 * <p/>
 * 加载更多和  加载最新
 * <p/>
 * <p/>
 * 加载更多是
 */
public class FirstFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TITLE = "首页";
    private ListView mlv;
    private SwipeRefreshLayout srl;

    @Override
    public void setHead(TextView title) {
        super.setHead(title);
        title.setText(TITLE);
    }

    public static FirstFragment getInstance() {
        FirstFragment ff = new FirstFragment();
        return ff;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void initViews() {
        mlv = (ListView) getView().findViewById(R.id.listview_first_list);
        srl = (SwipeRefreshLayout) getView().findViewById(R.id.srfl_firstpage_refresh);
        srl.setColorSchemeColors(getResources().getColor(R.color.globalcolornormal), getResources().getColor(R.color.globalcolorpress));
        srl.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(true);
                WeiboProvider.getFriendsWeibos(getActivity(), 20, 1, new RequestListener<String>() {
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
        });
    }

    private void showData(String s) {
        srl.setRefreshing(false);
        StatusList statuss = StatusList.parse(s);
        StatussAdapter adapter = new StatussAdapter(getActivity(), statuss.statusList);
        mlv.setAdapter(adapter);
        mlv.setOnScrollListener(adapter);
    }

    @Override
    public void onRefresh() {

    }
}
