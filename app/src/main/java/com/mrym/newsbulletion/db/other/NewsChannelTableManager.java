/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mrym.newsbulletion.db.other;



import android.util.Log;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.db.GreenDaoManager;
import com.mrym.newsbulletion.db.entity.NewsChannelTableDB;
import com.mrym.newsbulletion.db.gen.NewsChannelTableDBDao;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsChannelTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 首页管理
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsChannelTableManager {


    /**
     * 加载新闻类型
     * @return
     */
    public static List<NewsChannelTable> loadNewsChannelsMine() {
        List<String> channelName = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_name));
        List<String> channelId = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_id));
        ArrayList<NewsChannelTable>newsChannelTables=new ArrayList<>();
        ArrayList<NewsChannelTableDB> newsChannelTablesdb=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            NewsChannelTable entity = new NewsChannelTable(channelName.get(i), channelId.get(i)
                    , GlobalVariable.getType(channelId.get(i)), i <= 5, i, false);
            newsChannelTables.add(entity);
            NewsChannelTableDB db=new NewsChannelTableDB();
            db.setNewsChannelName(channelName.get(i));
            db.setNewsChannelId(channelId.get(i));
            db.setNewsChannelType(GlobalVariable.getType(channelId.get(i)));
            db.setNewsChannelSelect(i <= 5);
            db.setNewsChannelIndex(i);
            db.setNewsChannelFixed(false);
            newsChannelTablesdb.add(db);
            Log.i("what","  "+db.toString());
        }
        return newsChannelTables;
    }
    /**
     * 加载固定新闻类型
     * @return
     */
    public static List<NewsChannelTable> loadNewsChannelsStatic() {
        List<String> channelName = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_name_static));
        List<String> channelId = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.news_channel_id_static));
        ArrayList<NewsChannelTable>newsChannelTables=new ArrayList<>();
        NewsChannelTableDBDao newsChannelTableDBDao = GreenDaoManager.getInstance().getSession().getNewsChannelTableDBDao();
        for (int i = 0; i < channelName.size(); i++) {
            NewsChannelTable entity = new NewsChannelTable(channelName.get(i), channelId.get(i)
                    , GlobalVariable.getType(channelId.get(i)), i <= 5, i, true);
            newsChannelTables.add(entity);
//            NewsChannelTableDB newsChannelTableDB = new NewsChannelTableDB();
//            newsChannelTableDB.setNewsChannelName(channelName.get(i));
//            newsChannelTableDB.setNewsChannelId(channelId.get(i));
//            newsChannelTableDB.setNewsChannelType(GlobalVariable.getType(channelId.get(i)));
//            newsChannelTableDB.setNewsChannelSelect(i <= 5);
//            newsChannelTableDB.setNewsChannelIndex(i);
//            newsChannelTableDB.setNewsChannelFixed(true);
//            newsChannelTableDBDao.insert(newsChannelTableDB);
//            List<NewsChannelTableDB> list=GreenDaoManager.getInstance().getSession().getNewsChannelTableDBDao().queryBuilder().build().list();
//            Log.i("fuck you ",list.toString()+"    wo coa ");
        }
        return newsChannelTables;
    }

}
