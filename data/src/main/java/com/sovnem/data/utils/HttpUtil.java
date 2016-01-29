package com.sovnem.data.utils;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by sovnem on 16/1/2.
 */
public class HttpUtil {
    /**
     * 生成带参数的get请求URL
     *
     * @param url
     * @param params
     * @return
     */
    public static String makeGetUrl(String url, HashMap<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");
        Set<String> keys = params.keySet();
        for (String key : keys) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        url = sb.toString();
        return url.substring(0, url.length() - 1);
    }
}
