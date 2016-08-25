package com.mrym.newsbulletion.utils.common;

import android.content.Context;
import android.os.SystemClock;
import android.widget.Toast;

import com.zhangkong.fastpay.QuFanApplication;
import com.zhangkong.fastpay.util.ThreadManager;

/**
 * ToastUtils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-12-9
 */
public class ToastUtils {

    private ToastUtils() {
        throw new AssertionError();
    }
    private static Toast toast;

    private static Context getContext() {
        return AppUtils.getContext();
    }

    public static void show(int resId) {
        show(getContext().getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration) {
        show(getContext().getResources().getText(resId), duration);
    }

    public static void show(CharSequence text) {

        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(CharSequence text, int duration) {
        synchronized (getContext()) {
            if (toast != null && toast.getDuration() == duration) {
                toast.setText(text);
            } else {
                toast = Toast.makeText(getContext(), text, duration);
            }
            toast.show();
        }
        ThreadManager.getSinglePool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                synchronized (QuFanApplication.getContext()) {
                    toast = null;
                }
            }
        });
    }

    public static void show(int resId, Object... args) {
        show(String.format(getContext().getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration, Object... args) {
        show(String.format(getContext().getResources().getString(resId), args), duration);
    }

    public static void show(String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }
}
