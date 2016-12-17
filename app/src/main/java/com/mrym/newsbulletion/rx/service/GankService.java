package com.mrym.newsbulletion.rx.service;


import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.rx.retrofit.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public interface GankService {

    String BASE_URL = "http://www.gank.io/api/";

    //获取美女图片
    @GET("data/福利/20/{page}")
    Observable<HttpResult<List<PhotoGirl>>> getPhotoList(
            @Path("page") int page);

}
