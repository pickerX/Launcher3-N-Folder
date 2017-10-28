package com.gwchina.launcher3.hiboard;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by tic on 17-9-28.
 */

public class HiBoardSettings {

    public static final int CARD_INDEX_SEARCH_BAR = 0;
    public static final int CARD_INDEX_HEALTHY = 1;
    public static final int CARD_INDEX_STUDY_RESOURCE = 2;

    public interface TableSteps extends BaseColumns {
        //Uri地址
        String AUTHORITIES = "com.gnw.pedometer.LwStepProvider";
        //表名　　
        String TABLE_NAME = "step";
        //访问该ContentProvider的URI
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/" + TABLE_NAME);
        String INDEX_DATA = "today";//计步日期 yyyy-MM-dd
        String INDEX_STEP = "step";//步数
    }

    public interface TableGreenBox {
        //Uri地址
        String AUTHORITIES = "com.txtw.provider.greenbox";
        //表名
        String TABLE_NAME = "info";
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/" + TABLE_NAME);
        String COLUMN_DEVICE_USE_TIME = "device_use_time";
        String COLOMN_PERIOD_OF_NEXT_LOCK = "period_of_next_lock";
    }

}
