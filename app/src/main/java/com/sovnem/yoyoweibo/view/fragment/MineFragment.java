package com.sovnem.yoyoweibo.view.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.sovnem.data.bean.User;
import com.sovnem.data.net.RequestListener;
import com.sovnem.data.utils.L;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.model.UserProvider;

import java.util.ArrayList;

/**
 * 我的
 * Created by 赵军辉 on 2015/12/31.
 */
public class MineFragment extends BaseFragment {
    public static final String TITLE = "我的";
    private SwipeRefreshLayout wrl;
    private RelativeLayout flCover;
    private ImageView ivHead;
    private TextView tvNickname, tvLocation, tvFlowCount, tvFrdCount, tvStsCount, tvDesc;
    private TabLayout tabTop, tabNormal;
    private ViewPager vpMine;

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
        flCover = (RelativeLayout) getView().findViewById(R.id.framelayout_mine_cover);
        ivHead = (ImageView) getView().findViewById(R.id.imageview_mine_head);
        tvNickname = (TextView) getView().findViewById(R.id.textview_mine_nickname);
        tvNickname.getPaint().setFakeBoldText(true);
        tvLocation = (TextView) getView().findViewById(R.id.textview_mine_location);
        tvFlowCount = (TextView) getView().findViewById(R.id.textview_mine_followerscount);
        tvFrdCount = (TextView) getView().findViewById(R.id.textview_mine_friendscount);
        tvStsCount = (TextView) getView().findViewById(R.id.textview_mine_statuscount);
        tvDesc = (TextView) getView().findViewById(R.id.textview_mine_description);

        initTabLayout();

    }

    private void initTabLayout() {

        tabTop = (TabLayout) getView().findViewById(R.id.tabs_mine_top);
        tabNormal = (TabLayout) getView().findViewById(R.id.tabs_mine_normal);

        vpMine = (ViewPager) getView().findViewById(R.id.viewpager_mine_vp);

        fragments = new ArrayList<>();
        MyTimelineFragment myTimelineFragment = MyTimelineFragment.getInstance();
        MyPhotosFragment myPhotosFragment = MyPhotosFragment.getInstance();
        MyFavoritesFragment myFavoritesFragment = MyFavoritesFragment.getInstance();
        fragments.add(myTimelineFragment);
        fragments.add(myPhotosFragment);
        fragments.add(myFavoritesFragment);
        L.e("到底是不是为空:" + (getFragmentManager() == null));
        SimplePagerAdapter pagerAdapter = new SimplePagerAdapter(getFragmentManager());
        vpMine.setAdapter(pagerAdapter);
        tabNormal.setupWithViewPager(vpMine);
        tabNormal.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private static final String[] tabTitles = new String[]{"微博", "相册", "收藏"};

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
                UserProvider.getCurrentUserInfomation(getActivity(), new RequestListener() {
                    @Override
                    public void onSuccess(String s) {
                        wrl.setRefreshing(false);
                        User user = new Gson().fromJson(s, User.class);
                        L.i("获取到的用户信息:" + user.toString());
                        fillData(user);
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
        Drawable drawable = getResources().getDrawable(isMale ? R.mipmap.icon_sex_male : R.mipmap.icon_sex_famale);
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
