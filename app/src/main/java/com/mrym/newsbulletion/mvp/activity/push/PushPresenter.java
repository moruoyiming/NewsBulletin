package com.mrym.newsbulletion.mvp.activity.push;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rx.ServiceFactory;
import com.mrym.newsbulletion.rx.retrofit.TransformUtils;
import com.mrym.newsbulletion.rx.service.NewsService;

import org.xutils.http.RequestParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by Jian on 2016/11/28.
 */

public class PushPresenter extends BasePresenter<PushView> {

    public PushPresenter(PushView pushView) {
        attachView(pushView);
    }

    public void getPushMsg(int count, int pageIndex) {
        ServiceFactory.getInstance().createService(NewsService.class)
                .getNewsList("headline", "T1348648517839", pageIndex)
                .compose(TransformUtils.<HashMap<String, List<NewsSummary>>>defaultSchedulers())
                .subscribe(new Subscriber<HashMap<String, List<NewsSummary>>>() {
                               @Override
                               public void onCompleted() {
                                   mvpView.loadComplete();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   mvpView.loadComplete();
                               }

                               @Override
                               public void onNext(HashMap<String, List<NewsSummary>> stringListHashMap) {
                                   List<NewsSummary> news = stringListHashMap.get("T1348648517839");
                                   mvpView.loadComplete();
                                   mvpView.loadingSuccess(news);
                               }
                           }

                );
    }
}
