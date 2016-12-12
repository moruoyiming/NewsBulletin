package com.mrym.newsbulletion.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
    @Bind(R.id.login_skip)
    Button loginSkip;
    @Bind(R.id.login_splash)
    ImageView loginSplash;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        StatusBarCompat.translucentStatusBar(SplashActivity.this,true);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        NewsApplication.addActivity(this, TAG);
        mvpPresenter.showAdvertisement();
        mvpPresenter.gotoNext();
    }

    @Override
    public void startMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
//        Glide.with(this).load(url).crossFade().into(loginSplash);
        PicassoUtils.display(this,loginSplash, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NewsApplication.removeActivity(TAG);
    }

    @OnClick(R.id.login_skip)
    public void onClick() {
        mvpPresenter.close();
        startMain();
    }
}
