package com.mrym.newsbulletion.ui.fragment;

import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.video.VideoPresenter;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class VideoFragment extends MvpFragment<VideoPresenter> implements VideoView{
    @Override
    protected VideoPresenter createPresenter() {
        return new VideoPresenter(this);
    }
}
