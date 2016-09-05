package gamedirty.com.lib_widgets.widget;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 显示不同颜色字的textview
 * @author  zhjh
 * @description
 * @date 16/6/21.
 */
public class MultiColorTextView extends TextView {
    public MultiColorTextView(Context context) {
        super(context);
    }

    public MultiColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(String textA, String textB, int colorA,int colorB) {
        if (textA==null||textB==null)throw new RuntimeException("the two text should not be null");
        SpannableString ss = new SpannableString(textA + textB);
        ss.setSpan(new ForegroundColorSpan(colorA), 0, textA.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(colorB), textA.length(), textA.length()+textB.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(ss);
    }
}
