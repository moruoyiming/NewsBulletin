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
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryModel;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;
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
        mvpView.showAdvertisement("http://p3.image.hiapk.com/uploads/allimg/150513/7730-150513155P8-52.jpg");
//        addSubscription(apiStores.startImage(), new SubscriberCallBack<>(new ApiCallback<SplashModel>() {
//
//            @Override
//            public void onSuccess(SplashModel model) {
//                Log.i(TAG,model.toString());
//                mvpView.showAdvertisement(model.getImg());
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//
//            }
//        }));

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
            }, 2000);
        }
    }

    public void close() {
        handler = null;
    }
}
