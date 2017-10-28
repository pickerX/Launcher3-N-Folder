package com.gwchina.launcher3.sort;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import java.util.ArrayList;
import java.util.List;

import static com.gwchina.launcher3.LauncherSettings.AppType.*;

/**
 *
 */
public class AppTypeDao {

    public static AppTypeModel queryByPackageClassName(Context context, String packageName, String className) {
        String[] selectionArgs = {packageName, className, String.valueOf(ITEM_TYPE_APP)};
        List<AppTypeModel> models =
                query(context, null, PACKAGE_NAME + "=? AND " + CLASS_NAME + "=? AND " + ITEM_TYPE + "=? ", selectionArgs,
                        null);
        if (models != null && models.size() > 0) {
            return models.get(0);
        }
        return null;
    }

    public static List<AppTypeModel> queryByFolderType(Context context, String folderType, int itemType) {
        String[] selectionArgs = {folderType, String.valueOf(itemType)};
        List<AppTypeModel> data = query(context, null, APP_TYPE + "=? AND " + ITEM_TYPE + "=? ", selectionArgs, null);
        if (data == null) return new ArrayList<>();
        return data;
    }

    /**
     *
     * @param context
     * @param appType
     * @return
     */
    public static AppTypeModel queryByAppType(Context context, String appType) {
        String[] selectionArgs = {appType, String.valueOf(ITEM_TYPE_APP)};
        List<AppTypeModel> models = query(context, null, APP_TYPE + "=? AND " + ITEM_TYPE + "=? ", selectionArgs, null);
        if (models != null && models.size() > 0) {
            return models.get(0);
        }
        return null;
    }

    public static List<AppTypeModel> query(Context context, String[] projection, String selection,
                                           String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        try {
            final ContentResolver contentResolver = context.getContentResolver();
            final Uri uri = CONTENT_URI;
            cursor = contentResolver.query(uri, projection, selection.toString(), selectionArgs, sortOrder);
            List<AppTypeModel> list = new ArrayList<>();
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    AppTypeModel model = new AppTypeModel();
                    model.appType = cursor.getString(cursor.getColumnIndexOrThrow(APP_TYPE));
                    model.packageName = cursor.getString(cursor.getColumnIndexOrThrow(PACKAGE_NAME));
                    model.className = cursor.getString(cursor.getColumnIndexOrThrow(CLASS_NAME));
                    model.title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
                    model.itemType = cursor.getInt(cursor.getColumnIndexOrThrow(ITEM_TYPE));
                    list.add(model);
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    public static Uri save(Context context, AppTypeModel appTypeModel) {
        try {
            final ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            values.put(APP_TYPE, appTypeModel.appType);
            values.put(PACKAGE_NAME, appTypeModel.packageName);
            values.put(CLASS_NAME, appTypeModel.className);
            values.put(TITLE, appTypeModel.title);
            values.put(ITEM_TYPE, appTypeModel.itemType);
            return contentResolver.insert(CONTENT_URI, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
