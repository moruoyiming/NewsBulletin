package com.mrym.newsbulletion.mvp.fragment.follow;

import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.domain.modle.PhotoGirl;

import java.util.List;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface FollowView {
    public void loadingError(String errormsg);

    public void loadingSuccess(List<PhotoGirl> girls);

    public void loadComplete();
}
