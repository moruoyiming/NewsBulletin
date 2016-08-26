package com.mrym.newsbulletion.mvp.fragment.home;

import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.domain.modle.HomeOrderBean;

/**
 * Created by Shawn on 2016/8/23.
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
