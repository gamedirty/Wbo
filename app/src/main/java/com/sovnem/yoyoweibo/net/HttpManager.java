package com.sovnem.yoyoweibo.net;

import android.app.Activity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sovnem.data.utils.HttpUtil;
import com.sovnem.data.utils.NetUtils;
import com.sovnem.yoyoweibo.app.YoApp;

import java.util.HashMap;

/**
 * 封装的volley的网络请求库
 * Created by 赵军辉 on 2016/3/2.
 */
public class HttpManager {
    private static HttpManager instance;
    private Activity c;
    private static final int TIME_OUT_DURATION = 10 * 1000;

    private HttpManager(Activity context) {
        c = context;
    }

    public static HttpManager newInstance(Activity context) {
        if (instance == null) {
            instance = new HttpManager(context);
        }
        return instance;
    }

    /**
     * 简单的get请求
     *
     * @param params   请求参数
     * @param url      请求地址
     * @param listener 回调接口
     */
    public void doGetRequest(HashMap<String, String> params, String url, final RequestListener listener) {
        url = HttpUtil.makeGetUrl(url, params);
        if (!NetUtils.isConnected(c)) {
            listener.onNetError();
        }
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(VolleyErrorHelper.getMessage(error, c));
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT_DURATION, 1, 1.0f));//设置超时
        YoApp.getInstance().addToRequestQueue(request);
    }

    /**
     * 带标记位的get请求
     *
     * @param params   请求参数
     * @param url      请求地址
     * @param listener 回调接口
     * @param tag      添加标记
     */
    public void doGetRequestWithTag(HashMap<String, String> params, String url, final RequestListener listener, String tag) {
        url = HttpUtil.makeGetUrl(url, params);
        if (!NetUtils.isConnected(c)) {
            listener.onNetError();
        }
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(VolleyErrorHelper.getMessage(error, c));
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT_DURATION, 1, 1.0f));//设置超时
        YoApp.getInstance().addToRequestQueue(request, tag);
    }

    public void doPostRequest() {

    }

}
