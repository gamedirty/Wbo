package com.gamedirty.api;

import com.gamedirty.bean.StatusList;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by sovnem on 16/8/29,22:23.
 */
public interface TimelineApi {

    @POST
    Observable<StatusList> getStatus();

}
