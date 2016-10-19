package com.mrym.newsbulletion.mvp.fragment.videos;

import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.domain.modle.VideoData;

import java.util.ArrayList;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GateGoryVideoModel extends DefaultInterfaceBean {

    private ArrayList<VideoData> videoDatas;

    public ArrayList<VideoData> getNews() {
        return videoDatas;
    }

    public void setNews(ArrayList<VideoData> videoDatas) {
        this.videoDatas = videoDatas;
    }
}
