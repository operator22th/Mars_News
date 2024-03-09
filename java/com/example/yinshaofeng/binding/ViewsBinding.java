package com.example.yinshaofeng.binding;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.RequestManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewsBinding {

    private static final String TAG = "ViewsBinding";


    @BindingAdapter({"loadImage", "reqManager"})
    public static void changeImage(ImageView imageView , String imageUrl,RequestManager requestManager){
        requestManager.load(imageUrl).into(imageView);
    }


    @BindingAdapter("openWebView")
    public static void openArticle(WebView webView, String link){
        webView.loadUrl(link);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public Bitmap getDefaultVideoPoster() {
                return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            }
        });
    }

    @BindingAdapter("authorName")
    public static void setName(TextView textView, String authorName){
        if(authorName != null) {
            textView.setText(authorName);
        }
    }

    @BindingAdapter({"categoryImage"})
    public static void setCategory(ImageView imageView, int drawable){
        imageView.setImageResource(drawable);
    }
    @BindingAdapter("dateFormat")
    public static void getTodayDetailsDate(TextView textView, String date) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat output = new SimpleDateFormat("dd MMMM, hh:mm a", Locale.getDefault());
        Date d = null;
        try {
            d = input.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(d != null)
            textView.setText(output.format(d));
        else
            textView.setText(date);
    }
    @BindingAdapter("onSearchAction")
    public static void setOnSearchAction(EditText editText, final Runnable onSearchAction) {
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 用户按下了返回键，执行搜索操作
                    onSearchAction.run();
                    return true; // 消费事件，防止继续传递
                }
                return false; // 允许继续传递事件
            }
        });
    }
}
