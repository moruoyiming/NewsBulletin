package com.mrym.newsbulletion.utils.permission;

import android.content.Context;
import android.os.Build;

import com.karumi.dexter.Dexter;


/**
 * M 权限帮助类
 * Created by w on 2016/5/24.
 */
public class PermissionHelper {
    public interface Callback {
        public void onPermissionsChecked();

        public void onPermissionRationaleShouldBeShown();
    }

    public static void checkPermission(Context context, Callback callback, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callback.onPermissionsChecked();
            return;
        }
        if (Dexter.isRequestOngoing()) return;
        MyDialogOnAnyDeniedMultiplePermissionsListener dialogMultiplePermissionsListener =
                MyDialogOnAnyDeniedMultiplePermissionsListener.Builder
                        .withContext(context)
                        .withTitle("权限提示")
                        .withMessage("您禁用了所需权限，功能将无法正常使用")
                        .withButtonText(android.R.string.ok)
                                //.withIcon(R.mipmap.my_icon)
                        .build();
        dialogMultiplePermissionsListener.setCallback(callback);
        Dexter.checkPermissions(dialogMultiplePermissionsListener, permissions);
    }

}