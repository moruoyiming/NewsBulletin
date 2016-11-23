package com.mrym.newsbulletion.mvp.fragment.follow;

import android.util.Log;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.HostType;
import com.mrym.newsbulletion.domain.modle.GirlData;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rxjava.Api;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;
import com.mrym.newsbulletion.rxjava.baserx.RxManager;
import com.mrym.newsbulletion.rxjava.baserx.RxSchedulers;
import com.mrym.newsbulletion.rxjava.baserx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class FollowPresenter extends BasePresenter<FollowView> {

    public FollowPresenter(FollowView followView) {
        attachView(followView);
    }

    String[] str = new String[]{"http://ww1.sinaimg.cn/large/610dc034gw1fa1vkn6nerj20u011gdjm.jpg", "http://ww4.sinaimg.cn/large/610dc034gw1fa0ppsw0a7j20u00u0thp.jpg", "http://ww2.sinaimg.cn/large/610dc034gw1f9zjk8iaz2j20u011hgsc.jpg", "http://ww2.sinaimg.cn/large/610dc034jw1f9vyl2fqi0j20u011habc.jpg",
            "http://ww2.sinaimg.cn/large/610dc034jw1f9vyl2fqi0j20u011habc.jpg", "http://ww3.sinaimg.cn/large/610dc034gw1f9shm1cajkj20u00jy408.jpg", "http://ww3.sinaimg.cn/large/610dc034jw1f9rc3qcfm1j20u011hmyk.jpg"
            , "http://ww3.sinaimg.cn/large/610dc034jw1f9rc3qcfm1j20u011hmyk.jpg", "http://ww3.sinaimg.cn/large/610dc034jw1f9nuk0nvrdj20u011haci.jpg", "http://ww4.sinaimg.cn/large/610dc034jw1f9mp3xhjdhj20u00u0ta9.jpg", "http://ww2.sinaimg.cn/large/610dc034gw1f9lmfwy2nij20u00u076w.jpg", "http://ww2.sinaimg.cn/large/610dc034gw1f9kjnm8uo1j20u00u0q5q.jpg", "http://ww2.sinaimg.cn/large/610dc034jw1f9j7nvnwjdj20u00k0jsl.jpg",
            "http://ww4.sinaimg.cn/large/610dc034jw1f9frojtu31j20u00u0go9.jpg", "http://ww3.sinaimg.cn/large/610dc034jw1f9em0sj3yvj20u00w4acj.jpg", "http://ww4.sinaimg.cn/large/610dc034jw1f9dh2ohx2vj20u011hn0r.jpg", "http://ww1.sinaimg.cn/large/610dc034jw1f9cayjaa96j20u011hqbs.jpg", "http://ww2.sinaimg.cn/large/610dc034jw1f9b46kpoeoj20ku0kuwhc.jpg", "http://ww2.sinaimg.cn/large/610dc034jw1f978bh1cerj20u00u0767.jpg", "http://ww4.sinaimg.cn/large/610dc034gw1f96kp6faayj20u00jywg9.jpg"};

    public void getPhotoGrils(int size, int page) {
        List<PhotoGirl> girls = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            PhotoGirl photoGir = new PhotoGirl();
            photoGir.setCreatedAt("2016");
            photoGir.setUrl(str[i]);
            girls.add(photoGir);
        }
        mvpView.loadComplete();
        mvpView.loadingSuccess(girls);
//        Api.getDefault(HostType.GANK_GIRL_PHOTO)
//                .getPhotoList(size, page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SubscriberCallBack<>(new ApiCallback<GirlData>() {
//            @Override
//            public void onSuccess(GirlData girlData) {
//                try {
//                    List<PhotoGirl> list = girlData.getResults();
//                    Log.i("onSucc", list.toString());
//                    mvpView.loadComplete();
//                    mvpView.loadingSuccess(list);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
////                mvpView.loadingError(msg);
//                mvpView.loadComplete();
//            }
//
//            @Override
//            public void onCompleted() {
//                mvpView.loadComplete();
//            }
//        }));

    }
}
