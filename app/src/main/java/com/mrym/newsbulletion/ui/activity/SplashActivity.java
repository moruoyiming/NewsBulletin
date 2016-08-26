package com.mrym.newsbulletion.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.authenticator.account.AccountTool;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.splash.SplashPresenter;
import com.mrym.newsbulletion.mvp.activity.splash.SplashView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jian on 2016/8/26.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SplashActivity extends MvpActivity<SplashPresenter> implements SplashView {
    @Bind(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mvpPresenter.requestPermission();
        } else {
            mvpPresenter.gotoNext();
        }
        mvpPresenter.initSplash(getWindowManager());
    }
    @Override
    public void showSplash(Bitmap splash) {
        ivSplash.setImageBitmap(splash);
    }

    @Override
    public void showSplash(int splash) {
        ivSplash.setImageResource(splash);
    }

    @Override
    public void gotoFirst() {

    }

    @Override
    public void startMain() {
//        startActivity(new Intent(this, MainActivity.class));
//        finish();
    }

    @Override
    public void startLogin() {
//        startActivity(new Intent(this, MainActivity.class));
//        finish();
    }

    @Override
    public void shutDown() {
        finish();

    }

}
