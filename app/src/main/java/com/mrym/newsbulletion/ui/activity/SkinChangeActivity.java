package com.mrym.newsbulletion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.skin.ChageSkinPresenter;
import com.mrym.newsbulletion.mvp.activity.skin.SkinView;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;

import butterknife.Bind;

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
    @Bind(R.id.leftback_toobar_l1)
    RelativeLayout back;
    @Bind(R.id.left_back_title)
    TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeskin);
        StatusBarCompat.translucentStatusBar(SkinChangeActivity.this,true);
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
        mTitle.setText("换肤");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        finish();
            }
        });
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mvpPresenter.chageSkinFromLocal("theme.skin");
            }
        });

    }
    /**
     * 入口
     *
     * @param mContext
     */
    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, SkinChangeActivity.class);
        mContext.startActivity(intent);
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