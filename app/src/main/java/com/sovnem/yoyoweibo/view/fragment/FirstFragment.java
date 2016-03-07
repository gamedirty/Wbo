package com.sovnem.yoyoweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.app.AppConfig;
import com.sovnem.yoyoweibo.app.Constants;
import com.sovnem.yoyoweibo.bean.Status;
import com.sovnem.yoyoweibo.bean.StatusList;
import com.sovnem.yoyoweibo.net.HttpManager;
import com.sovnem.yoyoweibo.net.RequestListener;
import com.sovnem.yoyoweibo.utils.L;
import com.sovnem.yoyoweibo.utils.T;
import com.sovnem.yoyoweibo.utils.TokenManager;
import com.sovnem.yoyoweibo.view.adapter.StatusAdapter;
import com.sovnem.yoyoweibo.widget.LoadMoreListview;

import java.util.ArrayList;
import java.util.HashMap;

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
        mlv.setLoadMoreWork(new LoadMoreListview.LoadMoreWork() {
            @Override
            public void work() {
                loadMore();
            }
        });
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
                getFirstpageWeibos();
            }
        });
    }

    private void getFirstpageWeibos() {
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", "" + TokenManager.getToken(getActivity()));
        params.put("count", "" + AppConfig.PAGE_COUNT);
        isLoading = true;
        L.i("第一次请求");
        HttpManager.doGetRequest(getActivity(), params, Constants.getFriends_timeline, new MyRequestListener(MyRequestListener.TYPE_DEFALTLOAD));
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
        if (adapter == null) {
            getFirstpageWeibos();
        }
        isLoading = true;

        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", "" + TokenManager.getToken(getActivity()));
        params.put("count", "" + AppConfig.PAGE_COUNT);
        if (!TextUtils.isEmpty(newest))
            params.put("since_id", "" + newest);
        HttpManager.doGetRequest(getActivity(), params, Constants.getFriends_timeline, new MyRequestListener(MyRequestListener.TYPE_REFRESH));
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
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", "" + TokenManager.getToken(getActivity()));
        params.put("count", "" + AppConfig.PAGE_COUNT);
        params.put("max_id", "" + oldest);
        params.put("page", "" + page);
        HttpManager.doGetRequest(getActivity(), params, Constants.getFriends_timeline, new MyRequestListener(MyRequestListener.TYPE_LOADMORE));
    }

    private void addOldStatuss(String s) {
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

    class MyRequestListener implements RequestListener {
        static final int TYPE_DEFALTLOAD = 1;
        static final int TYPE_REFRESH = 2;
        static final int TYPE_LOADMORE = 3;
        int type;//

        MyRequestListener(int type) {
            this.type = type;
        }

        @Override
        public void onRequestSuccess(String result) {
            isLoading = false;
            switch (type) {
                case TYPE_DEFALTLOAD:
                    showNewData(result);
                    break;
                case TYPE_LOADMORE:
                    addOldStatuss(result);
                    page++;
                    break;
                case TYPE_REFRESH:
                    addNewestStatus(result);
                    break;
            }
        }

        @Override
        public void onRequestError(String errMsg) {
            isLoading = false;
            T.show(getActivity(), R.string.generic_error, Toast.LENGTH_SHORT);
            switch (type) {
                case TYPE_DEFALTLOAD:
                    srl.setRefreshing(false);
                    break;
                case TYPE_LOADMORE:
                    mlv.setStatusLoading();
                    break;
                case TYPE_REFRESH:
                    srl.setRefreshing(false);
                    break;
            }
        }

        @Override
        public void onNetError() {

            isLoading = false;

            switch (type) {
                case TYPE_DEFALTLOAD:
                    srl.setRefreshing(false);
                    T.show(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT);
                    break;
                case TYPE_LOADMORE:
                    mlv.setStatusLoadMoreError();
                    break;
                case TYPE_REFRESH:
                    T.show(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT);
                    srl.setRefreshing(false);
                    break;
            }
        }
    }
}
