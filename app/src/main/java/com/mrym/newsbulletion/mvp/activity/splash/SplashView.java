package com.mrym.newsbulletion.mvp.activity.splash;

import android.graphics.Bitmap;
import android.net.Uri;

import com.mrym.newsbulletion.authenticator.account.AccountTool;

/**
 * Created by Jian on 2016/8/26.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface SplashView {
    AccountTool getAccountTool();

    void startMain();

    void startLogin();

    void shutDown();
}
