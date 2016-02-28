package com.sovnem.yoyoweibo.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Gson 的封装类
 * Created by sovnem on 16/2/28.
 */
public class JsonParsor<T> {
    public JsonParsor() {
    }

    /**
     * json转Java实体
     *
     * @param jsonStr
     * @param tClass
     * @return
     */
    public T toBean(String jsonStr, Class<T> tClass) {
        return new Gson().fromJson(jsonStr, tClass);
    }

    /**
     * json转list集合
     *
     * @param jsonStr
     * @param tClass
     * @return
     */
    public List<T> toBeanList(String jsonStr, Class<T> tClass) {
        return new Gson().fromJson(jsonStr, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 转为json字符串
     *
     * @param t
     * @return
     */
    public String toJsonString(T t) {
        return new Gson().toJson(t);
    }
}
