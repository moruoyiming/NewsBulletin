package com.mrym.newsbulletion.ui.activity;

import android.os.Bundle;

import com.commander.newsonline.mvp.presenter.BasePresenter;

/**
 * Created by Ryan on 2016/8/8.
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null){
            mvpPresenter.detachView();
        }
    }
}
