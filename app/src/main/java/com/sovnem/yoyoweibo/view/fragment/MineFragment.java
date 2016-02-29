package com.sovnem.yoyoweibo.view.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private RelativeLayout flCover;
    private ImageView ivHead;
    private TextView tvNickname, tvLocation, tvFlowCount, tvFrdCount, tvStsCount, tvDesc;
    private TabLayout tabTop, tabNormal;

    @Override
    public void setHead(TextView title) {
        super.setHead(title);
        title.setText(TITLE);
    }

    public static MineFragment getInstance() {
        MineFragment ff = new MineFragment();
        return ff;
    }


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

        tabTop = (TabLayout) getView().findViewById(R.id.tabs_mine_top);
        tabNormal = (TabLayout) getView().findViewById(R.id.tabs_mine_normal);

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
                flCover.setBackground(new BitmapDrawable(resource));
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

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, null);
    }
}
