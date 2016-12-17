package com.mrym.newsbulletion.mvp.fragment.category;

import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rx.service.NewsService;
import com.mrym.newsbulletion.rx.retrofit.TransformUtils;
import com.mrym.newsbulletion.rx.ServiceFactory;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;


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
        ServiceFactory.getInstance().createService(NewsService.class)
                .getNewsList("headline", gateGory, pageIndex)
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
                                   List<NewsSummary> news = stringListHashMap.get(gateGory);
                                   mvpView.loadComplete();
                                   mvpView.loadingSuccess(news, gateGory);
                               }
                           }

                );
    }

}
