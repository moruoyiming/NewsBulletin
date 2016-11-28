package com.mrym.newsbulletion.mvp.activity.push;

import com.mrym.newsbulletion.domain.modle.NewsSummary;

import java.util.List;

/**
 * Created by Jian on 2016/11/28.
 */

public interface PushView {

    public void loadingError(String errormsg);

    public void loadingSuccess(List<NewsSummary> news);

    public void loadComplete();
}
