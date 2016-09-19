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
}
