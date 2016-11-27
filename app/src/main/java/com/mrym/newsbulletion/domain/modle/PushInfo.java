package com.mrym.newsbulletion.domain.modle;

import java.util.List;

/**
 * Created by Jian on 2016/8/16.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class PushInfo {


    private String id;
    private String title;
    private long pushTime;
    private long publishTime;
    private String auther;
    private String newslinks;
    private String picUrl;
    private List<NewInfo> msgInfos;
    private int pushType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPushTime() {
        return pushTime;
    }

    public void setPushTime(long pushTime) {
        this.pushTime = pushTime;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getNewslinks() {
        return newslinks;
    }

    public void setNewslinks(String newslinks) {
        this.newslinks = newslinks;
    }

    public List<NewInfo> getMsgInfos() {
        return msgInfos;
    }

    public void setMsgInfos(List<NewInfo> msgInfos) {
        this.msgInfos = msgInfos;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "pushMsgInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", pushTime=" + pushTime +
                ", publishTime=" + publishTime +
                ", auther='" + auther + '\'' +
                ", newslinks='" + newslinks + '\'' +
                ", msgInfos=" + msgInfos +
                ", pushType=" + pushType +
                '}';
    }
}
