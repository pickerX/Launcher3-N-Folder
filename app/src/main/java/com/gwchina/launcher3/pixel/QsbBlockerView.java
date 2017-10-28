/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gwchina.launcher3.pixel;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RemoteViews;

import com.gwchina.launcher3.DeviceProfile;
import com.gwchina.launcher3.Launcher;
import com.gwchina.launcher3.R;
import com.gwchina.launcher3.Workspace;
import com.gwchina.launcher3.hiboard.HiBoardSettings;
import com.gwchina.launcher3.util.DateTimeUtil;


/**
 * A simple view used to show the region blocked by QSB during drag and drop.
 */
public class QsbBlockerView extends FrameLayout implements Workspace.OnStateChangeListener {
    //        , WeatherListener.OnGsaListener
    public static final Property<QsbBlockerView, Integer> QSB_BLOCKER_VIEW_ALPHA = new QsbBlockerViewAlpha(Integer.TYPE, "bgAlpha");
    private int mState = 0;
    private boolean LOGD = true;

    private final Paint mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private View mView;
    private CountDownView mCountDown;

    public QsbBlockerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setAlpha(0);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mView != null && mState == 2) {
            DeviceProfile deviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
            LayoutParams layoutParams = (LayoutParams) mView.getLayoutParams();
            int size = (int) (((MeasureSpec.getSize(widthMeasureSpec) / deviceProfile.inv.numColumns) - deviceProfile.iconSizePx) / 2);
            layoutParams.rightMargin = size;
            layoutParams.leftMargin = size;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        Workspace w = Launcher.getLauncher(getContext()).getWorkspace();
        w.setOnStateChangeListener(this);
        onGsa(null);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void prepareStateChange(Workspace.State state, AnimatorSet animatorSet) {
        setCountDownMills(retrieveTimeMillis());

        int i;
        if (state == Workspace.State.SPRING_LOADED) {
            i = 60;
        } else {
            i = 0;
        }
        if (animatorSet == null) {
            QSB_BLOCKER_VIEW_ALPHA.set(this, i);
            return;
        }
        animatorSet.play(ObjectAnimator.ofInt(this, QSB_BLOCKER_VIEW_ALPHA, i));
    }

    private long retrieveTimeMillis() {
        long time = 0;
        Cursor ct = null;
        try {
            ct = getContext().getContentResolver().query(HiBoardSettings.TableGreenBox.CONTENT_URI,
                    new String[]{HiBoardSettings.TableGreenBox.COLOMN_PERIOD_OF_NEXT_LOCK}, null, null, null);
            if (ct != null && ct.moveToNext()) {
                time = ct.getLong(ct.getColumnIndex(HiBoardSettings.TableGreenBox.COLOMN_PERIOD_OF_NEXT_LOCK));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ct != null) ct.close();
        }
        return time;
    }

    /***
     * @param millis
     */
    public void setCountDownMills(long millis) {
        if (LOGD) Log.e("QsbBlockerView", "setCountDownMills:" + millis);
        if (DateTimeUtil.isInPerHour(millis)) {
            setHasTimeStrategyView(true);
            mCountDown.setCountDownMills(millis);
        } else {
            setHasTimeStrategyView(false);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBgPaint);
    }

    public void onGsa(RemoteViews views) {
        long n = 200L;
        View oldView = mView;
        int oldState = mState;
        mView = ShadowHostView.getView(views, this, mView);
        mState = 2;
        if (mView == null) {
            mState = 1;
            View inflatedView;
            if (oldView != null && oldState == 1) {
                inflatedView = oldView;
            } else {
                inflatedView = LayoutInflater.from(getContext()).inflate(R.layout.widget_date, this, false);
            }
            mView = inflatedView;
        }
        if (oldState == mState) {
            if (oldView != mView) {
                if (oldView != null) {
                    removeView(oldView);
                }
                addView(mView);
            }
        } else {
            if (oldView != null) {
                oldView.animate().setDuration(n).alpha(0.0f).withEndAction(new QsbBlockerViewViewRemover(this, oldView));
            }
            addView(mView);
            mView.setAlpha(0.0f);
            mView.animate().setDuration(n).alpha(1.0f);
        }
        mCountDown = (CountDownView) mView.findViewById(R.id.date_text3);
    }

    public void setHasTimeStrategyView(boolean hasTimeStrategy) {
        mCountDown.setVisibility(hasTimeStrategy ? View.VISIBLE : View.INVISIBLE);
    }

    public void setPadding(final int n, final int n2, final int n3, final int n4) {
        super.setPadding(0, 0, 0, 0);
    }

    class QsbBlockerViewViewRemover implements Runnable {
        final QsbBlockerView mQsbBlockerView;
        final View mView;

        QsbBlockerViewViewRemover(QsbBlockerView qsbBlockerView, View view) {
            mQsbBlockerView = qsbBlockerView;
            mView = view;
        }

        @Override
        public void run() {
            mQsbBlockerView.removeView(mView);
        }
    }

    static class QsbBlockerViewAlpha extends Property<QsbBlockerView, Integer> {

        public QsbBlockerViewAlpha(Class<Integer> type, String name) {
            super(type, name);
        }

        @Override
        public void set(QsbBlockerView qsbBlockerView, Integer num) {
            qsbBlockerView.mBgPaint.setAlpha(num);
            qsbBlockerView.setWillNotDraw(num == 0);
            qsbBlockerView.invalidate();
        }

        @Override
        public Integer get(QsbBlockerView obj) {
            return obj.mBgPaint.getAlpha();
        }

    }
}
