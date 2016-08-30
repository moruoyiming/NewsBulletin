package com.mrym.newsbulletion.utils;

import android.os.CountDownTimer;

import com.mrym.newsbulletion.mvp.activity.login.LoginView;


/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class TimeCount extends CountDownTimer {
    private LoginView loginview;
    private boolean isFinish;

    public TimeCount(LoginView loginView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.loginview = loginView;
    }

    @Override
    public void onFinish() {//计时完毕时触发
        isFinish = true;
        loginview.onFinish();
    }

    @Override
    public void onTick(long millisUntilFinished) {//计时过程显示
        loginview.onTick(millisUntilFinished);
    }

    public boolean isFinish() {
        return isFinish;
    }
}
