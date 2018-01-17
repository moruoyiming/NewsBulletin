package com.mrym.newsbulletion.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.ui.BaseActivity;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import solid.ren.skinlibrary.base.SkinBaseActivity;


public abstract class MvpActivity<P extends BasePresenter> extends SkinBaseActivity {
    protected P mvpPresenter;
    private Unbinder mUnBinder;
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mvpPresenter = createPresenter();
        mUnBinder = ButterKnife.bind(this);
        mActivity = this;
        initView();
    }

    protected abstract P createPresenter();

    protected abstract String getTag();

    protected abstract int getLayoutId();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        Log.d(getTag(), "onDestroy()");
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }
}
