package com.sovnem.data.biz;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sovnem.data.DataConstants;
import com.sovnem.data.utils.L;
import com.sovnem.data.utils.NetUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * 主要的网络请求业务类 的基类
 * Created by sovnem on 16/1/3.
 */
class BaseManager {
    private Context context;
    private RequestQueue queue;

    BaseManager(Context context) {
        this.context = context;
        if (null == queue) queue = Volley.newRequestQueue(context);
    }

    void addTokenTo(HashMap params) {
        params.put("access_token", "" + DataConstants.TOKEN);
    }


    /**
     * 将参数和基础URL组装成请求连接
     *
     * @param params
     * @param requestUrl
     * @return
     */
    private String makeGetUrl(HashMap params, String requestUrl) {
        StringBuilder sb = new StringBuilder(requestUrl);
        Set<String> keys = params.keySet();
        sb.append("?");
        for (String key : keys) {
            sb.append(key + "").append("=").append("" + params.get(key)).append("&");
        }

        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 请求微博获取相关的请求
     *
     * @param params
     * @param baseUrl
     * @param listener
     */
    void doGetRequest(HashMap<String, String> params, String baseUrl, final RequestListener listener) {
        String url = makeGetUrl(params, baseUrl);
        L.i("请求地址:" + url);
        if (!isNetEnable()) {
            listener.onNetError();
            return;
        }
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
                L.i("请求成功:" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error.getMessage());
                L.e("请求失败:" + error.getMessage());
            }
        });
        queue.add(request);
    }

    private boolean isNetEnable() {
        return NetUtils.isConnected(context);
    }

    boolean isWifi() {
        return NetUtils.isWifi(context);
    }
}
