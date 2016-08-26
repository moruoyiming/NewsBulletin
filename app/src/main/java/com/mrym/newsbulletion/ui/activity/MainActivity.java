//package com.mrym.newsbulletion.ui.activity;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.preference.PreferenceManager;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//
//
//import com.mrym.newsbulletion.R;
//import com.mrym.newsbulletion.domain.constans.GlobalVariable;
//
//import butterknife.Bind;
//
///**
// * Created by Shawn on 2016/8/18.
// */
//public class MainActivity extends MvpActivity<MainPresenter> implements MainView {
//    @Bind(R.id.rg_main)
//    RadioGroup rgMain;
//    @Bind(R.id.rb_order)
//    RadioButton rbOrder;
//
//    @Override
//    protected MainPresenter createPresenter() {
//        return new MainPresenter(this);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        // 腾讯信鸽
//        mvpPresenter.initXG();
//        mvpPresenter.createFragments(getSupportFragmentManager());
//        rgMain.setOnCheckedChangeListener(mvpPresenter.new RadioChangedListener());
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // 保存最后登录时间  --  7天未登录自动退出登录
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//        sp.edit().putLong(GlobalVariable.LAST_LOGIN_TIME, SystemClock.currentThreadTimeMillis()).apply();
//    }
//
//    public void goToOrder() {
//        rbOrder.setChecked(true);
//    }
//
//}
