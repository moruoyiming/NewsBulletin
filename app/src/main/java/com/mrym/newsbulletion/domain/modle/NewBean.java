package com.mrym.newsbulletion.domain.modle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by Jian on 2016/9/13.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewBean extends DefaultInterfaceBean implements MultiItemEntity, Serializable {

    @Override
    public int getItemType() {
        return 0;
    }
}
