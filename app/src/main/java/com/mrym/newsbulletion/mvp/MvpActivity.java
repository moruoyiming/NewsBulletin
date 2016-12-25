package com.mrym.newsbulletion.mvp;

import android.os.Bundle;
import android.util.Log;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.ui.BaseActivity;


public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();
    /**
     * 给我类名字符串
     *
     * @return 类名字符串
     */
    protected abstract String getTag();



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        Log.d(getTag(), "onDestroy()");
    }
}
