package com.mrym.newsbulletion.mvp.activity.main;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import com.mrym.newsbulletion.R;
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
    public static final String FRAGMENT_TAG_HOME = "FRAGMENT_TAG_HOME";
    public static final String FRAGMENT_TAG_VIDEO = "FRAGMENT_TAG_VIDEO";
    public static final String FRAGMENT_TAG_FOLLOW = "FRAGMENT_TAG_FOLLOW";
    public static final String FRAGMENT_TAG_MINE = "FRAGMENT_TAG_MINE";
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
        fragmentTransaction.add(R.id.fl_content, preFragment, FRAGMENT_TAG_HOME)
                .add(R.id.fl_content, video, FRAGMENT_TAG_VIDEO)
                .add(R.id.fl_content, follow, FRAGMENT_TAG_FOLLOW)
                .add(R.id.fl_content, mine, FRAGMENT_TAG_MINE)
                .hide(video)
                .hide(follow)
                .hide(mine).show(preFragment);
        fragmentTransaction.commit();
    }

    public void setCurrentItem(String tag) {
        Fragment currentFragment = fm.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.hide(preFragment);
        fragmentTransaction.show(currentFragment);
        fragmentTransaction.commitAllowingStateLoss();
        preFragment = currentFragment;
    }

    public class RadioChangedListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    setCurrentItem(FRAGMENT_TAG_HOME);
                    break;
                case R.id.rb_video:
                    setCurrentItem(FRAGMENT_TAG_VIDEO);
                    break;
                case R.id.rb_follow:
                    setCurrentItem(FRAGMENT_TAG_FOLLOW);
                    break;
                case R.id.rb_mine:
                    setCurrentItem(FRAGMENT_TAG_MINE);
                    break;
            }
        }
    }

}
