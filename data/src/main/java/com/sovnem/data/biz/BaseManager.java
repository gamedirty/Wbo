package com.sovnem.data.biz;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sovnem.data.DataConstants;
import com.sovnem.data.net.RequestListener;
import com.sovnem.data.utils.L;
import com.sovnem.data.utils.NetUtils;

import java.util.HashMap;
import java.util.Set;

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

    /**
     * 设置基础的配置参数
     *
     * @param params
     */
    protected void initBaseParams(HashMap params) {
        params.put("source", "" + DataConstants.APP_KEY);
        params.put("count", 20);
        params.put("access_token", "" + DataConstants.TOKEN);
        params.put("base_app", 0);
        params.put("feature", 0);//过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
        params.put("trim_user", NetUtils.isWifi(context) ? 0 : 1);//如果是WiFi返回user的完整字段，否则只返回userid
        L.i("token:" + DataConstants.TOKEN);
    }

    /**
     * 将参数组装成请求连接
     *
     * @param params
     * @param requestUrl
     * @return
     */
    public String makeGetUrl(HashMap params, String requestUrl) {
        initBaseParams(params);
        StringBuilder sb = new StringBuilder(requestUrl);
        Set<String> keys = params.keySet();
        sb.append("?");
        for (String key : keys) {
            sb.append(key + "").append("=").append("" + params.get(key)).append("&");
        }
        String result = sb.substring(0, sb.length() - 1);
        L.i("请求地址:" + result);
        return result;
    }

    /**
     * 通用get请求
     *
     * @param params
     * @param baseUrl
     * @param listener
     */
    public void doGetRequest(HashMap<String, String> params, String baseUrl, final RequestListener listener) {
        String url = makeGetUrl(params, baseUrl);
        if (!isNetEnable()) {
            listener.onNetError();
            return;
        }
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                L.i("请求结果:" + response);
//                try {
//                    JSONObject js = new JSONObject(response);
//                    String list = js.getString("statuses");
//                    Gson g = new Gson();
//                    StatusList l = g.fromJson(response,StatusList.class);
//
//                    L.i("返回微博的个数:" + l.statuses.size());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error.getMessage());
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
