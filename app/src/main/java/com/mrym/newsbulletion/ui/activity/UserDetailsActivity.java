package com.mrym.newsbulletion.ui.activity;

import com.mrym.newsbulletion.domain.modle.NewsDetail;
import com.mrym.newsbulletion.mvp.activity.details.DetailsPresenter;
import com.mrym.newsbulletion.mvp.activity.details.DetailsView;
import com.mrym.newsbulletion.ui.BaseActivity;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class UserDetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView {
    public static final String TAG = UserDetailsActivity.class.getCanonicalName();

    @Override
    protected DetailsPresenter createPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }


    @Override
    public void returnOneNewsData(NewsDetail newsDetail) {

    }

    @Override
    protected int setLayoutResourceID() {
        return 0;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void destroyActivityBefore() {

    }
}
