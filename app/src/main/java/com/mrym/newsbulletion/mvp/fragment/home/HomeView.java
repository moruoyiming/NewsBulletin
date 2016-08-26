package com.mrym.newsbulletion.mvp.fragment.home;


import com.mrym.newsbulletion.domain.modle.HomeOrderBean;

/**
 * Created by Shawn on 2016/8/18.
 */
public interface HomeView {
    void getSuccess(HomeOrderBean order);

    void getFailure(int code, String msg);
}
