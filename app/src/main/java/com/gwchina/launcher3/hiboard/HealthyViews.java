package com.gwchina.launcher3.hiboard;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gwchina.launcher3.Launcher;
import com.gwchina.launcher3.R;
import com.gwchina.launcher3.util.DateTimeUtil;

import java.util.Date;

/**
 * 使用时长，计步器
 * Created by tic on 17-9-28.
 */

public class HealthyViews extends BaseBoardViews {

    private ContentObserver stepObserver = new StepsContentObserver();

    public HealthyViews(Launcher launcher) {
        super(launcher);
    }


    @Override
    protected void init() {
        mContentView = inflaterLayout();
        mContentView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                mLauncher.getContentResolver().registerContentObserver(HiBoardSettings.TableSteps.CONTENT_URI, true, stepObserver);
                querySteps();
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                mLauncher.getContentResolver().unregisterContentObserver(stepObserver);
            }
        });
        queryDeviceUseTime();
    }

    /***
     * 获取设备使用时长
     */
    private void queryDeviceUseTime() {
        Cursor ct = null;
        long time = 0;
        try {
            ct = mLauncher.getContentResolver().query(HiBoardSettings.TableGreenBox.CONTENT_URI, new String[]{HiBoardSettings.TableGreenBox.COLUMN_DEVICE_USE_TIME}, null, null, null);
            if (ct != null && ct.moveToNext()) {
                time = ct.getLong(ct.getColumnIndex(HiBoardSettings.TableGreenBox.COLUMN_DEVICE_USE_TIME));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ct != null) ct.close();
            setUseTime(DateTimeUtil.formatElapsedTime(mLauncher, time, false));
        }
    }

    /**
     * 获取步数
     */
    private void querySteps() {
        Cursor cs = null;
        String steps = null;
        try {
            cs = mLauncher.getContentResolver().query(HiBoardSettings.TableSteps.CONTENT_URI, null, HiBoardSettings.TableSteps.INDEX_STEP,
                    new String[]{DateTimeUtil.convertToSimpleDateString(new Date())}, null);
            if (cs != null && cs.moveToNext()) {
                steps = cs.getString(cs.getColumnIndex(HiBoardSettings.TableSteps.INDEX_STEP));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cs != null) cs.close();
            setSteps(steps);
        }
    }

    @Override
    protected View inflaterLayout() {
        return mLauncher.getInflater().inflate(R.layout.hi_board_healthy, null);
    }

    private void setUseTime(String content) {
        if (!TextUtils.isEmpty(content))
            ((TextView) mContentView.findViewById(R.id.tv_use_time)).setText(content);
    }

    private void setSteps(String content) {
        if (!TextUtils.isEmpty(content))
            ((TextView) mContentView.findViewById(R.id.tv_steps)).setText(content);
    }

    public void onShow() {
        queryDeviceUseTime();
    }

    class StepsContentObserver extends ContentObserver {

        public StepsContentObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            querySteps();
        }
    }
}
