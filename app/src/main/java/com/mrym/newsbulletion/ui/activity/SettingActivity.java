package com.mrym.newsbulletion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.setting.SettingPresenter;
import com.mrym.newsbulletion.mvp.activity.setting.SettingView;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SettingActivity extends MvpActivity<SettingPresenter> implements SettingView {
    public static final String TAG = SettingActivity.class.getCanonicalName();
    @BindView(R.id.leftback_toobar_l1)
    RelativeLayout back;
    @BindView(R.id.left_back_title)
    TextView mTitle;
    @BindView(R.id.header)
    LinearLayout header;
    @BindView(R.id.activity_setting_feedback)
    SuperTextView feddback;

    @OnClick({R.id.activity_setting_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_setting_feedback:
                Intent feedback = new Intent(this, FeedBackActivity.class);
                startActivity(feedback);
                break;

        }
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        StatusBarCompat.translucentStatusBar(SettingActivity.this, true);
        dynamicAddView(header, "background", R.color.primary_dark);
        mTitle.setText("设置");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 入口
     *
     * @param mContext
     */
    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, SettingActivity.class);
        mContext.startActivity(intent);
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
