package com.mrym.newsbulletion.db.utils;

import android.content.Context;
import android.util.Log;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.db.DatabaseUtil;
import com.mrym.newsbulletion.db.entity.HomeCateGory;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class HomeCateGoryUtils extends DatabaseUtil<HomeCateGory> {

    private static final String TAG = HomeCateGoryUtils.class.getCanonicalName();
    private static HomeCateGoryUtils INSTANCE;

    public static HomeCateGoryUtils getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new HomeCateGoryUtils(context.getApplicationContext());
        }
        return INSTANCE;
    }

    public void initData() {
        if (getHomeCateGory() == null) {
            HomeCateGory homeCateGory = new HomeCateGory();
            homeCateGory.setCategory(mContext.getString(R.string.demo_tab_1));
            homeCateGory.setField(GlobalVariable.HEADLINE_ID);
            HomeCateGory homeCateGory2 = new HomeCateGory();
            homeCateGory2.setCategory(mContext.getString(R.string.demo_tab_2));
            homeCateGory2.setField(GlobalVariable.HOUSE_ID);
            HomeCateGory homeCateGory3 = new HomeCateGory();
            homeCateGory3.setCategory(mContext.getString(R.string.demo_tab_3));
            homeCateGory3.setField(GlobalVariable.ENTERTAINMENT_ID);
            HomeCateGory homeCateGory4 = new HomeCateGory();
            homeCateGory4.setCategory(mContext.getString(R.string.demo_tab_4));
            homeCateGory4.setField(GlobalVariable.SPORTS_ID);
            HomeCateGory homeCateGory5 = new HomeCateGory();
            homeCateGory5.setCategory(mContext.getString(R.string.demo_tab_5));
            homeCateGory5.setField(GlobalVariable.MOVIE_ID);
            HomeCateGory homeCateGory6 = new HomeCateGory();
            homeCateGory6.setCategory(mContext.getString(R.string.demo_tab_6));
            homeCateGory6.setField(GlobalVariable.CAR_ID);
            HomeCateGory homeCateGory7 = new HomeCateGory();
            homeCateGory7.setCategory(mContext.getString(R.string.demo_tab_7));
            homeCateGory7.setField(GlobalVariable.FASHION_ID);
            add(homeCateGory);
            add(homeCateGory2);
            add(homeCateGory3);
            add(homeCateGory4);
            add(homeCateGory5);
            add(homeCateGory6);
            add(homeCateGory7);
            Log.i(TAG, "初始化完成,数据size:");
        } else {
            Log.i(TAG, "初始化已完成");
        }

    }

    /**
     * 获取所有本地数据类型对象
     *
     * @return
     */
    public ArrayList<HomeCateGory> getHomeCateGory() {
        try {
            ArrayList<HomeCateGory> dbHomeCateGory = getAllCache();
            if (dbHomeCateGory == null) {
                dbHomeCateGory = (ArrayList<HomeCateGory>) getDefaultDbManager().selector(HomeCateGory.class).orderBy("id")
                        .findAll();
                createCache(dbHomeCateGory);
            }
            return dbHomeCateGory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 添加新闻类型到本地
     *
     * @param dbHomeCateGory 要添加的类型数据对象
     **/
    public void add(HomeCateGory dbHomeCateGory) {
        try {
            // 检查好友是否已经存在
            DbManager dbManager = getDefaultDbManager();
            dbManager.save(dbHomeCateGory);
            Long id = getLastInsertRowIdByTableName(dbManager, "home_category");
            dbHomeCateGory.setId(id);
            addCache(dbHomeCateGory);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public HomeCateGoryUtils(Context context) {
        super(context);
    }
}
