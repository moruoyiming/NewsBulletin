package com.mrym.newsbulletion.retrofit;


import com.mrym.newsbulletion.domain.constans.UrlFactory;
import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.mvp.activity.login.LoginModel;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryModel;
import com.mrym.newsbulletion.mvp.fragment.home.HomeModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface ApiStores {

    String BASE_PATH = "http://c.m.163.com/";
    //启动页广告
    @GET(UrlFactory.START_IMAGE)
    Observable<LoginModel> startImage();
    //登陆
    @GET(UrlFactory.LOGIN_PATH)
    Observable<LoginModel> login(@Query("password") String code, @Query("username") String username);

    //手机号登陆
    @GET(UrlFactory.LOGIN_PATH)
    Observable<LoginModel> phonelogin(@Query("telnumber") String telnumber, @Query("verification") String verification);

    //发送验证码
    @GET(UrlFactory.SEND_VERIFICATION)
    Observable<DefaultInterfaceBean> sendVerification(@Query("telnumber") String telnumber);

    //获取类型新闻
    @GET(UrlFactory.GET_GATEGORY_NEWS)
    Observable<GateGoryModel> getCategoryNews(@Query("gategory") String gategory);

    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<HashMap<String, List<NewsSummary>>> getNewsList(@Path("type") String type, @Path("id") String id,
                                                               @Path("startPage") int startPage);
    //获取类型新闻
    @GET(UrlFactory.GET_GATEGORY_VIDEOS)
    Observable<GateGoryModel> getCategoryVideos(@Query("gategory") String gategory);

}
