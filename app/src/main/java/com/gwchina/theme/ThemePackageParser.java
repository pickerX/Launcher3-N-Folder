package com.gwchina.theme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tic on 17-10-25.
 */

public class ThemePackageParser {

    private static String testDir = "/sdcard/Download/20161206102436.38829369";
    private static String DIR_ICONS = "icons";

    private final String TAG = ThemePackageParser.class.getSimpleName();

    /**
     * parse the icons zip
     *
     * @param path
     */
    public Map<String, Bitmap> iconsFiles(String path) {
        File icons = new File(testDir + File.separator + DIR_ICONS);
        Map<String, Bitmap> iconsMap = new HashMap<>();

        if (icons.exists()) {
            String[] iconPath = icons.list();
            if (iconPath == null) return new HashMap<>();

            for (String ic : iconPath) {
                if (isImage(ic)) {
                    Bitmap bitmap = BitmapFactory.decodeFile(icons + File.separator + ic);
                    iconsMap.put(packageName(ic), bitmap);
                }
            }
            return iconsMap;
        }

        return new HashMap<>();
    }

    private String packageName(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1) return fileName;
        return fileName.substring(0, index);
    }

    private boolean isImage(String fileName) {
        return fileName.endsWith("png");
    }

}
