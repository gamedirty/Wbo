package gamedirty.com.lib_widgets.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import gamedirty.com.lib_widgets.R;


/**
 * 加载更多的状态listview
 * 加载中 加载失败 没有更多数据 三种状态
 * Created by 赵军辉 on 2016/2/22.
 */
public class LoadMoreListview extends ListView {
    private TextView tv;
    private ProgressBar pb;

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
        View more = View.inflate(getContext(), R.layout.layout_loadmore_view, null);
        tv = (TextView) more.findViewById(R.id.textView_loadmore_text);
        pb = (ProgressBar) more.findViewById(R.id.progressBar_loadmore_pb);
        more.setClickable(true);
        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadMoreWork != null) {
                    loadMoreWork.work();
                }
            }
        });
        addFooterView(more);
    }

    public void setStatusLoadMoreError() {
        tv.setText("加载失败，点击重试");
        pb.setVisibility(View.INVISIBLE);
    }

    public void setStatusLoading() {
        pb.setVisibility(View.VISIBLE);
        tv.setText("加载中，请稍候");
    }

    public void setStatusNomore() {
        pb.setVisibility(View.INVISIBLE);
        tv.setText("没有更多的微博");
    }

    private LoadMoreWork loadMoreWork;

    public void setLoadMoreWork(LoadMoreWork loadMoreWork) {
        this.loadMoreWork = loadMoreWork;
    }

    public interface LoadMoreWork {
        public void work();
    }
}
