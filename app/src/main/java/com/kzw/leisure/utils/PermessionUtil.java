package com.kzw.leisure.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * author: kang4
 * Date: 2019/12/10
 * Description:
 */
public class PermessionUtil {

    public static void checkPermession(Activity activity) {
        if (!checkStoragePermission(activity)) {
            requestStoragePermission(activity);
        }
    }

    private static boolean checkStoragePermission(Activity activity) {
        if (afterM()) {
            String[] permessions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            for (String permession : permessions) {
                int hasPermission = activity.checkSelfPermission(permession);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }

        }
        return true;
    }

    private static boolean afterM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    @SuppressLint("CheckResult")
    private static void requestStoragePermission(Activity activity) {
        RxPermissions rxPermission = new RxPermissions(activity);
        rxPermission.requestEach(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(permission -> {

                });
    }
}
