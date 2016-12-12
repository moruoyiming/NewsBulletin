
package com.mrym.newsbulletion.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.newsbrowser.NewsbrowserPresenter;
import com.mrym.newsbulletion.mvp.activity.newsbrowser.NewsbrowserView;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;

import butterknife.Bind;
/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsBrowserActivity  extends MvpActivity<NewsbrowserPresenter> implements NewsbrowserView {
    public static final String TAG = NewsBrowserActivity.class.getCanonicalName();
    @Bind(R.id.leftback_toobar_l1)
    RelativeLayout back;
    @Bind(R.id.left_back_title)
    TextView mTitle;
    @Bind(R.id.header)
    LinearLayout header;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.web_view)
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_browser);
        StatusBarCompat.translucentStatusBar(NewsBrowserActivity.this,true);
        dynamicAddView(header, "background", R.color.primary_dark);
        mTitle.setText("新闻");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initWebView();
    }
    public static void startAction(Context context ,String link,String title){
        Intent intent = new Intent(context, NewsBrowserActivity.class);
        intent.putExtra(GlobalVariable.NEWS_LINK,link);
        intent.putExtra(GlobalVariable.NEWS_TITLE,title);
        context.startActivity(intent);
    }
    private void initWebView() {
        setWebViewSettings();
        setWebView();
    }

    private void setWebViewSettings() {
        WebSettings webSettings = webView.getSettings();
        // 打开页面时， 自适应屏幕
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        // 便页面支持缩放
        webSettings.setJavaScriptEnabled(true); //支持js
        webSettings.setSupportZoom(true); //支持缩放
//        webSettings.setBuiltInZoomControls(true); // 放大和缩小的按钮，容易引发异常 http://blog.csdn.net/dreamer0924/article/details/34082687

        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    private void setWebView() {
        webView.loadUrl(getIntent().getStringExtra(GlobalVariable.NEWS_LINK));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });
    }

    @Override
    protected NewsbrowserPresenter createPresenter() {
        return new NewsbrowserPresenter(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showProgress(int progress) {

    }

    @Override
    public void hideProgress() {

    }
}
