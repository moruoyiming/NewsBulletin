package com.mrym.newsbulletion.mvp.activity.skin;

/**
 * Created by Jian on 2016/10/10.
 */

public interface SkinView {

    void showLoading(String message);

    void showMessage(String message);

    void hideLoading();

    void showProgress(int progress);
}
