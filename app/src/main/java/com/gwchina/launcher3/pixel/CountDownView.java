package com.gwchina.launcher3.pixel;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.gwchina.launcher3.DeviceProfile;
import com.gwchina.launcher3.Launcher;


/**
 * 简易倒计时View
 * Created by tic on 17-10-11.
 */

public class CountDownView extends AppCompatTextView {
    /**
     * 总时间
     */
    private long seconds;
    /**
     * 当前分钟
     */
    private long minutes;
    /**
     * 当前秒数
     */
    private int second = 60;

    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MILLS_PER_SECOND = 1000;
    private static final int MILLS_PER_MINUTE = SECONDS_PER_MINUTE * 1000;

    private static final int WHAT_DONE = 2;
    private static final int WHAT_TICK = 1;

    private int marginEnd;

    private StringBuilder content = new StringBuilder();

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DeviceProfile deviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
        int size = (int) (MeasureSpec.getSize(widthMeasureSpec) / deviceProfile.inv.numColumns);
        marginEnd = marginEnd == 0 ? (size - deviceProfile.iconSizePx) / 2 : marginEnd;

        setMarginEnd(marginEnd);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void setMarginEnd(int marginEnd) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.setMarginEnd(marginEnd);
        layoutParams.resolveLayoutDirection(layoutParams.getLayoutDirection());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (handler.hasMessages(WHAT_TICK)) {
            handler.removeMessages(WHAT_TICK);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_DONE:
                    setVisibility(View.GONE);
                    break;
                default:
                    setText(content.toString());
                    handler.post(runnable);
                    break;
            }
        }
    };

    /***
     * 设置倒计时
     * @param millis
     */
    public void setCountDownMills(long millis) {
        seconds = (long) Math.floor(millis / MILLS_PER_SECOND);
        minutes = (long) Math.floor(millis / MILLS_PER_MINUTE) - 1;
        // start after one second
        handler.postDelayed(runnable, MILLS_PER_SECOND);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (seconds <= 0) {
                handler.sendEmptyMessage(WHAT_DONE);
                return;
            }
            seconds--;
            if (second <= 0) {
                second = SECONDS_PER_MINUTE;
                minutes = (long) Math.floor(seconds / SECONDS_PER_MINUTE);
            }
            second--;
            content.delete(0, content.length());

            appendZeroWhenLower10(minutes);
            content.append(":");
            appendZeroWhenLower10(second);

            if (handler.hasMessages(WHAT_TICK)) {
                handler.removeMessages(WHAT_TICK);
            }
            handler.sendEmptyMessageDelayed(WHAT_TICK, MILLS_PER_SECOND);
        }
    };

    private StringBuilder appendZeroWhenLower10(long value) {
        if (value < 10) {
            content.append("0").append(value);
        } else {
            content.append(value);
        }
        return content;
    }
}
