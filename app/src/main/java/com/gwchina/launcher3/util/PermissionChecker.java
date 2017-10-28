package com.gwchina.launcher3.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by tic on 17-9-28.
 */
public class PermissionChecker {

    public static final int PERMISSIONS_REQUEST = 1;


    /**
     * 是否有存储权限
     * @param activity
     * @return
     */
    public static boolean hasReadExternalStorage(Activity activity) {
        return isPermissionGranted(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private static boolean isPermissionGranted(Activity activity, String permission) {
        int hasPermission = ContextCompat.checkSelfPermission(activity, permission);
        if (hasPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        ActivityCompat.requestPermissions(activity, new String[]{permission}, PERMISSIONS_REQUEST);
        return false;
    }

}
