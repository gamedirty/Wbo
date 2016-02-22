package com.sovnem.yoyoweibo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sovnem.yoyoweibo.R;

/**
 * Created by 赵军辉 on 2016/2/22.
 */
public class LoadMoreListview extends ListView {
    private View more;
    private TextView tv;

    public LoadMoreListview(Context context) {
        super(context);
        init();
    }

    public LoadMoreListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        more = View.inflate(getContext(), R.layout.layout_loadmore_view, null);
        tv = (TextView) more.findViewById(R.id.textView_loadmore_text);
    }

    public void setStatusLoading(boolean isLoading) {
        if (isLoading) {
            addFooterView(more);
        } else {
            removeFooterView(more);
        }
    }

    public void setStatusNomore() {
    }
}
