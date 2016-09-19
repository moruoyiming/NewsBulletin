package com.mrym.newsbulletion.mvp.activity.splash;

import android.Manifest;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.authenticator.account.AccountTool;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.utils.common.AppUtils;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.mrym.newsbulletion.utils.file.FileOperate;

import java.io.File;
import java.util.List;

/**
 * Created by Jian on 2016/8/26.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    public static final String TAG = SplashPresenter.class.getCanonicalName();

    public Handler handler;

    public SplashPresenter(SplashView splashView) {
        attachView(splashView);
        handler = new Handler();
    }

    public void showAdvertisement() {
        mvpView.showAdvertisement("http://mmbiz.qpic.cn/mmbiz_jpg/0DSXLNibvwCDAVwEOiaNrA7Kyw8Ww5x6CnB6kDexG4YEbjhvpakupicsvTlMKriaibUy63ARkK2icWUCAQ6uFOwoIaVg/640?wx_fmt=jpeg&tp=jpeg&wxfrom=5&wx_lazy=1");
    }

    public void gotoNext() {
        if (handler != null) {
            handler.postDelayed(new Runnable() {
                public void run() {
//                    AccountTool tool = mvpView.getAccountTool();
//                    if (tool.checkAccountIfExits()) {
//                        mvpView.startMain();
//                    } else {
//                        mvpView.startLogin();
//                    }
                    if (handler != null) {
                        mvpView.startMain();
                    }
                }
            }, 1000);
        }
    }

    public void close() {
        handler = null;
    }
}
