package com.mrym.newsbulletion.mvp.activity.splash;

import com.mrym.newsbulletion.mvp.BasePresenter;

/**
 * Created by Jian on 2016/8/26.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    public SplashPresenter(SplashView splashView) {
        attachView(splashView);

    }

    public void showAdvertisement() {
        mvpView.showAdvertisement("http://p3.image.hiapk.com/uploads/allimg/150513/7730-150513155P8-52.jpg");
    }

    public void gotoNext() {
        mvpView.startMain();
    }

    public void close() {
    }
}
