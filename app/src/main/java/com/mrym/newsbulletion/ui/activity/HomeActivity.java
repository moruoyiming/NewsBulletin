package com.mrym.newsbulletion.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.commander.newsonline.R;
import com.commander.newsonline.mvp.presenter.HomePresenterImpl;
import com.commander.newsonline.mvp.view.HomeView;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;

/**
 * author : nan
 * time : 2016/8/9.
 */
public class HomeActivity extends MvpActivity<HomePresenterImpl> implements HomeView {
    @BindViews({R.id.tab_home, R.id.tab_video, R.id.tab_attention, R.id.tab_mine})
    List<RadioButton> mTabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mvpPresenter.prepareData();
        mvpPresenter.setSelect(0);
    }

    @Override
    protected HomePresenterImpl createPresenter() {
        return new HomePresenterImpl(this);
    }

    @OnClick({R.id.tab_home, R.id.tab_video, R.id.tab_attention, R.id.tab_mine})
    void bottomClick(View view) {
        mvpPresenter.resetImgs();
        switch (view.getId()) {
            case R.id.tab_home:
                mvpPresenter.setSelect(0);
                break;
            case R.id.tab_video:
                mvpPresenter.setSelect(1);
                break;
            case R.id.tab_attention:
                mvpPresenter.setSelect(2);
                break;
            case R.id.tab_mine:
                mvpPresenter.setSelect(3);
                break;
        }
    }


    @Override
    public HomeActivity getActivity() {
        return this;
    }

    public List<RadioButton> getTabs() {
        return mTabs;
    }

    public void setTabs(List<RadioButton> mTabs) {
        this.mTabs = mTabs;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mvpPresenter.onActivityResult(requestCode, resultCode, data);
    }
}
