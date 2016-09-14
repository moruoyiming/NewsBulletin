package com.mrym.newsbulletion.db.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 首页类型
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
@Table(name = "home_category")
public class HomeCateGory {
    //id
    @Column(name = "id", isId = true)
    private Long id;
    //类型，推荐，热点，视频，社会
    @Column(name = "category")
    //类型 字段 调用接口使用
    private String category;
    @Column(name = "field")
    private String field;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "HomeCateGory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}
