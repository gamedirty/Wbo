package com.sovnem.yoyoweibo.db;

/**
 * Created by sovnem on 16/3/7.
 */
public interface BaseDB<T> {
    public String getDBName();

    public void insert(T t);

    public T getBy(String by);

}
