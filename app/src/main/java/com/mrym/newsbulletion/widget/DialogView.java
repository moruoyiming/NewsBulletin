package com.mrym.newsbulletion.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrym.newsbulletion.R;


/**
 * Created by Shawn on 2016/3/10.
 */
public class DialogView {
    private static final String TAG = DialogView.class.getCanonicalName();

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {
        return createLoadingDialog(context, msg, true);
    }

    public static Dialog createLoadingDialog(Context context, String msg, boolean isCancelOutSide) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.layout_loading_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView ivLoading = (ImageView) v.findViewById(R.id.iv_loading);
        TextView tvLoading = (TextView) v.findViewById(R.id.tv_loading);// 提示文字
        if (TextUtils.isEmpty(msg)) {
            tvLoading.setVisibility(View.GONE);
        } else {
            tvLoading.setText(msg);// 设置加载信息
        }
        // 加载动画
        final Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        // 使用ImageView显示动画
        ivLoading.startAnimation(hyperspaceJumpAnimation);
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCanceledOnTouchOutside(isCancelOutSide);
//        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                hyperspaceJumpAnimation.cancel();
            }
        });
        return loadingDialog;
    }
}
