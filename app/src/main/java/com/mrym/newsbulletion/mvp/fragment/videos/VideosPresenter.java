package com.mrym.newsbulletion.mvp.fragment.videos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;
import com.mrym.newsbulletion.rx.retrofit.service.NewsService;
import com.mrym.newsbulletion.utils.net.BaseCallBack;
import com.mrym.newsbulletion.utils.net.NetUtils;

import org.xutils.http.RequestParams;

import java.util.List;
import java.util.Map;


/**
 * Created by Jian on 2016/10/19.
 */

public class VideosPresenter extends BasePresenter<VideoView> {

    public VideosPresenter(VideoView videoView) {
        attachView(videoView);
    }

    public void getVideosData(final String gateGory, int pageIndex) {
        RequestParams paramsData = new RequestParams(NewsService.BASE_URL + "nc/video/list/" + gateGory + "/n/" + pageIndex + "-10.html");
        NetUtils.get(paramsData, new BaseCallBack<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                try {
                    Map<String, List<VideoData>> model = new Gson().fromJson(result, new TypeToken<Map<String, List<VideoData>>>() {
                    }.getType());
                    List<VideoData> videoDatas = model.get(gateGory);
                    mvpView.loadComplete();
                    mvpView.loadingSuccess(videoDatas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onFinished() {
            }


        });
    }
}
