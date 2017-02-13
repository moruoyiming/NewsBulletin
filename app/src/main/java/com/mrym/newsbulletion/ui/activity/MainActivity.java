package com.mrym.newsbulletion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.ui.AppManager;
import com.mrym.newsbulletion.ui.BaseActivity;
import com.mrym.newsbulletion.ui.fragment.FollowFragment;
import com.mrym.newsbulletion.ui.fragment.HomeFragment;
import com.mrym.newsbulletion.ui.fragment.MineFragment;
import com.mrym.newsbulletion.ui.fragment.VideoFragment;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;
import com.mrym.newsbulletion.utils.statusbar.StatusBarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class MainActivity extends BaseActivity {
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
    public static final String FRAGMENT_TAG_HOME = "FRAGMENT_TAG_HOME";
    public static final String FRAGMENT_TAG_VIDEO = "FRAGMENT_TAG_VIDEO";
    public static final String FRAGMENT_TAG_FOLLOW = "FRAGMENT_TAG_FOLLOW";
    public static final String FRAGMENT_TAG_MINE = "FRAGMENT_TAG_MINE";
    private Fragment preFragment;
    private FragmentManager fm;
    private boolean isFullScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
        StatusBarCompat.translucentStatusBar(MainActivity.this, false);
        StatusBarUtil.setColor(MainActivity.this, SkinManager.getInstance().getColor(R.color.primary_dark), 100);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        createFragments(getSupportFragmentManager());
        rgMain.setOnCheckedChangeListener(new RadioChangedListener());

    }

    public void createFragments(FragmentManager fragmentManager) {
        this.fm = fragmentManager;
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        preFragment = new HomeFragment();
        Fragment video = new VideoFragment();
        Fragment follow = new FollowFragment();
        Fragment mine = new MineFragment();
        fragmentTransaction.add(R.id.fl_content, preFragment, FRAGMENT_TAG_HOME)
                .add(R.id.fl_content, video, FRAGMENT_TAG_VIDEO)
                .add(R.id.fl_content, follow, FRAGMENT_TAG_FOLLOW)
                .add(R.id.fl_content, mine, FRAGMENT_TAG_MINE)
                .hide(video)
                .hide(follow)
                .hide(mine).show(preFragment);
        fragmentTransaction.commit();
    }

    public void setCurrentItem(String tag, boolean needreset) {
        Fragment currentFragment = fm.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.hide(preFragment);
        fragmentTransaction.show(currentFragment);
        fragmentTransaction.commitAllowingStateLoss();
        if (needreset) {
            if (isFullScreen) {
                resetFragmentView(currentFragment);
            }
        }
        preFragment = currentFragment;

    }

    public void resetFragmentView(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                ViewGroup rootView;
                rootView = (ViewGroup) ((ViewGroup) contentView).getChildAt(0);
                if (rootView.getPaddingTop() != 0) {
                    rootView.setPadding(0, 0, 0, 0);
                }
            }
            if (fragment.getView() != null)
                fragment.getView().setPadding(0, getStatusBarHeight(this), 0, 0);
        }
    }

    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public class RadioChangedListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    isFullScreen = false;
                    StatusBarUtil.setColor(MainActivity.this, SkinManager.getInstance().getColor(R.color.primary_dark), 100);
                    setCurrentItem(FRAGMENT_TAG_HOME, true);
                    break;
                case R.id.rb_video:
                    isFullScreen = false;
                    StatusBarUtil.setColor(MainActivity.this, SkinManager.getInstance().getColor(R.color.primary_dark), 100);
                    setCurrentItem(FRAGMENT_TAG_VIDEO, true);
                    break;
                case R.id.rb_follow:
                    isFullScreen = false;
                    StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.black), 100);
                    setCurrentItem(FRAGMENT_TAG_FOLLOW, true);
                    break;
                case R.id.rb_mine:
                    isFullScreen = true;
//                    StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
                    StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.black), 100);
                    setCurrentItem(FRAGMENT_TAG_MINE, false);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 5:
                Intent intent = new Intent();
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
                AppManager.getAppManager().AppExit(MainActivity.this,false);
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
