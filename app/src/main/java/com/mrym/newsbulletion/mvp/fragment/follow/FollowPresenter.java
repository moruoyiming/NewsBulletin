package com.mrym.newsbulletion.mvp.fragment.follow;

import android.util.Log;

import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rx.retrofit.service.GankService;
import com.mrym.newsbulletion.rx.retrofit.HttpResult;
import com.mrym.newsbulletion.rx.retrofit.TransformUtils;
import com.mrym.newsbulletion.rx.ServiceFactory;
import com.mrym.newsbulletion.rx.retrofit.subscriber.HttpResultSubscriber;

import java.util.List;


/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class FollowPresenter extends BasePresenter<FollowView> {

    public FollowPresenter(FollowView followView) {
        attachView(followView);
    }

    public void getPhotoGrils(int size, int page) {
        ServiceFactory.getInstance().createService(GankService.class)
                .getPhotoList(page)
                .compose(TransformUtils.<HttpResult<List<PhotoGirl>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<PhotoGirl>>() {
                    @Override
                    public void onSuccess(List<PhotoGirl> girlData) {
                        mvpView.loadComplete();
                        mvpView.loadingSuccess(girlData);
                    }

                    @Override
                    public void _onError(Throwable e) {
                        mvpView.loadComplete();
                    }
                });

    }
}