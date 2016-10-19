package com.mrym.newsbulletion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.about.AboutPresenter;
import com.mrym.newsbulletion.mvp.activity.about.AboutView;

import butterknife.Bind;


/**
 * des:关于
 * Created by xsf
 * on 2016.09.16:57
 */
public class AboutActivity  extends MvpActivity<AboutPresenter> implements AboutView {


    @Bind(R.id.news_detail_photo_iv)
    ImageView newsDetailPhotoIv;
    @Bind(R.id.mask_view)
    View maskView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.news_detail_from_tv)
    TextView newsDetailFromTv;
    @Bind(R.id.tv_code_des)
    TextView tvCodeDes;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private String mShareLink;

    /**
     * 入口
     *
     * @param mContext
     */
    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, AboutActivity.class);
        mContext.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_about);
//        dynamicAddView(toolbar, "background", R.color.primary_dark);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
        toolbar.setTitle(getString(R.string.app_name));
        toolbarLayout.setTitle(getString(R.string.app_name));
        //分享
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShareLink == null) {
                    mShareLink = "";
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share));
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_contents, getString(R.string.app_name), mShareLink));
                startActivity(Intent.createChooser(intent, getTitle()));
            }
        });
    }

    @Override
    protected AboutPresenter createPresenter() {
        return new AboutPresenter(this);
    }

}
