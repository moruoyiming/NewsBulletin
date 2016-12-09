package com.mrym.newsbulletion.db;


import android.util.Log;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.db.entity.NewsChannelTableDB;
import com.mrym.newsbulletion.db.gen.DaoMaster;
import com.mrym.newsbulletion.db.gen.DaoSession;
import com.mrym.newsbulletion.db.gen.NewsChannelTableDBDao;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.utils.common.PrefUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wyk on 2016/7/12.
 */
public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;


    public GreenDaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(NewsApplication.getContext(), "notes-db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    public void initDB() {
        List<String> channelName = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_name_static));
        List<String> channelId = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_id_static));
        NewsChannelTableDBDao newsChannelTableDBDao = GreenDaoManager.getInstance().getSession().getNewsChannelTableDBDao();
        for (int i = 0; i < channelName.size(); i++) {
            NewsChannelTableDB newsChannelTableDB = new NewsChannelTableDB();
            newsChannelTableDB.setNewsChannelName(channelName.get(i));
            newsChannelTableDB.setNewsChannelId(channelId.get(i));
            newsChannelTableDB.setNewsChannelType(GlobalVariable.getType(channelId.get(i)));
            newsChannelTableDB.setNewsChannelSelect(true);
            newsChannelTableDB.setNewsChannelIndex(i);
            newsChannelTableDB.setNewsChannelFixed(true);
            newsChannelTableDBDao.insert(newsChannelTableDB);
        }
    }
    public void initDB2() {
        List<String> channelName = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_name));
        List<String> channelId = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_id));
        NewsChannelTableDBDao newsChannelTableDBDao = GreenDaoManager.getInstance().getSession().getNewsChannelTableDBDao();
        List<NewsChannelTableDB> list=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            NewsChannelTableDB newsChannelTableDB = new NewsChannelTableDB();
            newsChannelTableDB.setNewsChannelName(channelName.get(i));
            newsChannelTableDB.setNewsChannelId(channelId.get(i));
            newsChannelTableDB.setNewsChannelType(GlobalVariable.getType(channelId.get(i)));
            newsChannelTableDB.setNewsChannelSelect(false);
            newsChannelTableDB.setNewsChannelIndex(i);
            newsChannelTableDB.setNewsChannelFixed(true);
            list.add(newsChannelTableDB);
        }
        newsChannelTableDBDao.insertInTx(list);
    }
}  