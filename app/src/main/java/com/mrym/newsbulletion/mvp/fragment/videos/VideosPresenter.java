package com.mrym.newsbulletion.mvp.fragment.videos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;
import com.mrym.newsbulletion.retrofit.GankApi;
import com.mrym.newsbulletion.retrofit.NewsApi;
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

//        List<VideoData> videoDatas = new ArrayList<VideoData>();
//        VideoData videoData = new VideoData();
//        videoData.setMp4_url("http://flv2.bn.netease.com/videolib3/1506/29/tUuas4099/SD/tUuas4099-mobile.mp4");
//        videoData.setMp4Hd_url("http://flv2.bn.netease.com/videolib3/1506/29/tUuas4099/HD/tUuas4099-mobile.mp4");
//        videoData.setTitle("传冬日娜成刘翔经纪人遭否认");
//        videoData.setTopicImg("http://img1.imgtn.bdimg.com/it/u=847347552,2484324697&fm=21&gp=0.jpg");
//        videoData.setCover("http://vimg1.ws.126.net/image/snapshot/2015/6/A/R/VASBATPAR.jpg");
//        videoData.setPlayCount(250);
//        videoData.setVideosource("优酷");
//        videoDatas.add(videoData);
//        VideoData videoData2 = new VideoData();
//        videoData2.setMp4_url("http://flv2.bn.netease.com/videolib3/1506/29/CGHqr2259/HD/CGHqr2259-mobile.mp4");
//        videoData2.setMp4Hd_url("http://flv2.bn.netease.com/videolib3/1506/29/CGHqr2259/HD/CGHqr2259-mobile.mp4");
//        videoData2.setTitle("邓超开飞机拒谈出轨事件 靠俞白眉“基情”解围");
//        videoData2.setPlayCount(100);
//        videoData2.setVideosource("风行");
//        videoData2.setTopicImg("http://img5.imgtn.bdimg.com/it/u=4187165161,104612686&fm=21&gp=0.jpg");
//        videoData2.setCover("http://vimg2.ws.126.net/image/snapshot/2015/6/I/1/VASBBI4I1.jpg");
//        videoDatas.add(videoData2);
//
//        VideoData videoData3 = new VideoData();
//        videoData3.setMp4_url("http://flv2.bn.netease.com/videolib3/1506/29/WobOf3217/SD/WobOf3217-mobile.mp4");
//        videoData3.setMp4Hd_url("http://flv2.bn.netease.com/videolib3/1506/29/WobOf3217/HD/WobOf3217-mobile.mp4");
//        videoData3.setTitle("郭晶晶为儿子找学校有意再追生");
//        videoData3.setPlayCount(150);
//        videoData3.setVideosource("YOUTUBE");
//        videoData3.setTopicImg("http://img2.imgtn.bdimg.com/it/u=1360025392,356559994&fm=21&gp=0.jpg");
//        videoData3.setCover("http://vimg2.ws.126.net/image/snapshot/2015/6/E/Q/VASBAT1EQ.jpg");
//        videoDatas.add(videoData3);
//                mRxManage.add(Api.getDefault(HostType.NETEASE_NEWS_VIDEO)
//                .getVideoList(gateGory,  pageIndex)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe( new SubscriberCallBack<>(new ApiCallback<Map<String, List<VideoData>>>() {
//                    @Override
//                    public void onSuccess(Map<String, List<VideoData>> model) {
//                        try {
//                            List<VideoData> videoDatas = model.get(gateGory);
//                            mvpView.loadComplete();
//                            mvpView.loadingSuccess(videoDatas);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
////                mvpView.loadingError(msg);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//                })));//nc/video/list/{type}/n/{startPage}-10.html
        RequestParams paramsData = new RequestParams(NewsApi.BASE_URL+"nc/video/list/"+gateGory+"/n/"+pageIndex+"-10.html");
        NetUtils.get(paramsData, new BaseCallBack<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                try {
                    Map<String, List<VideoData>>  model = new Gson().fromJson(result, new TypeToken<Map<String, List<VideoData>> >() {
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
