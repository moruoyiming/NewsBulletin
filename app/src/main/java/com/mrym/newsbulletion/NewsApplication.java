package com.mrym.newsbulletion;

import android.app.Activity;

import android.content.Context;


import org.xutils.x;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import solid.ren.skinlibrary.base.SkinBaseApplication;


/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsApplication extends SkinBaseApplication {

    private static Context CONTEXT;
    private static Map<String, Activity> activities = new HashMap<String, Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
        x.Ext.init(NewsApplication.this);
        x.Ext.setDebug(true);

    }

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
