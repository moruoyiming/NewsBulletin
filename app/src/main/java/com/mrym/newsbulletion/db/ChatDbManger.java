package com.mrym.newsbulletion.db;

import com.mrym.newsbulletion.db.entity.NewsChannelTableDB;

import org.greenrobot.greendao.AbstractDao;

public class ChatDbManger extends AbstractDatabaseManager<NewsChannelTableDB, Long> {
    @Override
    public AbstractDao<NewsChannelTableDB, Long> getAbstractDao() {
        return daoSession.getNewsChannelTableDBDao();
    }
}