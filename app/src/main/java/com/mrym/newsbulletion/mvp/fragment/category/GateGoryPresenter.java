package com.mrym.newsbulletion.mvp.fragment.category;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.mrym.newsbulletion.domain.constans.HostType;
import com.mrym.newsbulletion.domain.constans.UrlFactory;
import com.mrym.newsbulletion.domain.modle.GirlData;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.retrofit.ApiStores;
import com.mrym.newsbulletion.rxjava.Api;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;
import com.mrym.newsbulletion.rxjava.baserx.RxSchedulers;
import com.mrym.newsbulletion.utils.common.JsonUtils;
import com.mrym.newsbulletion.utils.net.BaseCallBack;
import com.mrym.newsbulletion.utils.net.NetUtils;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GateGoryPresenter extends BasePresenter<GateGoryView> {

    public GateGoryPresenter(GateGoryView getGoryView) {
        attachView(getGoryView);
    }

    public void getGategoryData(final String gateGory, int pageIndex) {
        RequestParams paramsData = new RequestParams(ApiStores.BASE_PATH+"nc/article/headline/"+gateGory+"/"+pageIndex+"-20.html");
        NetUtils.get(paramsData, new BaseCallBack<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                try {
                    Map<String, List<NewsSummary>>  customerBean = new Gson().fromJson(result, new TypeToken<Map<String, List<NewsSummary>> >() {
                    }.getType());
                    List<NewsSummary> news = customerBean.get(gateGory);
                    mvpView.loadComplete();
                    mvpView.loadingSuccess(news,gateGory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onFinished() {
            }


        });
//        mRxManage.add(Api.getDefault(HostType.NETEASE_NEWS_VIDEO)
//                .getNewsList("headline", gate, pageIndex)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe( new SubscriberCallBack<>(new ApiCallback<Map<String, List<NewsSummary>>>() {
//                    @Override
//                    public void onSuccess(Map<String, List<NewsSummary>> model) {
//                        try {
//                            List<NewsSummary> wgat = model.get(gate);
//                            Log.i("NewsSummary", wgat.get(0).getEname());
//                            mvpView.loadComplete();
//                            mvpView.loadingSuccess(wgat,gate);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
////                mvpView.loadingError(msg);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//                })));
    }

}
