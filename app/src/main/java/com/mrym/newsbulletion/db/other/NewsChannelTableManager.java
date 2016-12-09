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
    public static List<NewsChannelTableDB> loadNewsChannelsMine() {
        List<NewsChannelTableDB> list= null;
        try {
            list = GreenDaoManager.getInstance().getSession().getNewsChannelTableDBDao().queryBuilder().where(NewsChannelTableDBDao.Properties.NewsChannelSelect.eq(false)).build().list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 加载固定新闻类型
     * @return
     */
    public static List<NewsChannelTableDB> loadNewsChannelsStatic() {

        List<NewsChannelTableDB> list= null;
        try {
            list = GreenDaoManager.getInstance().getSession().getNewsChannelTableDBDao().queryBuilder().where(NewsChannelTableDBDao.Properties.NewsChannelSelect.eq(true)).build().list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
