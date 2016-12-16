package com.mrym.newsbulletion.mvp.fragment.home;

import com.mrym.newsbulletion.mvp.BasePresenter;


/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView view) {
        attachView(view);
    }

    public void getOrderPriceSum(long accountId) {
    }
}
