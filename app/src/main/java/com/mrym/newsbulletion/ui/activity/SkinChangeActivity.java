package com.mrym.newsbulletion.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
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
    @Bind(R.id.activity_changeskin_rc)
    XRecyclerView mSkinGrid;
    @Bind(R.id.header)
    LinearLayout header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_changeskin);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        dynamicAddView(header, "background", R.color.primary_dark);
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