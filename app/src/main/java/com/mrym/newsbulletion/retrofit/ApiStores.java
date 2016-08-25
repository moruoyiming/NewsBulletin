package com.mrym.newsbulletion.retrofit;


import com.mrym.newsbulletion.domain.constans.UrlFactory;

/**
 * Created by WuXiaolong
 * on 2016/3/24.
 */
public interface ApiStores {
    //baseUrl
//    String API_SERVER_URL = "http://www.weather.com.cn/";
//    String BASE_PATH = "http://192.168.10.186:8181/";
//    String BASE_PATH = "http://192.168.10.186:8181/";
//    String BASE_PATH = "http://203.100.95.115:7000/";
    String BASE_PATH = "http://203.100.95.115:7000/";
//    String BASE_PATH = "http://203.100.95.115:7000/";
//    String BASE_PATH = "http://123.56.202.160:8080/";

    //加载天气
//    @GET("adat/sk/{cityId}.html")
//    Observable<MainModel> loadData(@Path("cityId") String cityId);

    @GET(UrlFactory.LOGIN_PATH)
    Observable<LoginModel> login(@Query("password") String code, @Query("username") String username);

    @GET(UrlFactory.RESET_PASSWORD)
    Observable<FirstResetModel> resetPassword(@Query("oldPwd") String oldPassword, @Query("password") String newPassword, @Query("shopId") String shopId);

    @GET(UrlFactory.GET_ORDER_PRICE_SUM)
    Observable<HomeModel> getOrderPriceSum(@Query("shopId") long oldPassword);

    @GET(UrlFactory.GET_ORDER_LIST)
    Observable<OrderModel> getOrderList(@Query("pageNow") int pageNow, @Query("pageSize") int pageSize, @Query("payType") String payType, @Query("shopId") String shopId);

}
