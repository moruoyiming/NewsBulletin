package com.mrym.newsbulletion.mvp.activity.main;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.activity.login.LoginView;
import com.mrym.newsbulletion.ui.fragment.FollowFragment;
import com.mrym.newsbulletion.ui.fragment.HomeFragment;
import com.mrym.newsbulletion.ui.fragment.MineFragment;
import com.mrym.newsbulletion.ui.fragment.VideoFragment;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class MainPresenter extends BasePresenter<MainView> {

    private Fragment preFragment;
    private FragmentManager fm;

    public MainPresenter(MainView mainView) {
        attachView(mainView);
    }

    public void createFragments(FragmentManager fragmentManager) {
        this.fm = fragmentManager;
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        preFragment = new HomeFragment();
        Fragment video = new VideoFragment();
        Fragment follow = new FollowFragment();
        Fragment mine = new MineFragment();
    }
}
