package com.mrym.newsbulletion.mvp.activity.newsbrowser;

/**
 * Created by Jian on 2016/11/22.
 */

public interface NewsbrowserView {

    void showLoading(String message);

    void showMessage(String message);

    void hideLoading();

    void showProgress(int progress);

    void hideProgress();
}
