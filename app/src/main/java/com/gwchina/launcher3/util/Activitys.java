package com.gwchina.launcher3.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by tic on 17-9-28.
 */

public class Activitys {

    public static void start(Context context, String packageName, String clazz) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, clazz));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startLwBrowser(Context context, String keywords) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.browser.txtw", "com.browser.txtw.activity.MainActivity"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(!TextUtils.isEmpty(keywords.trim()))
            intent.putExtra("com.browser.txtw.search.keyword", keywords);
        context.startActivity(intent);
    }
}
