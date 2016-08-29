package com.sovnem.yoyoweibo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sovnem.yoyoweibo.R;
import com.gamedirty.global.Constants;
import com.sovnem.yoyoweibo.app.TokenManager;
import com.sovnem.yoyoweibo.base.BaseActivity;

import gamedirty.com.lib_support.utils.T;

/**
 * 登陆activity  包括登陆按钮和 随便看看按钮
 */
public class LoginActivity extends BaseActivity {

    private SsoHandler ssoHandler;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view) {
        AuthInfo ai = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
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
                Toast.makeText(LoginActivity.this,
                        R.string.auth_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
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


    public void showPublics(View view) {
    }

}
