package com.mrym.newsbulletion;

import android.app.Activity;

import android.content.Context;
import android.util.Log;


import com.mrym.newsbulletion.db.GreenDaoManager;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.utils.common.PrefUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import solid.ren.skinlibrary.base.SkinBaseApplication;




public class NewsApplication extends SkinBaseApplication {

    private static Context CONTEXT;
    private static Map<String, Activity> activities = new HashMap<String, Activity>();
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
        x.Ext.init(NewsApplication.this);
        x.Ext.setDebug(true);
        refWatcher = LeakCanary.install(this);
        GreenDaoManager.getInstance();
        initDb();
    }

    /**
     * 初始化新闻分类
     */
    public void initDb(){
        if(PrefUtils.getBoolean(NewsApplication.getContext(), GlobalVariable.FIRST_LOGIN_STATE,true)){
            PrefUtils.putBoolean(NewsApplication.getContext(),GlobalVariable.FIRST_LOGIN_STATE,false);
            GreenDaoManager.getInstance().initDB();
            GreenDaoManager.getInstance().initDB2();
        }else{

        }
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        NewsApplication application = (NewsApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

    public static Context getContext() {
        return CONTEXT;
    }

    /**
     * 获取管理类中注册的所有Activity的map
     *
     * @return
     */
    public static Map<String, Activity> getActivitys() {
        return activities;
    }

    /**
     * 注册Activity
     *
     * @param value
     * @param key
     */
    public static void addActivity(Activity value, String key) {
        activities.put(key, value);
    }

    /**
     * 将key对应的Activity关闭
     *
     * @param key
     */
    public static void finishActivity(String key) {
        activities.remove(key).finish();
    }

    /**
     * 将key对应的Activity移除
     *
     * @param key
     */
    public static void removeActivity(String key) {
        activities.remove(key);
    }

    /**
     * finish掉所有的Activity移除所有的Activity
     */
    public static void removeAllActivity() {
        Iterator<Activity> iterActivity = activities.values().iterator();
        while (iterActivity.hasNext()) {
            iterActivity.next().finish();
        }
        activities.clear();
    }


}
