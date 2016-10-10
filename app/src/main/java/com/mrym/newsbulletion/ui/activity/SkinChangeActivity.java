package com.mrym.newsbulletion.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.skin.ChageSkinPresenter;
import com.mrym.newsbulletion.mvp.activity.skin.SkinView;
import com.mrym.newsbulletion.mvp.activity.splash.SplashPresenter;
import com.mrym.newsbulletion.mvp.activity.splash.SplashView;
import com.mrym.newsbulletion.ui.BaseActivity;

import butterknife.Bind;
import solid.ren.skinlibrary.listener.ILoaderListener;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * Created by Jian on 2016/10/10.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SkinChangeActivity extends MvpActivity<ChageSkinPresenter> implements SkinView {
    private MaterialDialog dialog;
    @Bind(R.id.btn_from_not)
    Button btnnot;
    @Bind(R.id.btn_from_net)
    Button skin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_changeskin);
        dialog = new MaterialDialog.Builder(SkinChangeActivity.this)
                .title("换肤中")
                .content("请耐心等待")
                .canceledOnTouchOutside(false)
                .progress(false, 100, true).build();
        btnnot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.restoreDefaultTheme();
            }
        });
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mvpPresenter.chageSkinFromLocal("theme.skin");
            }
        });
    }

    @Override
    protected ChageSkinPresenter createPresenter() {
        return new ChageSkinPresenter(this);
    }

    @Override
    public void showLoading(String message) {
        dialog.setContent(message);
        dialog.show();
    }

    @Override
    public void showMessage(String message) {
        dialog.setContent(message);
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showProgress(int progress) {
        dialog.setProgress(progress);
    }
}