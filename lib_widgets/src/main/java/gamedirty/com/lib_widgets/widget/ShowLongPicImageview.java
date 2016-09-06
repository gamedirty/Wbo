package gamedirty.com.lib_widgets.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;

/**
 * 展示长图的view
 *
 * @author zjh
 * @description
 * @date 16/9/6.
 */
public class ShowLongPicImageview extends WebView {
    public ShowLongPicImageview(Context context) {
        super(context);
        init();
    }

    private void init() {
        WebSettings webSettings = this.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);//support zoom
        webSettings.setUseWideViewPort(true);// 这个很关键
        webSettings.setLoadWithOverviewMode(true);
    }

    public ShowLongPicImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShowLongPicImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void showLargeImage(String filePath) {
        showLargeImage(new File(filePath));
    }

    public void showLargeImage(File file) {
        if (file == null && !file.exists()) {
            throw new RuntimeException("文件不合法");
        }
        final String base = Uri.fromFile(file.getParentFile()).toString();
        final String name = file.getName();
        loadDataWithBaseURL(base + "/",
                "<html><body><img  src=\"" + name + "\"  width=\"100%\"/></body></html>", "text/html", "UTF-8", "");

    }
}
