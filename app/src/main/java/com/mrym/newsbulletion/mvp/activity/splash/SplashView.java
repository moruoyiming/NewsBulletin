package com.mrym.newsbulletion.mvp.activity.splash;

import android.graphics.Bitmap;

import com.mrym.newsbulletion.authenticator.account.AccountTool;

/**
 * Created by Jian on 2016/8/26.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface SplashView {
    AccountTool getAccountTool();

    void showSplash(Bitmap splash);

    void showSplash(int splash);

    void gotoFirst();

    void startMain();

    void startLogin();

    void shutDown();
}
