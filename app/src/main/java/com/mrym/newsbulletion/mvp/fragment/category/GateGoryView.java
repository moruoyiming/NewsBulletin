package com.mrym.newsbulletion.mvp.fragment.category;

import com.mrym.newsbulletion.domain.modle.NewsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface GateGoryView {

    public void loadingError(String errormsg);

    public void loadingSuccess(List<NewsEntity> news);

    public void loadComplete();

}
