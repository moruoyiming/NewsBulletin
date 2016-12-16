package com.mrym.newsbulletion.mvp;


import com.mrym.newsbulletion.retrofit.GankApi;
import com.mrym.newsbulletion.retrofit.AppClient;
import com.mrym.newsbulletion.retrofit.NewsApi;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by WuXiaolong on 2016/3/30.
 */
public class BasePresenter<V> implements Presenter<V> {
    public V mvpView;
    public NewsApi apiStores = AppClient.retrofit().create(NewsApi.class);
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }
    @Override
    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber)  {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        try {
            mCompositeSubscription.add(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
