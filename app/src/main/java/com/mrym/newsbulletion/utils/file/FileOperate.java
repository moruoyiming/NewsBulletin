package com.mrym.newsbulletion.utils.file;

import android.util.Log;

import com.mrym.newsbulletion.NewsApplication;
import com.zhangkong.fastpay.QuFanApplication;
import com.zhangkong.fastpay.util.common.AppUtils;

import net.lingala.zip4j.exception.ZipException;

import java.io.IOException;
/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class FileOperate {

    private static final String TAG = "FileOperate";

    /**
     * 拷贝splash图片
     *
     * @return
     */
    public static boolean copyStartPage() {
        // 拷贝移动到指定文件夹下
        try {
            FileHelper.copyFileOrDir(NewsApplication.getContext(), "start_page.zip", AppUtils.getCacheDir());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "拷贝启动页包出错");
            return false;
        }
    }

    public static boolean unZipStartPage() {
        try {
            Zip4jUtil.unzip(AppUtils.getCacheDir() + "/start_page.zip", AppUtils.getBaseFilePath() + "/startPage", "123");
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            Log.e(TAG, "解压启动页包出错");
            return false;
        }
    }
}
