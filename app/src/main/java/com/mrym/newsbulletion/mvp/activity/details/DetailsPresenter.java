package com.mrym.newsbulletion.mvp.activity.details;

import com.mrym.newsbulletion.mvp.BasePresenter;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    public DetailsPresenter(DetailsView detailsView) {
        attachView(detailsView);
    }
}
