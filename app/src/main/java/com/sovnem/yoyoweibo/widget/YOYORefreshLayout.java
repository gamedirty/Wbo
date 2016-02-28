package com.sovnem.yoyoweibo.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.sovnem.yoyoweibo.R;

/**
 * 统一swiperefreshlayout的风格
 * Created by sovnem on 16/2/28.
 */
public class YOYORefreshLayout extends SwipeRefreshLayout {
    public YOYORefreshLayout(Context context) {
        this(context, null);
    }

    public YOYORefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeColors(getResources().getColor(R.color.globalcolornormal), getResources().getColor(R.color.globalcolorpress));
        setProgressViewEndTarget(true, getProgressCircleDiameter());
    }

}
