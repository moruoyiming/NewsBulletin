package com.mrym.newsbulletion.mvp.fragment.home;

import android.text.TextUtils;

import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;
import com.mrym.newsbulletion.utils.common.ToastUtils;


/**
 * Created by Shawn on 2016/8/18.
 */
public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView view) {
        attachView(view);
    }

    public void getOrderPriceSum(long accountId) {
//        addSubscription(apiStores.getOrderPriceSum(accountId), new SubscriberCallBack<>(new ApiCallback<HomeModel>() {
//            @Override
//            public void onSuccess(HomeModel model) {
//                if (TextUtils.equals(model.getState(), GlobalVariable.SUCCESS)) {
//                    mvpView.getSuccess(model.getOrder());
//                } else {
//                    ToastUtils.show(model.getMsg());
//                }
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                mvpView.getFailure(code, msg);
//            }
//
//            @Override
//            public void onCompleted() {}
//        }));
    }
}
