package com.mrym.newsbulletion;

import android.app.Application;

import android.content.Context;


/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsApplication extends Application {

    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
    }

    public static Context getContext() {
        return CONTEXT;
    }

}
