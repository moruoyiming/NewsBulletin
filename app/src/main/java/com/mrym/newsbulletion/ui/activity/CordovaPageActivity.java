package com.mrym.newsbulletion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;

import org.apache.cordova.CordovaActivity;

/**
 * Created by Shawn on 2016/3/15.
 */
public class CordovaPageActivity extends CordovaActivity {

    private static final String TAG = CordovaPageActivity.class.getCanonicalName();
    public final static String INTENT_URL_KEY = "url_key";
    private LinearLayout linearLayout;

    public static void startAction(Context context , String link, String title){
        Intent intent = new Intent(context, NewsBrowserActivity.class);
        intent.putExtra(INTENT_URL_KEY,link);
        intent.putExtra(GlobalVariable.NEWS_TITLE,title);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra(INTENT_URL_KEY);
        loadUrl(url);

        // 如果加载外部网页，给页面添加Loading效果
        if (url.startsWith("http")){
            FrameLayout frameLayout = (FrameLayout) appView.getView().getParent();
            linearLayout = new LinearLayout(this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(linearLayout.getLayoutParams());
            imageView.setImageResource(R.mipmap.loading);
            // 加载动画
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.loading_animation);
            // 使用ImageView显示动画
            imageView.startAnimation(hyperspaceJumpAnimation);
            TextView textView = new TextView(this);
            textView.setLayoutParams(linearLayout.getLayoutParams());
            textView.setText("页面加载中，请稍候...");
            textView.setTextColor(Color.parseColor("#DDDDDD"));
            textView.setTextSize(14);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            frameLayout.addView(linearLayout);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        View webView = appView.getView();
        // 解除WebView与父控件的依附关系
        ((ViewGroup)webView.getParent()).removeView(webView);
    }

    @Override
    public void onPageFinished(){
        if (linearLayout!=null){
            linearLayout.setVisibility(View.GONE);
        }
    }

    protected void onPause() {
        super.onPause();
//        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
//        AVAnalytics.onResume(this);
    }
}
