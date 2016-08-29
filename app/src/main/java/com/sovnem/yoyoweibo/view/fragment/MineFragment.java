package com.sovnem.yoyoweibo.view.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gamedirty.bean.User;
import com.gamedirty.global.Constants;
import com.google.gson.Gson;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.app.TokenManager;
import com.sovnem.yoyoweibo.net.HttpManager;
import com.sovnem.yoyoweibo.net.RequestListener;

import java.util.ArrayList;
import java.util.HashMap;

import gamedirty.com.lib_support.utils.L;
import gamedirty.com.lib_widgets.widget.LoadMoreListview;

/**
 * 我的
 * Created by 赵军辉 on 2015/12/31.
 */
public class MineFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TITLE = "我的";
    private SwipeRefreshLayout wrl;
    private RelativeLayout flCover;
    private ImageView ivHead;
    private TextView tvNickname, tvLocation, tvFlowCount, tvFrdCount, tvStsCount, tvDesc;
    private LoadMoreListview lvContainer;

    @Override
    public void setHead(TextView title) {
        super.setHead(title);
        title.setText(TITLE);
    }

    public static MineFragment getInstance() {
        return new MineFragment();
    }


    ArrayList<Fragment> fragments;

    @Override
    public void initViews() {
        wrl = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh_mine_srl);
        wrl.setOnRefreshListener(this);
        lvContainer = (LoadMoreListview) getView().findViewById(R.id.listview_mine_container);
        View headView = View.inflate(getActivity(), R.layout.layout_mine_headview, null);
        lvContainer.addHeaderView(headView);
        flCover = (RelativeLayout) getView().findViewById(R.id.framelayout_mine_cover);
        ivHead = (ImageView) getView().findViewById(R.id.imageview_mine_head);
        tvNickname = (TextView) getView().findViewById(R.id.textview_mine_nickname);
        tvNickname.getPaint().setFakeBoldText(true);
        tvLocation = (TextView) getView().findViewById(R.id.textview_mine_location);
        tvFlowCount = (TextView) getView().findViewById(R.id.textview_mine_followerscount);
        tvFrdCount = (TextView) getView().findViewById(R.id.textview_mine_friendscount);
        tvStsCount = (TextView) getView().findViewById(R.id.textview_mine_statuscount);
        tvDesc = (TextView) getView().findViewById(R.id.textview_mine_description);
//        initTabLayout();
        initListview();

    }

    private void initListview() {
        ArrayList<String> data = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            data.add("我就是哈哈:" + i);
//        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        lvContainer.setAdapter(adapter);
    }

    private void initTabLayout() {
        fragments = new ArrayList<>();
        MyTimelineFragment myTimelineFragment = MyTimelineFragment.getInstance();
        MyPhotosFragment myPhotosFragment = MyPhotosFragment.getInstance();
        MyFavoritesFragment myFavoritesFragment = MyFavoritesFragment.getInstance();
        fragments.add(myTimelineFragment);
        fragments.add(myPhotosFragment);
        fragments.add(myFavoritesFragment);
        SimplePagerAdapter pagerAdapter = new SimplePagerAdapter(getFragmentManager());
    }

    private static final String[] tabTitles = new String[]{"微博", "相册", "收藏"};

    @Override
    public void onRefresh() {
        initData();
    }

    class SimplePagerAdapter extends FragmentPagerAdapter {

        public SimplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }


    @Override
    public void initData() {

        wrl.post(new Runnable() {
            @Override
            public void run() {
                wrl.setRefreshing(true);
                L.i("获取用户信息");
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("access_token", "" + TokenManager.getToken(getActivity()));
                params.put("uid", "" + TokenManager.getUid(getActivity()));


                HttpManager.doGetRequest(getActivity(), params, Constants.getUserinfo, new RequestListener() {
                    @Override
                    public void onRequestSuccess(String result) {
                        wrl.setRefreshing(false);
                        User user = new Gson().fromJson(result, User.class);
                        fillData(user);
                    }

                    @Override
                    public void onRequestError(String errMsg) {
                        wrl.setRefreshing(false);
                    }

                    @Override
                    public void onNetError() {
                        wrl.setRefreshing(false);
                    }
                });
            }
        });
        lvContainer.setStatusLoading();
    }

    /**
     * 获取的用户信息填充到视图中
     *
     * @param user
     */
    private void fillData(User user) {
        SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                flCover.setBackground(new BitmapDrawable(getResources(), resource));
            }
        };
        Glide.with(getActivity()).load(user.cover_image_phone).asBitmap().into(target);
        Glide.with(getActivity()).load(user.avatar_large).dontAnimate().into(ivHead);

        boolean isMale = "m".equals(user.gender);
        Drawable drawable = getResources().getDrawable(isMale ? R.drawable.icon_sex_male : R.drawable.icon_sex_famale);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvNickname.setCompoundDrawables(null, null, drawable, null);
        tvDesc.setText(user.description);
        tvLocation.setText(user.location);
        tvFlowCount.append(Html.fromHtml("<font color='#9EC668'>" + user.followers_count
                + "</font>"));
        tvFrdCount.append(Html.fromHtml("<font color='#9EC668'>" + user.friends_count
                + "</font>"));
        tvStsCount.append(Html.fromHtml("<font color='#9EC668'>" + user.statuses_count
                + "</font>"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, null);
    }
}
