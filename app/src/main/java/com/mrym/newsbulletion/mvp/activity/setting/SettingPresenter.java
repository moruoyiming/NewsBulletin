package com.mrym.newsbulletion.mvp.activity.setting;

import android.util.Log;

import com.mrym.newsbulletion.mvp.BasePresenter;

/**
 * Created by Jian on 2016/10/10.
 */

public class SettingPresenter extends BasePresenter<SettingView> {

    public SettingPresenter(SettingView settingView) {
        attachView(settingView);
    }

}
