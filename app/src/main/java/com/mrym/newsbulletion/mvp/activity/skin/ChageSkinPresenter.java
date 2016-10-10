package com.mrym.newsbulletion.mvp.activity.skin;

import android.util.Log;

import com.mrym.newsbulletion.mvp.BasePresenter;

import solid.ren.skinlibrary.listener.ILoaderListener;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * Created by Jian on 2016/10/10.
 */

public class ChageSkinPresenter extends BasePresenter<SkinView> {

    public ChageSkinPresenter(SkinView skinView) {
        attachView(skinView);
    }

    public void restoreDefaultTheme(){
        SkinManager.getInstance().restoreDefaultTheme();
    }

    public void chageSkinFromNet(String skinUrl){
        if(skinUrl==null){
            return;
        }
        SkinManager.getInstance().loadSkinFromUrl(skinUrl, new ILoaderListener() {
            @Override
            public void onStart() {
                Log.i("ILoaderListener", "正在切换中");
                mvpView.showLoading("正在换肤皮肤文件");
            }

            @Override
            public void onSuccess() {
                Log.i("ILoaderListener", "切换成功");
                mvpView.hideLoading();
            }

            @Override
            public void onFailed(String errMsg) {
                Log.i("ILoaderListener", "切换失败:" + errMsg);
                mvpView.showMessage("换肤失败:" + errMsg);
            }

            @Override
            public void onProgress(int progress) {
                Log.i("ILoaderListener", "皮肤文件下载中:" + progress);
                mvpView.showProgress(progress);

            }
        });
    }
    public void chageSkinFromLocal(String skinpath){
        if(skinpath==null){
            return;
        }
        SkinManager.getInstance().loadSkin(skinpath, new ILoaderListener() {
            @Override
            public void onStart() {
                Log.i("ILoaderListener", "正在切换中");
                mvpView.showLoading("正在换肤皮肤文件");
            }

            @Override
            public void onSuccess() {
                Log.i("ILoaderListener", "切换成功");
                mvpView.hideLoading();
            }

            @Override
            public void onFailed(String errMsg) {
                Log.i("ILoaderListener", "切换失败:" + errMsg);
                mvpView.showMessage("换肤失败:" + errMsg);
            }

            @Override
            public void onProgress(int progress) {
                Log.i("ILoaderListener", "皮肤文件下载中:" + progress);
                mvpView.showProgress(progress);

            }
        });
    }
}
