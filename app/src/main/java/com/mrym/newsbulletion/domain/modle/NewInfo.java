package com.mrym.newsbulletion.domain.modle;

import java.io.Serializable;

public class NewInfo implements Serializable {
    private String id;
    private String title;
    private long ppublishTime;
    private String auther;
    private String newslink;
    private String imgurl;

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

    public long getPpublishTime() {
        return ppublishTime;
    }

    public void setPpublishTime(long ppublishTime) {
        this.ppublishTime = ppublishTime;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getNewslink() {
        return newslink;
    }

    public void setNewslink(String newslink) {
        this.newslink = newslink;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "NewInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", ppublishTime=" + ppublishTime +
                ", auther='" + auther + '\'' +
                ", newslink='" + newslink + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}