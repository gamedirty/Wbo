package com.sovnem.yoyoweibo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 显示多个imageview 微博中用于展示每条微博的图片
 * 填充最多9张图片
 * <p/>
 * 除了只有一张图片的情况，其他时候图片都是正方形，且边长为屏幕宽度的三分之一
 * 只有一张图片，图片的宽度为边长的三分之二
 * <p/>
 * 分9中情況
 * <p/>
 * 1 1
 * 1 1
 * <p/>
 * 1 2
 * <p/>
 * 1 2 3
 * <p/>
 * 1 2
 * 3 4
 * <p/>
 * 1 2 3
 * 4 5
 * <p/>
 * 1 2 3
 * 4 5 6
 * <p/>
 * 1 2 3
 * 4 5 6
 * 7
 * <p/>
 * 1 2 3
 * 4 5 6
 * 7 8
 * <p/>
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * <p/>
 * <p/>
 * 子view数除了1和4都是按照
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 排列
 * <p/>
 * 观察可以发现只有子view个数为1的时候测量和布局都不一样
 * 子view个数为4的时候大小一样  布局不常规
 * <p/>
 * <p/>
 * <p/>
 * Created by 赵军辉 on 2016/1/4.
 */
public class MultiImageViewGroup extends
                                 ViewGroup {
    /**
     * 每个图片之间的缝隙宽度
     */
    private final int SPACE = 10;
    private int childW;//单个子控件的宽度

    public MultiImageViewGroup(Context context) {
        super(context);
        init(context);
    }


    public MultiImageViewGroup(Context context,
                               AttributeSet
                                       attrs) {
        super(context, attrs);
        init(context);
    }

    public MultiImageViewGroup(Context context,
                               AttributeSet
                                       attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
    }

    /**
     * 布局所有的子view
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed,
                            int l, int t, int
                                    r, int
                                    b) {
        int count = getChildCount();
        if (count == 0)
            return;
        if (count == 1) {
            getChildAt(0).layout(0, 0,
                    getMeasuredWidth(),
                    getMeasuredHeight());
        } else if (count == 4) {
            getChildAt(0).layout(0, 0, childW,
                    childW);
            getChildAt(1).layout((childW +
                    SPACE), 0, childW + (childW
                    + SPACE), childW);
            getChildAt(2).layout(0, (childW +
                    SPACE), childW, childW +
                    (childW + SPACE));
            getChildAt(3).layout((childW +
                            SPACE), (childW +
                            SPACE),
                    childW + (childW + SPACE),
                    childW + (childW + SPACE));
        } else {
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                int row = i / 3;//行数
                int num = i % 3;//列数
                child.layout((childW + SPACE) *
                        num, (childW + SPACE) *
                        row, (childW + SPACE) *
                        num + childW, (childW +
                        SPACE) * row + childW);
            }
        }


    }


    private int oW, oH;

    /**
     * 测量逻辑
     * 判断子view个数 如果只有一个那么限制其大小在屏幕宽的矩形内
     * <p/>
     * 如果不是固定宽高为屏幕的三分之一
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,
                heightMeasureSpec);
        if (oW == 0 || oH == 0) {
            oH = MeasureSpec.getSize
                    (heightMeasureSpec);
            oW = MeasureSpec.getSize
                    (widthMeasureSpec);
        }


        int count = getChildCount();
        if (count == 0)
            return;
        //声明最终控件的尺寸
        int finalSizew = 0;
        int finalSizeh = 0;
        int w = oW;//控件的建议尺寸
        childW = (w - SPACE * 2) / 3;//子控件尺寸


        if (count != 1) {//子view个数不是1的话宽度固定为控件宽度的三分之一
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                //让每个子view都占满宽度的三分之一
                measureChild(child, MeasureSpec
                        .makeMeasureSpec
                                (childW,
                                        MeasureSpec.AT_MOST), MeasureSpec
                        .makeMeasureSpec(childW, MeasureSpec.AT_MOST));
            }
        }
        if (count == 1) {//只有一个控件 测量其高度

            measureChild(getChildAt(0),
                    MeasureSpec.makeMeasureSpec
                            (childW * 2 +
                                            SPACE,
                                    MeasureSpec
                                            .EXACTLY), MeasureSpec
                            .makeMeasureSpec(childW * 2 + SPACE, MeasureSpec
                                    .EXACTLY));
            finalSizew = (childW * 2 +
                    SPACE);

            finalSizeh = (childW * 2 +
                    SPACE);
        } else if (count == 4) {
            finalSizew = finalSizeh = childW *
                    2 + SPACE;
        } else {
            finalSizew = count >= 3 ? (3 *
                    childW + 2 * SPACE) :
                    (childW * 2 + SPACE);
            finalSizeh = count > 6 ? (3 *
                    childW + 2 * SPACE) :
                    (count > 3 ? (childW * 2 +
                            SPACE) : childW);
        }
        setMeasuredDimension(finalSizew,
                finalSizeh);
    }


}
