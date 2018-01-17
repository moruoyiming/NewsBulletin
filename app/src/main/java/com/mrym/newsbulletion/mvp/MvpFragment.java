package com.mrym.newsbulletion.mvp;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import solid.ren.skinlibrary.base.SkinBaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MvpFragment<P extends BasePresenter> extends SkinBaseFragment {
    protected P mvpPresenter;
    public Activity mActivity;
    private Unbinder mUnBinder;
    protected View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mUnBinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mvpPresenter = createPresenter();
        mActivity = getActivity();
        initView();
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mvpPresenter = null;
        }
        mUnBinder.unbind();
    }
}
