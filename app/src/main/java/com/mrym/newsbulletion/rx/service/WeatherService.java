package com.mrym.newsbulletion.rx.service;

import com.mrym.newsbulletion.domain.modle.WeatherBean;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jian on 2016/12/19.
 */

public interface WeatherService {

    String BASE_URL = "http://apicloud.mob.com/v1/weather/";

    //天气查询
    @GET("query")
    Observable<WeatherBean> getWeather(
            @Query("key") String appkey, @Query("city") String city, @Query("province") String province);
}
