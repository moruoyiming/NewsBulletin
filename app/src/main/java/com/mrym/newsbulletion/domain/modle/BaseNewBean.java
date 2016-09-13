package com.mrym.newsbulletion.domain.modle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Jian on 2016/9/13.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class BaseNewBean implements Serializable {
    //新闻ID
    private Long id;
    //作者
    private String author;
    //作者头像
    private String headimg;
    //是否已读
    private boolean read;
    //发布时间
    private String sendTime;
    //评论条数
    private int commoncount;
    //新闻是否推荐，广告,hot
    private int status;
    //是否置顶
    private boolean top;
    //新闻类型 文字新闻，大图新闻，小图新闻，三张图新闻，视频新闻
    private int newType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getCommoncount() {
        return commoncount;
    }

    public void setCommoncount(int commoncount) {
        this.commoncount = commoncount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public int getNewType() {
        return newType;
    }

    public void setNewType(int newType) {
        this.newType = newType;
    }

    @Override
    public String toString() {
        return "BaseNewBean{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", headimg='" + headimg + '\'' +
                ", read=" + read +
                ", sendTime='" + sendTime + '\'' +
                ", commoncount=" + commoncount +
                ", status=" + status +
                ", top=" + top +
                ", newType=" + newType +
                '}';
    }
}
