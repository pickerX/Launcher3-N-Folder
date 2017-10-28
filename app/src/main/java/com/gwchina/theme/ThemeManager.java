package com.gwchina.theme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.gwchina.launcher3.IconCache;
import com.gwchina.launcher3.LauncherAppState;
import com.gwchina.launcher3.compat.UserHandleCompat;

import java.util.Map;
import java.util.Set;

/**
 * Created by tic on 17-10-25.
 */

public class ThemeManager {
    private IconCache mIconCache;

    public static final String ACTION_THEME_APPLIED = "com.gwchina.launcher3.action.THEME_APPLIED";


    public ThemeManager() {
        this.mIconCache = LauncherAppState.getInstance().getIconCache();
    }

    /**
     * applyDefaultTheme the default theme
     */
    public void applyDefaultTheme(Context context) {
        ThemePackageParser tParser = new ThemePackageParser();
        Map<String, Bitmap> icons = tParser.iconsFiles(null);
        Set<String> keys = icons.keySet();
//        for (String key : keys) {
//            Log.e("icon", "icon:" + key);
//        }


        boolean done = mIconCache.updateIconsToDB(icons, UserHandleCompat.myUserHandle());
        if (done) {
            Intent intent = new Intent(ACTION_THEME_APPLIED);
            context.sendBroadcast(intent);
        }
    }

}
