package com.mrym.newsbulletion;


import android.content.Context;

import com.mrym.newsbulletion.db.GreenDaoManager;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.utils.common.PrefUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.xutils.x;

import solid.ren.skinlibrary.base.SkinBaseApplication;


public class NewsApplication extends SkinBaseApplication {

    private static Context CONTEXT;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
        refWatcher = LeakCanary.install(this);
        GreenDaoManager.getInstance();
        initDb();
    }


    /**
     * 初始化新闻分类
     */
    public void initDb() {
        if (PrefUtils.getBoolean(NewsApplication.getContext(), GlobalVariable.FIRST_LOGIN_STATE, true)) {
            PrefUtils.putBoolean(NewsApplication.getContext(), GlobalVariable.FIRST_LOGIN_STATE, false);
            GreenDaoManager.getInstance().initDB();
            GreenDaoManager.getInstance().initDB2();
        } else {

        }
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        NewsApplication application = (NewsApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

    public static Context getContext() {
        return CONTEXT;
    }

}
