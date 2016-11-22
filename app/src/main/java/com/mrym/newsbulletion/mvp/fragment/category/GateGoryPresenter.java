package com.mrym.newsbulletion.mvp.fragment.category;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;
import com.mrym.newsbulletion.utils.common.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GateGoryPresenter extends BasePresenter<GateGoryView> {

    public GateGoryPresenter(GateGoryView getGoryView) {
        attachView(getGoryView);
    }

    public void getGategoryData(String gateGory, int pageIndex) {
        final String gate = gateGory;
        addSubscription(apiStores.getNewsList("headline", gate, pageIndex), new SubscriberCallBack<>(new ApiCallback<Map<String, List<NewsSummary>>>() {
            @Override
            public void onSuccess(Map<String, List<NewsSummary>> model) {
                try {
                    List<NewsSummary> wgat = model.get(gate);
                    Log.i("NewsSummary", wgat.get(0).getEname());
                    mvpView.loadComplete();
                    mvpView.loadingSuccess(wgat);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int code, String msg) {
//                mvpView.loadingError(msg);
            }

            @Override
            public void onCompleted() {
            }
        }));
    }

}
