package com.gwchina.launcher3.util;

import android.content.Context;

import com.gwchina.launcher3.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tic on 17-10-11.
 */

public class DateTimeUtil {
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int SECONDS_PER_HOUR = 60 * 60;
    public static final int SECONDS_PER_DAY = 24 * 60 * 60;

    private static final String FORMAT_SIMPLE = "yyyyMMdd";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_SIMPLE, Locale.CHINA);

    /***
     * yyyyMMdd
     * @param date
     * @return
     */
    public static String convertToSimpleDateString(Date date) {
        return simpleDateFormat.format(date);
    }


    /**
     * Returns elapsed time for the given millis, in the following format:
     * 2d 5h 40m 29s
     *
     * @param context     the application context
     * @param millis      the elapsed time in milli seconds
     * @param withSeconds include seconds?
     * @return the formatted elapsed time
     */
    public static String formatElapsedTime(Context context, double millis, boolean withSeconds) {
        StringBuilder sb = new StringBuilder();
        int seconds = (int) Math.floor(millis / 1000);
        if (!withSeconds) {
            // Round up.
            seconds += 30;
        }

        int days = 0, hours = 0, minutes = 0;
        if (seconds >= SECONDS_PER_DAY) {
            days = seconds / SECONDS_PER_DAY;
            seconds -= days * SECONDS_PER_DAY;
        }
        if (seconds >= SECONDS_PER_HOUR) {
            hours = seconds / SECONDS_PER_HOUR;
            seconds -= hours * SECONDS_PER_HOUR;
        }
        if (seconds >= SECONDS_PER_MINUTE) {
            minutes = seconds / SECONDS_PER_MINUTE;
            seconds -= minutes * SECONDS_PER_MINUTE;
        }
        if (withSeconds) {
            if (days > 0) {
                sb.append(context.getString(R.string.str_time_days,
                        days, hours, minutes, seconds));
            } else if (hours > 0) {
                sb.append(context.getString(R.string.str_time_hours,
                        hours, minutes, seconds));
            } else if (minutes > 0) {
                sb.append(context.getString(R.string.str_time_minutes, minutes, seconds));
            } else {
                sb.append(context.getString(R.string.str_time_seconds, seconds));
            }
        } else {
            if (days > 0) {
                sb.append(context.getString(R.string.str_time_days_no_seconds,
                        days, hours, minutes));
            } else if (hours > 0) {
                sb.append(context.getString(R.string.str_time_hours_no_seconds,
                        hours, minutes));
            } else {
                sb.append(context.getString(R.string.str_time_minutes_no_seconds, minutes));
            }
        }
        return sb.toString();
    }

    /**
     * 是否在1小时之内
     * @param millis
     * @return
     */
    public static boolean isInPerHour(long millis) {
        if (millis <= 0) return false;

        int seconds = (int) Math.floor(millis / 1000);
        return seconds <= SECONDS_PER_HOUR;
    }
}
