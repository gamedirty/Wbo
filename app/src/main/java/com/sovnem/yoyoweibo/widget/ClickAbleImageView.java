package com.sovnem.yoyoweibo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.sovnem.yoyoweibo.R;

/**
 * 有点击效果的imageview
 * <p/>
 * 默認scaletype是CENTER_CROP
 */
public class ClickAbleImageView extends ImageView {
    public static final int TYPE_GIF = 0;
    public static final int TYPE_LONGIMAGE = 1;
    public static final int TYPE_DEFAULT = -1;
    private int type;//图片类型
    private Bitmap gif, cut;
    private Paint bmPaint;


    public ClickAbleImageView(Context context) {
        super(context);
        setScaleType(ScaleType.CENTER_CROP);
        setClickable(true);
        setBackgroundColor(Color.parseColor("#dfdfdf"));
        gif = BitmapFactory.decodeResource(getResources(), R.mipmap.timeline_image_gif);
        cut = BitmapFactory.decodeResource(getResources(), R.mipmap.timeline_image_longimage);
        bmPaint = new Paint();
        this.type = type;
    }


    public ClickAbleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.CENTER_CROP);
        setClickable(true);
    }

    public ClickAbleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.CENTER_CROP);
        setClickable(true);
    }

    public void setType(int type) {
        this.type = type;
        postInvalidate();
    }

    private boolean pressed;//是不是被点击状态

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pressed) {
            canvas.drawColor(Color.parseColor("#1f000000"));
        }
        switch (type) {
            case TYPE_GIF:
                canvas.drawBitmap(gif, (w - gif.getWidth()), h - gif.getHeight(), bmPaint);
                break;
            case TYPE_LONGIMAGE:
                canvas.drawBitmap(cut, (w - cut.getWidth()), h - cut.getHeight(), bmPaint);
                break;
        }
    }

    private int w, h;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                pressed = false;
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
