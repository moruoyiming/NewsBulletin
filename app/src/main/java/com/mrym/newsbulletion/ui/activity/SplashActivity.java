package com.mrym.newsbulletion.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.splash.SplashPresenter;
import com.mrym.newsbulletion.mvp.activity.splash.SplashView;
import com.mrym.newsbulletion.utils.PicassoUtils;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jian on 2016/8/26.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SplashActivity extends MvpActivity<SplashPresenter> implements SplashView {
    public String TAG = SplashActivity.class.getCanonicalName();
    private ImageView loginSplash;
    private Handler handler;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        StatusBarCompat.translucentStatusBar(SplashActivity.this,true);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        loginSplash = (ImageView) findViewById(R.id.login_splash);
        mvpPresenter.showAdvertisement();
        mvpPresenter.gotoNext();
    }

    @Override
    public void startMain() {

        if (handler != null) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, 2000);
        }
    }

    @Override
    public void startLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void shutDown() {
        finish();
    }

    @Override
    public void showAdvertisement(String url) {
        Picasso.with(SplashActivity.this)
                .load(R.mipmap.background_splash)
                .placeholder(R.mipmap.shouyetu)
                .error(R.mipmap.shouyetu)
                .config(Bitmap.Config.RGB_565)
                .into(loginSplash);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 避免Handler导致内存泄漏
        handler.removeCallbacksAndMessages(null);
        handler = null;
        NewsApplication.removeActivity(TAG);
    }
}
