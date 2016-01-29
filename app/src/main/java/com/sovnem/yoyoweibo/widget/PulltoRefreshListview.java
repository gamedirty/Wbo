package com.sovnem.yoyoweibo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * 可以下拉的listview
 * Created by sovnem on 16/1/9.
 */
public class PulltoRefreshListview extends ListView {

    public PulltoRefreshListview(Context context) {
        super(context);
    }

    public PulltoRefreshListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PulltoRefreshListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childH = child.getHeight();
        }

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
