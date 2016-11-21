package com.mrym.newsbulletion.domain.modle;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsEntity implements Serializable {
    /**
     * 新闻类别 ID
     */
    private Integer newsCategoryId;
    /**
     * 新闻类型
     */
    private String newsCategory;
    /**
     * 标记状态，如推荐之类的
     */
    private Integer mark;
    /**
     * 评论数量
     */
    private Double commentNum;
    /**
     * ID
     */
    private Integer id;
    /**
     * 新闻ID
     */
    private Integer newsId;
    /**
     * 标题
     */
    private String title;
    /**
     * 新闻源
     */
    private String source;
    /**
     * 发布时间
     */
    private String publishTime;
    /**
     * 总结
     */
    private String summary;
    /**
     * 摘要
     */
    private String newsAbstract;
    /**
     * 评论
     */
    private String comment;
    /**
     * 特殊标签，如广告推广之类的 ，可以为空
     */
    private String local;
    /**
     * 图片列表字符串
     */
    private String picListString;
    /**
     * 图片1 URL
     */
    private String picOne;
    /**
     * 图片2 URL
     */
    private String picTwo;
    /**
     * 图片3 URL
     */
    private String picThr;
    /**
     * 图片 列表
     */
    private ArrayList<String> picList;
    /**
     * 图片类型是否为大图
     */
    private Boolean isLarge;
    /**
     * 阅读状态 ，读过的话显示灰色背景
     */
    private Boolean readStatus;
    /**
     * 收藏状态
     */
    private Boolean collectStatus;
    /**
     * 喜欢 状态
     */
    private Boolean likeStatus;
    /**
     * 感兴趣状态
     */
    private Boolean interestedStatus;
    /**
     * 视频源 URL
     */
    private String videoUrl;
    /**
     * 视频图片 URL
     */
    private String videoPic;
    /**
     * 类型
     */
    private int newType;
    /**
     * 作者
     */
    private String author;
    /**
     * 作者头像
     */
    private String headimg;
    /**
     * 是否已读
     */
    private boolean read;


    public Integer getNewsCategoryId() {
        return newsCategoryId;
    }

    public void setNewsCategoryId(Integer newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Double getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Double commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPicListString() {
        return picListString;
    }

    public void setPicListString(String picListString) {
        this.picListString = picListString;
    }

    public String getPicOne() {
        return picOne;
    }

    public void setPicOne(String picOne) {
        this.picOne = picOne;
    }

    public String getPicTwo() {
        return picTwo;
    }

    public void setPicTwo(String picTwo) {
        this.picTwo = picTwo;
    }

    public String getPicThr() {
        return picThr;
    }

    public void setPicThr(String picThr) {
        this.picThr = picThr;
    }

    public ArrayList<String> getPicList() {
        return picList;
    }

    public void setPicList(ArrayList<String> picList) {
        this.picList = picList;
    }

    public Boolean getLarge() {
        return isLarge;
    }

    public void setLarge(Boolean large) {
        isLarge = large;
    }

    public Boolean getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Boolean readStatus) {
        this.readStatus = readStatus;
    }

    public Boolean getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Boolean collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Boolean getInterestedStatus() {
        return interestedStatus;
    }

    public void setInterestedStatus(Boolean interestedStatus) {
        this.interestedStatus = interestedStatus;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoPic() {
        return videoPic;
    }

    public void setVideoPic(String videoPic) {
        this.videoPic = videoPic;
    }

    public int getNewType() {
        return newType;
    }

    public void setNewType(int newType) {
        this.newType = newType;
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

    @Override
    public String toString() {
        return "NewsEntity{" +
                "newsCategoryId=" + newsCategoryId +
                ", newsCategory='" + newsCategory + '\'' +
                ", mark=" + mark +
                ", commentNum=" + commentNum +
                ", id=" + id +
                ", newsId=" + newsId +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", publishTime=" + publishTime +
                ", summary='" + summary + '\'' +
                ", newsAbstract='" + newsAbstract + '\'' +
                ", comment='" + comment + '\'' +
                ", local='" + local + '\'' +
                ", picListString='" + picListString + '\'' +
                ", picOne='" + picOne + '\'' +
                ", picTwo='" + picTwo + '\'' +
                ", picThr='" + picThr + '\'' +
                ", picList=" + picList +
                ", isLarge=" + isLarge +
                ", readStatus=" + readStatus +
                ", collectStatus=" + collectStatus +
                ", likeStatus=" + likeStatus +
                ", interestedStatus=" + interestedStatus +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoPic='" + videoPic + '\'' +
                ", newType=" + newType +
                ", author='" + author + '\'' +
                ", headimg='" + headimg + '\'' +
                ", read=" + read +
                '}';
    }

}
