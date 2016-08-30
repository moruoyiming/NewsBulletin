package com.mrym.newsbulletion.mvp.activity.login;

import com.mrym.newsbulletion.domain.modle.UserBean;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface LoginView {

    void showLoading();

    void hideLoading();

    void loginFailure(int code, String msg);

    void loginSuccess(UserBean userBean);

    void startMain();

    void showToast(String msg);

    void onTick(long millisUntilFinished);

    void onFinish();
}
