package com.mrym.newsbulletion.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.activity.about.AboutPresenter;
import com.mrym.newsbulletion.mvp.activity.about.AboutView;
import com.mrym.newsbulletion.ui.BaseActivity;

import butterknife.BindView;


/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class AboutActivity extends BaseActivity<AboutPresenter> implements AboutView {
    public static String TAG = AboutActivity.class.getCanonicalName();
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.leftback_toobar_l1)
    RelativeLayout back;
    private String mShareLink;

    @Override
    protected AboutPresenter createPresenter() {
        return new AboutPresenter(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_about;
    }

    @Override
    protected void setUpView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                intent.putExtra(Intent.EXTRA_TEXT, NewsApplication.getContext().getString(R.string.share_contents, getString(R.string.app_name), mShareLink));
                startActivity(Intent.createChooser(intent, getTitle()));
            }
        });
    }

    @Override
    protected void destroyActivityBefore() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter = null;
        back = null;
    }
}
