package com.mrym.newsbulletion.domain.modle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Jian on 2016/9/13.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsBean extends BaseNewBean implements MultiItemEntity {
    //新闻类型 0文字新闻，1大图新闻，2小图新闻，3三张图新闻，4视频新闻
    /**
     * 文字新闻
     **/
    private String title;
    /**
     * 图片
     */
    private List<String> pics;
    /**
     * 视频新闻
     */
    private String videourl;

    /**
     * 视频时长
     */
    private String videotime;

    @Override
    public int getItemType() {
        return 0;
    }
}
