package com.mrym.newsbulletion.ui.fragment;

import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.follow.FollowPresenter;
import com.mrym.newsbulletion.mvp.fragment.follow.FollowView;

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
}
