package com.sovnem.yoyoweibo.mvp.model;

import com.gamedirty.bean.Status;

import java.util.Collection;

/**
 * Created by sovnem on 16/8/29,23:14.
 */
public interface ITimelineModel {
    Collection<Status> getStatus();
}
