package com.mrym.newsbulletion.mvp.activity.setting;

import android.util.Log;

import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.activity.skin.SkinView;

import solid.ren.skinlibrary.listener.ILoaderListener;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * Created by Jian on 2016/10/10.
 */

public class SettingPresenter extends BasePresenter<SettingView> {

    public SettingPresenter(SettingView settingView) {
        attachView(settingView);
    }

}
