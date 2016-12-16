package com.mrym.newsbulletion.retrofit;


import com.mrym.newsbulletion.domain.constans.UrlFactory;
import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.domain.modle.NewsDetail;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.activity.login.LoginModel;
import com.mrym.newsbulletion.rx.retrofit.HttpResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface NewsApi {

    String BASE_URL = "http://c.m.163.com/";
    //启动页广告
    @GET(UrlFactory.START_IMAGE)
    Observable<LoginModel> startImage();

    //登陆
    @GET(UrlFactory.LOGIN_PATH)
    Observable<LoginModel> login(
            @Query("password") String code,
            @Query("username") String username);

    //手机号登陆
    @GET(UrlFactory.LOGIN_PATH)
    Observable<LoginModel> phonelogin(
            @Query("telnumber") String telnumber,
            @Query("verification") String verification);

    //发送验证码
    @GET(UrlFactory.SEND_VERIFICATION)
    Observable<DefaultInterfaceBean> sendVerification(
            @Query("telnumber") String telnumber);

    //获取类型新闻
    @GET(UrlFactory.GET_GATEGORY_NEWS)
    Observable<HashMap<String, List<NewsSummary>>> getNewsList(
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);
    //获取视频新闻
    @GET(UrlFactory.GET_GATEGORY_VIDEOS)
    Observable<Map<String, List<VideoData>>> getVideoList(
            @Path("type") String type,
            @Path("startPage") int startPage);

    //获取新闻详情
    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, NewsDetail>> getNewDetail(
            @Path("postId") String postId);
}
