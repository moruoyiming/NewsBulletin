package com.mrym.newsbulletion.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 首页类型
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
@Entity
public class ChannelunSelected {
    @Id
    private Long id;
    private String newsChannelName;
    private String newsChannelId;
    private String newsChannelType;
    private boolean newsChannelSelect;
    private int newsChannelIndex;
    private Boolean newsChannelFixed;
    @Generated(hash = 288867173)
    public ChannelunSelected(Long id, String newsChannelName, String newsChannelId,
            String newsChannelType, boolean newsChannelSelect, int newsChannelIndex,
            Boolean newsChannelFixed) {
        this.id = id;
        this.newsChannelName = newsChannelName;
        this.newsChannelId = newsChannelId;
        this.newsChannelType = newsChannelType;
        this.newsChannelSelect = newsChannelSelect;
        this.newsChannelIndex = newsChannelIndex;
        this.newsChannelFixed = newsChannelFixed;
    }
    @Generated(hash = 666414372)
    public ChannelunSelected() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNewsChannelName() {
        return this.newsChannelName;
    }
    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }
    public String getNewsChannelId() {
        return this.newsChannelId;
    }
    public void setNewsChannelId(String newsChannelId) {
        this.newsChannelId = newsChannelId;
    }
    public String getNewsChannelType() {
        return this.newsChannelType;
    }
    public void setNewsChannelType(String newsChannelType) {
        this.newsChannelType = newsChannelType;
    }
    public boolean getNewsChannelSelect() {
        return this.newsChannelSelect;
    }
    public void setNewsChannelSelect(boolean newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }
    public int getNewsChannelIndex() {
        return this.newsChannelIndex;
    }
    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }
    public Boolean getNewsChannelFixed() {
        return this.newsChannelFixed;
    }
    public void setNewsChannelFixed(Boolean newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }

}
