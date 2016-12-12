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

    public static final String CHANELCHANGERECEIVER = "android.chanelchange.receiver";
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
    /** newType=2 ：一图 */
    public final static int ITEM_ONE_PIC = 2;
    /** newType=3 ：二图 */
    public final static int ITEM_TWO_PIC = 3;
    /** newType=4 ：三图 */
    public final static int ITEM_THREE_PIC= 4;
    /** newType=5 ：视频 */
    public final static int ITEM_VIDEO= 5;
    /** newType=6 ：其他 */
    public final static int ITEM_OTHER= 6;
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
    // 电影
    public static final String MOVIE_ID = "T1348648650048";
    // 汽车
    public static final String CAR_ID = "T1348654060988";
    // 笑话
    public static final String FASHION_ID = "T1348650593803";
    // 精选
    public static final String CHOICE_ID = "T1370583240249";
    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 房产TYPE
    public static final String HOUSE_TYPE = "house";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";
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
    /**
     * 新闻id获取类型
     *
     * @param id 新闻id
     * @return 新闻类型
     */
    public static String getType(String id) {
        switch (id) {
            case HEADLINE_ID:
                return HEADLINE_TYPE;
            case HOUSE_ID:
                return HOUSE_TYPE;
            default:
                break;
        }
        return OTHER_TYPE;
    }
}
