package com.mrym.newsbulletion.ui.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.main.MainPresenter;
import com.mrym.newsbulletion.mvp.activity.main.MainView;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {
    public static String TAG = MainActivity.class.getCanonicalName();
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_video)
    RadioButton tabVideo;
    @Bind(R.id.rb_follow)
    RadioButton rbFollow;
    @Bind(R.id.rb_mine)
    RadioButton rbMine;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mvpPresenter.createFragments(getSupportFragmentManager());
        rgMain.setOnCheckedChangeListener(mvpPresenter.new RadioChangedListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 保存最后登录时间  --  7天未登录自动退出登录
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putLong(GlobalVariable.LAST_LOGIN_TIME, SystemClock.currentThreadTimeMillis()).apply();
    }


}
