package com.sovnem.yoyoweibo.view.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.app.YoApp;
import com.sovnem.yoyoweibo.base.BaseActivity;
import com.sovnem.yoyoweibo.view.activity.LoginActivity;
import com.sovnem.yoyoweibo.view.activity.MainActivity;

import gamedirty.com.lib_support.utils.L;

public class SplashActivity extends BaseActivity {
    private TextView tvT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvT = (TextView) findViewById(R.id.tvTexiao);
        anim();
    }

    private void anim() {
        ValueAnimator a = ValueAnimator.ofInt(Color.GREEN, Color.GREEN);
        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = Integer.parseInt(animation.getAnimatedValue().toString());
                tvT.setTextColor(value);
            }
        });
        a.setRepeatCount(1);
        a.setDuration(1000);
        a.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                toMain();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        a.start();

    }

    private void toMain() {
        Intent intent = new Intent();
        boolean hasLogin = YoApp.getInstance().hasLogin();
        if (hasLogin) {
            L.i("已经登陆");
            intent.setClass(this, MainActivity.class);
        } else {
            L.i("未登陆");
            intent.setClass(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
