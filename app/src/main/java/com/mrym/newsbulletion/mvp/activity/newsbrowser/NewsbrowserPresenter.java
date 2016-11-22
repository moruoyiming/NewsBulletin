package com.mrym.newsbulletion.mvp.activity.newsbrowser;

import com.mrym.newsbulletion.mvp.BasePresenter;

/**
 * Created by Jian on 2016/11/22.
 */

public class NewsbrowserPresenter extends BasePresenter<NewsbrowserView> {

    public NewsbrowserPresenter(NewsbrowserView newsbrowserView) {
        attachView(newsbrowserView);
    }
    public void loadUrl(){

    }

}
