package com.mrym.newsbulletion.utils.common;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.WindowManager;

import com.mrym.newsbulletion.NewsApplication;

import java.io.File;

/**
 * Created by Shawn on 2016/8/17.
 */
public class AppUtils {

    public static Context getContext() {
        return NewsApplication.getContext();
    }

    /**
     * 获取项目文件存储路径
     *
     * @return
     */
    public static String getBaseFilePath() {
        return NewsApplication.getContext().getFilesDir().getPath();
    }

    public static String getCacheDir() {
        if (existSDCard()) {
            File cacheDir = getContext().getExternalCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            return cacheDir.getAbsolutePath();
        } else {
            return getContext().getCacheDir().getAbsolutePath();
        }
    }

    /**
     * 判断SD卡是否存在
     *
     * @return
     */
    public static boolean existSDCard() {
        return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    //版本名
    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    //版本号
    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    private static PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        Context context = NewsApplication.getContext();
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
}
