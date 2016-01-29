package com.sovnem.data.biz;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sovnem.data.DataConstants;
import com.sovnem.data.standard.RequestListener;
import com.sovnem.data.utils.HttpUtil;
import com.sovnem.data.utils.L;
import com.sovnem.data.utils.NetUtils;

import java.util.HashMap;

/**
 * Created by sovnem on 16/1/3.
 */
public class BaseManager {
    private Context context;
    private RequestQueue queue;

    public BaseManager(Context context) {
        this.context = context;
        if (null == queue) queue = Volley.newRequestQueue(context);
    }

    protected void initBaseParams(HashMap params) {
        params.put("source", "" + DataConstants.APP_KEY);
        params.put("access_token", "" + DataConstants.TOKEN);
        L.i("token:" + DataConstants.TOKEN);
    }

    public void doGetRequest(HashMap params, String url, final RequestListener requestListener) {
        if (!isNetEnable()) {
            requestListener.onNetError();
            return;
        }
        initBaseParams(params);
        url = HttpUtil.makeGetUrl(url, params);
        L.i("get请求：" + url);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                L.i("响应值:" + response);
                requestListener.onRequestSuccess(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                L.i("请求错误");
                requestListener.onRequestFail(error.getMessage());
            }
        });

        queue.add(request);
    }

    public void doPostRequest() {

    }

    public boolean isNetEnable() {
        return NetUtils.isConnected(context);
    }
}
