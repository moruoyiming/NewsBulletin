package com.mrym.newsbulletion.mvp.fragment.home;

import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.domain.modle.HomeOrderBean;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class HomeModel extends DefaultInterfaceBean {

    private HomeOrderBean order;

    public HomeOrderBean getOrder() {
        return order;
    }

    public void setOrder(HomeOrderBean order) {
        this.order = order;
    }

}
