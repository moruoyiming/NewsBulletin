package com.mrym.newsbulletion.mvp.fragment.mine;


import android.util.Log;

import com.mrym.newsbulletion.domain.modle.UserBean;
import com.mrym.newsbulletion.mvp.BasePresenter;

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

}
