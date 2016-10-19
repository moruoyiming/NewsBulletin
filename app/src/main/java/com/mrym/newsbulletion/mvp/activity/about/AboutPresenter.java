package com.mrym.newsbulletion.mvp.activity.about;

import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.activity.details.DetailsView;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class AboutPresenter extends BasePresenter<AboutView> {

    public AboutPresenter(AboutView aboutView) {
        attachView(aboutView);
    }
}
