package com.mrym.newsbulletion.domain.constans;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.utils.common.PrefUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GlobalVariable {
    public static final String SUCCESS = "success";
    public static final String LAST_LOGIN_TIME = "LAST_LOGIN_TIME";
    public static final String FIRST_LOGIN_STATE = "FIRST_LOGIN_STATE";
    public static final int SUCCESS_CODE = 200;
    public static final String EXTRA_DEVICE_ADDRESS = "device_address";
    public static final String PHOTO_DETAIL = "photo_detail";
    public static final String NEWS_IMG_RES = "news_img_res";
    public static final String TRANSITION_ANIMATION_NEWS_PHOTOS = "transition_animation_news_photos";
    public static String NEWS_POST_ID = "NEWS_POST_ID";//新闻详情id
    public static String NEWS_LINK = "NEWS_LINK";
    public static String NEWS_TITLE = "NEWS_TITLE";
    public static final String NEWS_ID = "news_id";
    public static final String NEWS_TYPE = "news_type";
    /* 视频*/
    public static final String VIDEO_TYPE = "VIDEO_TYPE";
    public static final String CHANNEL_POSITION = "channel_position";
    public static final int CONNECT_TIMEOUT = 5000;
    @SuppressLint("NewApi")
    public static String getLogSavePath() {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return NewsApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath() + File.separator;
            } catch (NullPointerException e) {
                return Environment.getExternalStorageDirectory() + File.separator + "Documents" + File.separator;
            }
        }
        return Environment.getExternalStorageDirectory() + File.separator + "Documents" + File.separator;
    }

    /** newType=0 ：文字 */
    public final static int ITEM_TEXT = 0;
    /** newType=1 ：大图 */
    public final static int ITEM_BIGPIC= 1;
    /** newType=2 ：右侧小图 */
    public final static int ITEM_SMALLPIC = 2;
    /** newType=3 ：三图 */
    public final static int ITEM_EXCLUSIVE = 3;
    /** newType=4 ：视频 */
    public final static int ITEM_VIDEO= 4;
    /** XRecycleViewState */
    public static final int ACTION_REFRESH = 1;
    public static final int ACTION_LOAD_MORE = 2;
    // 推荐
    public static final String HEADLINE_ID = "T1348647909107";
    // 房产id
    public static final String HOUSE_ID = "5YyX5Lqs";
    // 足球
    public static final String FOOTBALL_ID = "T1399700447917";
    // 娱乐
    public static final String ENTERTAINMENT_ID = "T1348648517839";
    // 体育
    public static final String SPORTS_ID = "T1348649079062";
    // 财经
    public static final String FINANCE_ID = "T1348648756099";
    // 科技
    public static final String TECH_ID = "T1348649580692";
    // 电影
    public static final String MOVIE_ID = "T1348648650048";
    // 汽车
    public static final String CAR_ID = "T1348654060988";
    // 笑话
    public static final String JOKE_ID = "T1350383429665";
    // 游戏
    public static final String GAME_ID = "T1348654151579";
    // 时尚
    public static final String FASHION_ID = "T1348650593803";
    // 情感
    public static final String EMOTION_ID = "T1348650839000";
    // 精选
    public static final String CHOICE_ID = "T1370583240249";
    // 电台
    public static final String RADIO_ID = "T1379038288239";
    // nba
    public static final String NBA_ID = "T1348649145984";
    // 数码
    public static final String DIGITAL_ID = "T1348649776727";
    // 移动
    public static final String MOBILE_ID = "T1351233117091";
    // 彩票
    public static final String LOTTERY_ID = "T1356600029035";
    // 教育
    public static final String EDUCATION_ID = "T1348654225495";
    // 论坛
    public static final String FORUM_ID = "T1349837670307";
    // 旅游
    public static final String TOUR_ID = "T1348654204705";
    // 手机
    public static final String PHONE_ID = "T1348649654285";
    // 博客
    public static final String BLOG_ID = "T1349837698345";
    // 社会
    public static final String SOCIETY_ID = "T1348648037603";
    // 家居
    public static final String FURNISHING_ID = "T1348654105308";
    // 暴雪游戏
    public static final String BLIZZARD_ID = "T1397016069906";
    // 亲子
    public static final String PATERNITY_ID = "T1397116135282";
    // CBA
    public static final String CBA_ID = "T1348649475931";
    // 消息
    public static final String MSG_ID = "T1371543208049";
    // 军事
    public static final String MILITARY_ID = "T1348648141035";
    public static final String NETEAST_HOST = "http://c.m.163.com/";
    /**
     * 新浪图片新闻
     * http://gank.io/api/data/福利/{size}/{page}
     */
    public static final String SINA_PHOTO_HOST = "http://gank.io/api/";
    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.NETEASE_NEWS_VIDEO:
                host = NETEAST_HOST;
                break;
            case HostType.GANK_GIRL_PHOTO:
                host = SINA_PHOTO_HOST;
                break;
            case HostType.NEWS_DETAIL_HTML_PHOTO:
                host = "http://kaku.com/";
                break;
            default:
                host = "";
                break;
        }
        return host;
    }
}
