package com.mrym.newsbulletion.mvp.fragment.mine;


import android.util.Log;

import com.mrym.newsbulletion.domain.modle.UserBean;
import com.mrym.newsbulletion.domain.modle.WeatherBean;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rx.ServiceFactory;
import com.mrym.newsbulletion.rx.retrofit.TransformUtils;
import com.mrym.newsbulletion.rx.service.WeatherService;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class MinePresenter extends BasePresenter<MineView> {

    public MinePresenter(MineView view) {
        attachView(view);
    }

    public void initUserData() {

        UserBean userBean = new UserBean();
        userBean.setNickName("Brigitte");
        userBean.setAge("24");
        userBean.setBirthDate("1992-03-01");
        userBean.setCustomerName("莫若已明");
        userBean.setHeadImg("http://www.bz55.com/uploads/allimg/150604/140-150604112059-50.jpg");
        userBean.setBackgroudImg("http://img.article.pchome.net/00/34/48/17/pic_lib/wm/1920JZYL_4012.jpg");
        mvpView.initUserData(userBean);
    }

    public void getWeather(String city, String province) {

        try {
            ServiceFactory.getInstance().createService(WeatherService.class)
                    .getWeather("19d1e70ae8ee0", city, province)
                    .compose(TransformUtils.<WeatherBean>defaultSchedulers())
                    .subscribe(new Subscriber<WeatherBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(WeatherBean weatherBean) {
                            Log.d("what", weatherBean.getResult().get(0).getCity());
                            mvpView.showWeather(weatherBean.getResult().get(0));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
