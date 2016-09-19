package com.mrym.newsbulletion.mvp.fragment.home;


import com.mrym.newsbulletion.domain.modle.HomeOrderBean;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface HomeView {
    void getSuccess(HomeOrderBean order);

    void getFailure(int code, String msg);
}
