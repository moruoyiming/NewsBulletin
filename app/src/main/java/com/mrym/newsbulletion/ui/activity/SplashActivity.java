package com.mrym.newsbulletion.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.authenticator.account.AccountTool;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.splash.SplashPresenter;
import com.mrym.newsbulletion.mvp.activity.splash.SplashView;


/**
 * Created by Jian on 2016/8/26.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SplashActivity extends MvpActivity<SplashPresenter> implements SplashView {
    public String TAG = SplashActivity.class.getCanonicalName();
    //    private ImageView loginSplash;
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
    protected int getLayoutId() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        handler = new Handler();
//        loginSplash = (ImageView) findViewById(R.id.login_splash);
        mvpPresenter.showAdvertisement();
        mvpPresenter.gotoNext();
        Log.i("caonima", "caonima ");
    }


    @Override
    public void startMain() {
        if (handler != null) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Log.i("caonima", "caonima ");
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, 3000);
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
//        Picasso.with(SplashActivity.this)
//                .load(R.mipmap.background_splash)
//                .placeholder(R.mipmap.shouyetu)
//                .error(R.mipmap.shouyetu)
//                .config(Bitmap.Config.RGB_565)
//                .into(loginSplash);
//        Glide.with(this).load(R.mipmap.google).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new GlideDrawableImageViewTarget(loginSplash, 1));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 避免Handler导致内存泄漏
        handler.removeCallbacksAndMessages(null);
        handler = null;
        mvpPresenter = null;
        mActivity = null;
    }
}
