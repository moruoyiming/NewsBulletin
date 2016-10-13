package com.mrym.newsbulletion.mvp.fragment.video;

import com.mrym.newsbulletion.domain.modle.NewsEntity;

import java.util.List;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface VideoView {
    public void loadingError(String errormsg);

    public void loadingSuccess(List<NewsEntity> news);

    public void loadComplete();
}
