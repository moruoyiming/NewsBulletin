package com.mrym.newsbulletion.mvp.fragment.video;

import android.util.Log;

import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryModel;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class VideoPresenter extends BasePresenter<VideoView> {
    public VideoPresenter(VideoView videoview) {
        attachView(videoview);
    }

}
