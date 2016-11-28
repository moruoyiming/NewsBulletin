package com.mrym.newsbulletion.mvp.activity.push;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.retrofit.ApiStores;
import com.mrym.newsbulletion.utils.net.BaseCallBack;
import com.mrym.newsbulletion.utils.net.NetUtils;

import org.xutils.http.RequestParams;

import java.util.List;
import java.util.Map;

/**
 * Created by Jian on 2016/11/28.
 */

public class PushPresenter extends BasePresenter<PushView> {

    public PushPresenter(PushView pushView) {
        attachView(pushView);
    }

    public void getPushMsg(int count,int pageIndex){
        RequestParams paramsData = new RequestParams(ApiStores.BASE_PATH+"nc/article/headline/"+"T1348648517839"+"/"+pageIndex+"-20.html");
        NetUtils.get(paramsData, new BaseCallBack<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                try {
                    Map<String, List<NewsSummary>> customerBean = new Gson().fromJson(result, new TypeToken<Map<String, List<NewsSummary>> >() {
                    }.getType());
                    List<NewsSummary> news = customerBean.get("T1348648517839");
                    mvpView.loadComplete();
                    mvpView.loadingSuccess(news);
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
    }
}
