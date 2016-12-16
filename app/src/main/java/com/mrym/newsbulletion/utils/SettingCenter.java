package com.mrym.newsbulletion.utils;

import android.os.Handler;
import android.os.Looper;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.utils.common.PrefUtils;

import java.io.File;

/**
 * Created by Jian on 2016/9/1.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class SettingCenter {

    public static boolean getOnlyWifiLoadImage() {
        return PrefUtils.getBoolean(NewsApplication.getContext(), "getOnlyWifiLoadImage", false);
    }

    public static void setOnlyWifiLoadImage(boolean isEnable) {
        PrefUtils.putBoolean(NewsApplication.getContext(), "getOnlyWifiLoadImage", isEnable);
    }

    //region 缓存相关

    /**
     * 计算缓存大小
     *
     * @param listener
     */
    public static void countDirSizeTask(final CountDirSizeListener listener) {
        new Thread() {
            public void run() {
                final long result = getDirSize(NewsApplication.getContext().getCacheDir());
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(result);
                    }
                });
            }
        }.start();
    }

    /**
     * 清除缓存
     *
     * @param listener
     */
    public static void clearAppCache(final ClearCacheListener listener) {
        new Thread() {
            public void run() {
                clearFile(NewsApplication.getContext().getCacheDir());
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult();
                    }
                });
            }
        }.start();
    }

    private static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    private static void clearFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                clearFile(child);
            }
        } else {
            file.delete();
        }
    }

    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }

        if (fileSizeString.startsWith(".")) {
            return "0B";
        }
        return fileSizeString;
    }

    public interface ClearCacheListener {
        void onResult();
    }

    public interface CountDirSizeListener {
        void onResult(long result);
    }
    //endregion
}
