package com.mrym.newsbulletion.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jaeger.library.StatusBarUtil;
import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.main.MainPresenter;
import com.mrym.newsbulletion.mvp.activity.main.MainView;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;
import com.squareup.haha.perflib.Main;

import butterknife.Bind;
import butterknife.ButterKnife;
import solid.ren.skinlibrary.loader.SkinManager;

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
    private long exitTime = 0;
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(MainActivity.this,false);
        StatusBarUtil.setColor(MainActivity.this, SkinManager.getInstance().getColor(R.color.primary_dark), 100);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mvpPresenter.createFragments(getSupportFragmentManager());
        mvpPresenter.setActivity(MainActivity.this);
        rgMain.setOnCheckedChangeListener(mvpPresenter.new RadioChangedListener());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 5:
                Intent intent=new Intent();
                intent.setAction(GlobalVariable.CHANELCHANGERECEIVER);
                sendBroadcast(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        // 保存最后登录时间  --  7天未登录自动退出登录
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//        sp.edit().putLong(GlobalVariable.LAST_LOGIN_TIME, SystemClock.currentThreadTimeMillis()).apply();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > GlobalVariable.KEY_DOWN_TIME) {
                ToastUtils.show(getString(R.string.twopress_finish));
                exitTime = System.currentTimeMillis();
            } else {
                NewsApplication.removeAllActivity();
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
