package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sovnem.data.utils.L;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.model.WeiboProvider;
import com.sovnem.yoyoweibo.utils.T;
import com.sovnem.yoyoweibo.view.adapter.StatussAdapter;
import com.sovnem.yoyoweibo.widget.LoadMoreListview;

import java.util.ArrayList;

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
    private LoadMoreListview mlv;
    private SwipeRefreshLayout srl;
    private ArrayList<Status> statuses;
    private String newest, oldest;//最新微博和最老微博的id
    private StatussAdapter adapter;
    private int preLast;//记录上一次

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
        mlv = (LoadMoreListview) getView().findViewById(R.id.listview_first_list);
        srl = (SwipeRefreshLayout) getView().findViewById(R.id.srfl_firstpage_refresh);
        srl.setColorSchemeColors(getResources().getColor(R.color.globalcolornormal), getResources().getColor(R.color.globalcolorpress));
        srl.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        statuses = new ArrayList<>();
        firstRequest();
    }

    /**
     * 第一次请求
     */
    private void firstRequest() {
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(true);
                isLoading = true;
                WeiboProvider.getFriendsWeibos(getActivity(), new RequestListener() {
                    @Override
                    public void onComplete(String s) {
                        showNewData(s);
                        isLoading = false;
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        isLoading = false;
                    }
                });
            }
        });
    }

    private void showNewData(String s) {
        srl.setRefreshing(false);
        StatusList statuss = StatusList.parse(s);
        statuses.addAll(0, statuss.statusList);
        recordId();
        adapter = new StatussAdapter(getActivity(), statuss.statusList);
        mlv.setAdapter(adapter);
        mlv.setOnScrollListener(scrollListener);
    }

    /**
     * 记录 微博id
     */
    private void recordId() {
        newest = statuses.get(0).id;
        oldest = statuses.get(statuses.size() - 1).id;
    }

    /**
     * 下拉刷新回调，在这里加载最新的微博
     */
    @Override
    public void onRefresh() {
        isLoading = true;
        WeiboProvider.getFriendsWeibosAfter(newest, getActivity(), new RequestListener() {
            @Override
            public void onComplete(String s) {
                isLoading = false;
                addNewestStatus(s);
            }

            @Override
            public void onWeiboException(WeiboException e) {
                isLoading = false;
                L.i(e.getMessage());
            }
        });
    }

    /**
     * 加入最新微博并更新列表
     *
     * @param s
     */
    private void addNewestStatus(String s) {
        srl.setRefreshing(false);
        StatusList list = StatusList.parse(s);


        if (list.statusList == null || list.statusList.size() == 0) {
            T.show(getActivity(), "没有新微博", Toast.LENGTH_LONG);
            return;
        }
        newest = list.statusList.get(0).id;
        if (adapter != null) {
            adapter.addNewStatus(list.statusList);
        }
    }


    boolean canLoad;
    private boolean isLoading;
    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == SCROLL_STATE_TOUCH_SCROLL || scrollState == SCROLL_STATE_FLING) {
                Glide.with(getActivity()).pauseRequests();
            } else {
                Glide.with(getActivity()).resumeRequests();
            }
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                    && !isLoading && canLoad) {
                loadMore();
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            canLoad = false;
            if ((firstVisibleItem + visibleItemCount) >= totalItemCount) {
                canLoad = true;
            }
        }
    };

    /**
     * 加载更多
     */
    private void loadMore() {
        isLoading = true;
        mlv.setStatusLoading(true);
        WeiboProvider.getFriendsWeibosBefore(oldest, getActivity(), new RequestListener() {
            @Override
            public void onComplete(String s) {
                L.d("请求成功返回：" + s);
                addOldStatuss(s);
                mlv.setStatusLoading(false);
                isLoading = false;
            }

            @Override
            public void onWeiboException(WeiboException e) {
                mlv.setStatusLoading(false);
                isLoading = false;
            }
        });
    }

    private void addOldStatuss(String s) {
//        L.i("加载更多返回值:" + s);
        StatusList list = StatusList.parse(s);
        if (list.statusList == null) {
            return;
        }
        L.i("返回的个数是:" + list.statusList.size());
        list.statusList.remove(0);
        adapter.addOldStatuses(list.statusList);
        oldest = list.statusList.get(list.statusList.size() - 1).id;
    }
}
