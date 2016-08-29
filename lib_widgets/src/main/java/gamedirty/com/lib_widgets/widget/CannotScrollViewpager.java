package gamedirty.com.lib_widgets.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不可滚动的viewpager
 * Created by sovnem on 16/1/1.
 */
public class CannotScrollViewpager extends ViewPager {
    public CannotScrollViewpager(Context context) {
        super(context);
    }

    public CannotScrollViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }

}
