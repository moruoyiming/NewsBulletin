package com.mrym.newsbulletion.domain.constans;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class UrlFactory {
    //启动页广告接口
    public final static String START_IMAGE="start-image/1080*1776";
    //登陆接口
    public final static String LOGIN_PATH = "zhangkongpay/loginControl/login.shtml";
    //发送短信接口
    public final static String SEND_VERIFICATION = "zhangkongpay/orderControl/getOrdersByPayType.shtml";
    //获取类型新闻接口
    public final static String GET_GATEGORY_NEWS = "nc/article/{type}/{id}/{startPage}-20.html";//"headline/T1348647909107/0-10.html"
    //获取类型视频接口
    public final static String GET_GATEGORY_VIDEOS = "nc/video/list/{type}/n/{startPage}-10.html";

}
