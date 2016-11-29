package com.mrym.newsbulletion.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.setting.SettingPresenter;
import com.mrym.newsbulletion.mvp.activity.setting.SettingView;

public class MainSettingActivity  extends MvpActivity<SettingPresenter> implements SettingView {
    private ImageButton backToMainButton = null;
	
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showProgress(int progress) {

    }
}
