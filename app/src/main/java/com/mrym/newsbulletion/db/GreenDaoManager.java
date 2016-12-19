package com.mrym.newsbulletion.db;


import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.db.entity.ChannelSelected;
import com.mrym.newsbulletion.db.entity.ChannelunSelected;
import com.mrym.newsbulletion.db.gen.ChannelSelectedDao;
import com.mrym.newsbulletion.db.gen.ChannelunSelectedDao;
import com.mrym.newsbulletion.db.gen.DaoMaster;
import com.mrym.newsbulletion.db.gen.DaoSession;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
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
        ChannelSelectedDao newsChannelTableDBDao = GreenDaoManager.getInstance().getSession().getChannelSelectedDao();
        for (int i = 0; i < channelName.size(); i++) {
            ChannelSelected newsChannelTableDB = new ChannelSelected();
            newsChannelTableDB.setNewsChannelName(channelName.get(i));
            newsChannelTableDB.setNewsChannelId(channelId.get(i));
            newsChannelTableDB.setNewsChannelType(GlobalVariable.HEADLINE_TYPE);
            newsChannelTableDB.setNewsChannelSelect(true);
            newsChannelTableDB.setNewsChannelIndex(i);
            newsChannelTableDB.setNewsChannelFixed(true);
            newsChannelTableDBDao.insert(newsChannelTableDB);
        }
    }
    public void initDB2() {
        List<String> channelName = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_name));
        List<String> channelId = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_id));
        ChannelunSelectedDao newsChannelTableDBDao = GreenDaoManager.getInstance().getSession().getChannelunSelectedDao();
        List<ChannelunSelected> list=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            ChannelunSelected newsChannelTableDB = new ChannelunSelected();
            newsChannelTableDB.setNewsChannelName(channelName.get(i));
            newsChannelTableDB.setNewsChannelId(channelId.get(i));
            newsChannelTableDB.setNewsChannelType(GlobalVariable.HEADLINE_TYPE);
            newsChannelTableDB.setNewsChannelSelect(false);
            newsChannelTableDB.setNewsChannelIndex(i);
            newsChannelTableDB.setNewsChannelFixed(true);
            list.add(newsChannelTableDB);
        }
        newsChannelTableDBDao.insertInTx(list);
    }
}  