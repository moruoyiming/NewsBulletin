package com.mrym.newsbulletion.mvp.fragment.videos;

import android.util.Log;

import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryModel;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jian on 2016/10/19.
 */

public class VideosPresenter extends BasePresenter<VideoView> {

    public VideosPresenter(VideoView videoView) {
        attachView(videoView);
    }

    public void getVideosData(String gateGory, int pageIndex) {
//        addSubscription(apiStores.getCategoryVideos(gateGory), new SubscriberCallBack<>(new ApiCallback<GateGoryVideoModel>() {
//            @Override
//            public void onSuccess(GateGoryVideoModel model) {
        List<VideoData> videoDatas = new ArrayList<VideoData>();
        VideoData videoData = new VideoData();
        videoData.setMp4_url("http://flv2.bn.netease.com/videolib3/1506/29/tUuas4099/SD/tUuas4099-mobile.mp4");
        videoData.setMp4Hd_url("http://flv2.bn.netease.com/videolib3/1506/29/tUuas4099/HD/tUuas4099-mobile.mp4");
        videoData.setTitle("传冬日娜成刘翔经纪人遭否认");
        videoData.setTopicImg("http://img1.imgtn.bdimg.com/it/u=847347552,2484324697&fm=21&gp=0.jpg");
        videoData.setCover("http://vimg1.ws.126.net/image/snapshot/2015/6/A/R/VASBATPAR.jpg");
        videoData.setPlayCount(250);
        videoData.setVideosource("优酷");
        videoDatas.add(videoData);
        VideoData videoData2 = new VideoData();
        videoData2.setMp4_url("http://flv2.bn.netease.com/videolib3/1506/29/CGHqr2259/HD/CGHqr2259-mobile.mp4");
        videoData2.setMp4Hd_url("http://flv2.bn.netease.com/videolib3/1506/29/CGHqr2259/HD/CGHqr2259-mobile.mp4");
        videoData2.setTitle("邓超开飞机拒谈出轨事件 靠俞白眉“基情”解围");
        videoData2.setPlayCount(100);
        videoData2.setVideosource("风行");
        videoData2.setTopicImg("http://img5.imgtn.bdimg.com/it/u=4187165161,104612686&fm=21&gp=0.jpg");
        videoData2.setCover("http://vimg2.ws.126.net/image/snapshot/2015/6/I/1/VASBBI4I1.jpg");
        videoDatas.add(videoData2);

        VideoData videoData3 = new VideoData();
        videoData3.setMp4_url("http://flv2.bn.netease.com/videolib3/1506/29/WobOf3217/SD/WobOf3217-mobile.mp4");
        videoData3.setMp4Hd_url("http://flv2.bn.netease.com/videolib3/1506/29/WobOf3217/HD/WobOf3217-mobile.mp4");
        videoData3.setTitle("郭晶晶为儿子找学校有意再追生");
        videoData3.setPlayCount(150);
        videoData3.setVideosource("YOUTUBE");
        videoData3.setTopicImg("http://img2.imgtn.bdimg.com/it/u=1360025392,356559994&fm=21&gp=0.jpg");
        videoData3.setCover("http://vimg2.ws.126.net/image/snapshot/2015/6/E/Q/VASBAT1EQ.jpg");
        videoDatas.add(videoData3);
        mvpView.loadComplete();
        mvpView.loadingSuccess(videoDatas);
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
////                mvpView.loadingError(msg);
//            }
//
//            @Override
//            public void onCompleted() {
//            }
//        }));
    }
}
