package com.sovnem.yoyoweibo.bean;

/**
 * 返回的错误实体
 * Created by sovnem on 16/2/27.
 */
public class ErrorBean {

    /**
     * request : /statuses/home_timeline.json
     * error_code : 20502
     * error : Need you follow uid.
     */

    public String request;
    public String error_code;
    public String error;


    @Override
    public String toString() {
        return "ErrorBean{" +
                "request='" + request + '\'' +
                ", error_code='" + error_code + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
