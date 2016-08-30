package com.mrym.newsbulletion.mvp.activity.splash;

import android.Manifest;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public SplashPresenter(SplashView splashView) {
        attachView(splashView);
    }


    public void initSplash(WindowManager manager) {
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        Log.e(TAG, "metrics.scaledDensity : " + metrics.scaledDensity);
        String fileName = AppUtils.getBaseFilePath() + "/startPage/";

        if (metrics.scaledDensity <= 0.75) {
            // ldpi
            fileName += "start_page_240x320.png";
        } else if (metrics.scaledDensity <= 1.0) {
            // mdpi
            fileName += "start_page_320x480.png";
        } else if (metrics.scaledDensity <= 1.5) {
            // hdpi
            fileName += "start_page_480x800.png";
        } else if (metrics.scaledDensity <= 2.0) {
            // xdpi
            fileName += "start_page_720x1280.png";
        } else {
            // xxhdpi 3.0
            fileName += "start_page_1080x1920.png";
        }

        File file = new File(fileName);
        Log.e(TAG, "startPageName -->" + fileName + "file.exists() --> " + file.exists());
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(fileName);
            mvpView.showSplash(bm);
        } else {
            mvpView.showSplash(R.mipmap.splash);
            if (FileOperate.copyStartPage()) {
                FileOperate.unZipStartPage();
            }
        }
    }

    public void requestPermission() {
        Dexter.checkPermissions(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
                    Log.d(TAG, "GrantedPermissionName:" + response.getPermissionName());
                }
                for (PermissionDeniedResponse response : report.getDeniedPermissionResponses()) {
                    ToastUtils.show("需要同意所有权限请求");
                    mvpView.shutDown();
                    return;
                }
                gotoNext();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);
    }


    public void gotoNext() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                AccountTool tool = mvpView.getAccountTool();
                if (tool.checkAccountIfExits()) {
//                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(AppUtils.getContext());
//                    long last = sp.getLong(GlobalVariable.LAST_LOGIN_TIME, 0);
//                    long current = SystemClock.currentThreadTimeMillis();
//                    if (current - last <= 1000 * 3600 * 24 * 7) {
//                        mvpView.startMain();
//                        return;
//                    }
//                    ToastUtils.show(R.string.login_out_of_data);
//                    tool.removeAccount();
                    mvpView.startMain();
                } else {
                    mvpView.startLogin();
                }

            }
        }, 1500);
    }
}
