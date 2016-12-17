package com.mrym.newsbulletion.mvp.activity.details;


import com.mrym.newsbulletion.domain.modle.NewsDetail;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rx.ServiceFactory;
import com.mrym.newsbulletion.rx.retrofit.TransformUtils;
import com.mrym.newsbulletion.rx.service.NewsService;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    public DetailsPresenter(DetailsView detailsView) {
        attachView(detailsView);
    }

    public void getOneNewsDataRequest(final String postId) {
        ServiceFactory.getInstance().createService(NewsService.class)
                .getNewDetail(postId)
                .compose(TransformUtils.<Map<String, NewsDetail>>defaultSchedulers())
                .subscribe(
                        new Subscriber<Map<String, NewsDetail>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(Map<String, NewsDetail> newsDetail) {
                                NewsDetail detail = newsDetail.get(postId);
                                mvpView.returnOneNewsData(detail);
                            }
                        });
    }
}
