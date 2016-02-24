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
import com.sovnem.data.DataConstants;
import com.sovnem.data.biz.TokenManager;
import com.sovnem.yoyoweibo.R;
import com.sovnem.yoyoweibo.app.WoodApplication;
import com.sovnem.yoyoweibo.utils.T;

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



    public void showPublics(View view) {
        Intent intent = new Intent(this, TimelineActivity.class);
        startActivity(intent);
    }

}
