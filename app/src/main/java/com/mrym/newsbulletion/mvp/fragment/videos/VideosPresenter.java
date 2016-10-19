package com.mrym.newsbulletion.mvp.fragment.videos;

import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;

/**
 * Created by Jian on 2016/10/19.
 */

public class VideosPresenter  extends BasePresenter<VideoView> {

    public VideosPresenter(VideoView videoView) {
        attachView(videoView);
    }
}
