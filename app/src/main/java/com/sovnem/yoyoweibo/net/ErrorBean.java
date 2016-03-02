package com.sovnem.yoyoweibo.net;

/**
 * 返回的错误实体
 * Created by sovnem on 16/2/27.
 */
public class ErrorBean {


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
