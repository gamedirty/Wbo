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
import com.google.gson.Gson;
import com.sovnem.data.bean.Status;
import com.sovnem.data.bean.StatusList;
import com.sovnem.data.net.RequestListener;
import com.sovnem.data.utils.L;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.model.WeiboProvider;
import com.sovnem.yoyoweibo.utils.T;
import com.sovnem.yoyoweibo.view.adapter.StatusAdapter;
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
 * 第一次获取之后记录最老的id
 * 分页加载从这个id之前导数加载各个页的数据
 */
public class FirstFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TITLE = "首页";
    private LoadMoreListview mlv;
    private SwipeRefreshLayout srl;
    private ArrayList<Status> statuses;
    private String newest, oldest;//最新微博和最老微博的id
    private StatusAdapter adapter;
    private int preLast;//记录上一次
    private int page = 1;//

    @Override
    public void setHead(TextView title) {
        super.setHead(title);
        title.setText(TITLE);
    }

    public static FirstFragment getInstance() {

        return new FirstFragment();
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
                    public void onSuccess(String ts) {
                        showNewData(ts);
                        isLoading = false;
                    }

                    @Override
                    public void onFailed(String failMsg) {
                        isLoading = false;
                    }

                    @Override
                    public void onNetError() {
                        isLoading = false;
                    }
                });
            }
        });
    }

    private void showNewData(String s) {
        srl.setRefreshing(false);
        Gson gson = new Gson();
        StatusList statuss = gson.fromJson(s, StatusList.class);
        statuses.addAll(0, statuss.statuses);
        recordId();
        adapter = new StatusAdapter(getActivity(), statuses);
        mlv.setAdapter(adapter);
        mlv.setOnScrollListener(scrollListener);
    }

    /**
     * 记录 微博id
     * 只记录一次
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
            public void onSuccess(String s) {
                isLoading = false;
                addNewestStatus(s);
            }

            @Override
            public void onFailed(String failMsg) {

                L.i(failMsg);
            }

            @Override
            public void onNetError() {
                isLoading = false;
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
        StatusList list = new Gson().fromJson(s, StatusList.class);
        if (list.statuses == null || list.statuses.size() == 0) {
            T.show(getActivity(), "没有新微博", Toast.LENGTH_LONG);
            return;
        }
        newest = list.statuses.get(0).id + "";
        if (adapter != null) {
            adapter.addNewStatus(list.statuses);
        }
    }


    boolean canLoad;
    private boolean isLoading;
    private boolean noMore;
    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

            if (scrollState == SCROLL_STATE_TOUCH_SCROLL || scrollState == SCROLL_STATE_FLING) {
                Glide.with(getActivity()).pauseRequests();
            } else {
                Glide.with(getActivity()).resumeRequests();
            }
            if (!noMore && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                    && !isLoading && canLoad) {
                loadMore();
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            canLoad = (firstVisibleItem + visibleItemCount) >= totalItemCount;
        }
    };

    /**
     * 加载旧的微博
     */
    private void loadMore() {
        isLoading = true;
        mlv.setStatusLoading();
        WeiboProvider.getFriendsWeibosBefore(oldest, getActivity(), page, new RequestListener() {
            @Override
            public void onSuccess(String s) {
                L.d("请求成功返回：" + s);
                addOldStatuss(s);
                isLoading = false;
                page++;
            }

            @Override
            public void onFailed(String failMsg) {
                mlv.setStatusLoading();
                isLoading = false;
            }

            @Override
            public void onNetError() {
                isLoading = false;
            }
        });
    }

    private void addOldStatuss(String s) {
//        L.i("加载更多返回值:" + s);
        StatusList list = new Gson().fromJson(s, StatusList.class);
        if (list.statuses == null) {
            mlv.setStatusLoadMoreError();
            return;
        }
        int count = list.statuses.size();
        L.i("加载了更多的:" + count);
        if (page == 1)
            list.statuses.remove(0);
        adapter.addOldStatuses(list.statuses);
        if (count == 0) {
            mlv.setStatusNomore();
            noMore = true;
        }
    }
}
