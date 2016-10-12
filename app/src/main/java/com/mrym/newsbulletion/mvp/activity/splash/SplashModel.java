package com.mrym.newsbulletion.mvp.activity.splash;

import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;

/**
 * Created by Jian on 2016/10/12.
 */

public class SplashModel extends DefaultInterfaceBean {

    private String text;

    private String img;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
