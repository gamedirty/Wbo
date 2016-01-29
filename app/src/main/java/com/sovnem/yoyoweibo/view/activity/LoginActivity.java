package com.sovnem.yoyoweibo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sovnem.data.DataConstants;
import com.sovnem.data.biz.TokenManager;
import com.sovnem.data.standard.RequestListener;
import com.sovnem.data.utils.L;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.app.WoodApplication;
import com.sovnem.yoyoweibo.model.WeiboProvider;
import com.sovnem.yoyoweibo.utils.T;

import java.util.ArrayList;

/**
 * 登陆activity  包括登陆按钮和 随便看看按钮
 */
public class LoginActivity extends BaseActivity {

    private SsoHandler ssoHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view) {
        AuthInfo ai = new AuthInfo(this, DataConstants.APP_KEY, DataConstants.REDIRECT_URL, DataConstants.SCOPE);
        ssoHandler = new SsoHandler(this, ai);
        ssoHandler.authorize(new AuthListener());
    }

    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {

            // 从 Bundle 中解析 Token
            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {

                TokenManager.writeAccessToken(LoginActivity.this, mAccessToken);
                DataConstants.TOKEN = mAccessToken.getToken();
                WoodApplication.hasLogin = true;
                Toast.makeText(LoginActivity.this,
                        R.string.auth_success, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancel() {
            T.show(LoginActivity.this, R.string.auth_cancle, Toast.LENGTH_SHORT);
        }

        @Override
        public void onWeiboException(WeiboException e) {
            T.show(LoginActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 获取公共微博信息
     *
     * @param view
     */
    public void getPublicWeibos(View view) {
        WeiboProvider.getPublicWeibos(LoginActivity.this, 20, 1, new RequestListener<String>() {
            @Override
            public void onRequestSuccess(String s) {
                L.i("请求成功，结果：" + s);
            }

            @Override
            public void onRequestFail(String msg) {
                L.i("请求失败:" + msg);
            }

            @Override
            public void onNetError() {
                L.i("网络错误");
            }
        });
    }

    public void getFriendWeibos(View view) {
        WeiboProvider.getFriendsWeibos(this, 30, 1, new RequestListener<String>() {
            @Override
            public void onRequestSuccess(String s) {
                StatusList list = StatusList.parse(s);
                ArrayList<Status> staturss = list.statusList;
                for (Status ss : staturss) {
                    L.i("请求回来的数据:" + ss.toString());
                }
                L.i("关注的人微博：" + s);
            }

            @Override
            public void onRequestFail(String msg) {
                L.i("请求朋友微博出错");
            }

            @Override
            public void onNetError() {
                L.i("网络错误");
            }
        });
    }


    public void showPublics(View view) {
        Intent intent = new Intent(this, TimelineActivity.class);
        startActivity(intent);
    }

}
