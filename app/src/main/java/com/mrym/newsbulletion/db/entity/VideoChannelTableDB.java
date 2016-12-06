package com.mrym.newsbulletion.db.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 首页类型
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
@Entity
public class VideoChannelTableDB {
    @Id
    private Long id;
    private String channelId;
    private String channelName;

    @Generated(hash = 188178537)
    public VideoChannelTableDB(Long id, String channelId, String channelName) {
        this.id = id;
        this.channelId = channelId;
        this.channelName = channelName;
    }

    @Generated(hash = 198538253)
    public VideoChannelTableDB() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
