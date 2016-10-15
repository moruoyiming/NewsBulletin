package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.follow.FollowPresenter;
import com.mrym.newsbulletion.mvp.fragment.follow.FollowView;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class FollowFragment extends MvpFragment<FollowPresenter> implements FollowView{

    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_follow, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarCompat.translucentStatusBar(getActivity(), false);
    }
}
