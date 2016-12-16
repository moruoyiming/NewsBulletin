package com.mrym.newsbulletion.retrofit;


import com.mrym.newsbulletion.domain.constans.UrlFactory;
import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.domain.modle.GirlData;
import com.mrym.newsbulletion.domain.modle.NewsDetail;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.activity.login.LoginModel;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryModel;
import com.mrym.newsbulletion.mvp.fragment.home.HomeModel;
import com.mrym.newsbulletion.rx.retrofit.HttpResult;

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
public interface GankApi {

    String BASE_URL = "http://www.gank.io/api/";

    //获取美女图片
    @GET("data/福利/20/{page}")
    Observable<HttpResult<List<PhotoGirl>>> getPhotoList(
            @Path("page") int page);

}
