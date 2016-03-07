package com.sovnem.yoyoweibo.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sovnem.yoyoweibo.utils.L;
import com.sovnem.yoyoweibo.utils.NetUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 封装的volley请求工具类
 * Created by sovnem on 16/3/6.
 */
public class HttpManager {
    private static final int TIMEOUT_LIMIT_MILLIS = 10 * 1000;

    private HttpManager() {

    }

    /**
     * 做get请求
     *
     * @param context  上下文实例
     * @param params   参数
     * @param url      请求地址
     * @param listener 监听器
     * @param limit    是不是设置超时
     */
    public static void doGetRequest(Context context, HashMap<String, String> params, String url, final RequestListener listener, boolean limit) {
        if (!NetUtils.isConnected(context)) {
            listener.onNetError();
            return;
        }
        url = makeGetUrlWithParams(url, params);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onRequestSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestError(error.getMessage());
            }
        });
        if (limit) {
            request.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_LIMIT_MILLIS, 1, 1.0f));
        }
        SingletonQueue.getInstance(context).addToQueue(request);
    }

    /**
     * 做get请求
     *
     * @param context  上下文实例
     * @param params   参数
     * @param url      请求地址
     * @param listener 监听器
     */
    public static void doGetRequest(Context context, HashMap<String, String> params, String url, final RequestListener listener) {
        doGetRequest(context, params, url, listener, true);
    }

    /**
     * 生成带参数的get请求URL
     *
     * @param url
     * @param params
     * @return
     */
    public static String makeGetUrlWithParams(String url, HashMap<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");
        Set<String> keys = params.keySet();
        for (String key : keys) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        url = sb.toString();
        L.i("get请求地址:" + url);
        return url.substring(0, url.length() - 1);
    }

    /**
     * 进行post请求
     *
     * @param context  上下文实例
     * @param params   参数
     * @param url      请求地址
     * @param listener 监听器
     * @param limit    是不是设置超时
     */
    public static void doPostRequest(Context context, final HashMap<String, String> params, String url, final RequestListener listener, boolean limit) {
        if (!NetUtils.isConnected(context)) {
            listener.onNetError();
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onRequestSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };


        if (limit) {
            request.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_LIMIT_MILLIS, 1, 1.0f));
        }
        SingletonQueue.getInstance(context).addToQueue(request);
    }
}
