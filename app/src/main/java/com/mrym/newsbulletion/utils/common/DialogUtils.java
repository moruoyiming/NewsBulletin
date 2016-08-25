package com.mrym.newsbulletion.utils.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * author : nan
 * time : 2016/8/23.
 */
public class DialogUtils {
    // 定义一个显示消息的对话框
    public static void showDialog(final Context ctx
            , String msg, String positiveStr, DialogInterface.OnClickListener clickListener, String negativeStr, DialogInterface.OnClickListener negativeClick) {
        // 创建一个AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                .setMessage(msg).setCancelable(false);
        builder.setPositiveButton(positiveStr, clickListener);
        builder.setNegativeButton(negativeStr, negativeClick);
        builder.create().show();
    }

    /**
     * 定义一个显示指定组件的对话框
     */
    private static AlertDialog alertDialog;

    /**
     * 显示对话框
     * @param ctx
     * @param view
     * @param str
     */
    public static AlertDialog showDialog(Context ctx, View view, String str) {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(ctx).setView(view).setCancelable(false).setTitle(str).create();
        }
        alertDialog.show();
        return null;
    }

    /**
     * 关闭对话框
     */
    public static void cancelDialog() {
        if (alertDialog != null)
            alertDialog.cancel();
    }
}
