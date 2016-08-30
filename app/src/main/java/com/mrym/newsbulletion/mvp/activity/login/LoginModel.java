package com.mrym.newsbulletion.mvp.activity.login;


import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.domain.modle.UserBean;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class LoginModel extends DefaultInterfaceBean {

    private int code;
    private UserBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }
}
