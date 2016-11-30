package com.mrym.newsbulletion.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.setting.SettingPresenter;
import com.mrym.newsbulletion.mvp.activity.setting.SettingView;

import butterknife.Bind;
/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SettingActivity extends MvpActivity<SettingPresenter> implements SettingView {
    @Bind(R.id.leftback_toobar_l1)
    RelativeLayout back;
    @Bind(R.id.left_back_title)
    TextView mTitle;
    @Bind(R.id.header)
    LinearLayout header;
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_setting);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        dynamicAddView(header, "background", R.color.primary_dark);
        mTitle.setText("设置");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
