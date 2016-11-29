package com.mrym.newsbulletion.mvp.activity.setting;

/**
 * Created by Jian on 2016/10/10.
 */

public interface SettingView {

    void showLoading(String message);

    void showMessage(String message);

    void hideLoading();

    void showProgress(int progress);
}
