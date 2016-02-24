package com.sovnem.data.biz;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sovnem.data.DataConstants;
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


    public void doPostRequest() {

    }

    public boolean isNetEnable() {
        return NetUtils.isConnected(context);
    }
}
