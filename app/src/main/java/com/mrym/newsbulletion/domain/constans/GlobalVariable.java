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
    public static final long KEY_DOWN_TIME = 2000;
    public static final String CHANELCHANGERECEIVER = "android.chanelchange.receiver";
    public static int SIZE = 20;
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
    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 房产TYPE
    public static final String HOUSE_TYPE = "house";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";
}
