package com.mrym.newsbulletion.mvp.fragment.mine;

import com.mrym.newsbulletion.domain.modle.Weather;
import com.mrym.newsbulletion.domain.modle.UserBean;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface MineView {

    void showLoading(String msg);

    void hideLoading(String msg, int code);

    void initUserData(UserBean userBean);

    void startLoginActivity();

    void startUserDetilsActivity();

    void showWeather(Weather weather);
}
