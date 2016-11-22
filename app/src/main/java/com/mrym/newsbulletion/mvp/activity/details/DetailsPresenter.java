package com.mrym.newsbulletion.mvp.activity.details;

import android.util.Log;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.modle.NewsDetail;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;

import java.util.List;
import java.util.Map;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    public DetailsPresenter(DetailsView detailsView) {
        attachView(detailsView);
    }

    public void getOneNewsDataRequest(String postId) {
        addSubscription(apiStores.getNewDetail(postId), new SubscriberCallBack<>(new ApiCallback<NewsDetail>() {
            @Override
            public void onSuccess(NewsDetail newsDetail) {
                mvpView.returnOneNewsData(newsDetail);
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
