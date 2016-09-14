package com.mrym.newsbulletion.mvp.fragment.category;

import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.domain.modle.NewsEntity;

import java.util.ArrayList;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GateGoryModel extends DefaultInterfaceBean {

    private ArrayList<NewsEntity> news;

    public ArrayList<NewsEntity> getNews() {
        return news;
    }

    public void setNews(ArrayList<NewsEntity> news) {
        this.news = news;
    }
}
