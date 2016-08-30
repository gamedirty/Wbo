package com.sovnem.yoyoweibo.presener;

import com.sovnem.yoyoweibo.imvp.presener.ITimelinePresener;
import com.sovnem.yoyoweibo.imvp.view.ITimelineView;

/**
 * @author zjh
 * @description
 * @date 16/8/30.
 */
public class TimelinePresener implements ITimelinePresener {
    private ITimelineView iTimelineView;

    public TimelinePresener(ITimelineView iTimelineView) {
        this.iTimelineView = iTimelineView;
    }


    @Override
    public void initLoad() {

    }

    @Override
    public void loadMore() {

    }
}
