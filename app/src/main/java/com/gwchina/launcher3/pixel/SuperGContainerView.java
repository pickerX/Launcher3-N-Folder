package com.gwchina.launcher3.pixel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.gwchina.launcher3.DeviceProfile;
import com.gwchina.launcher3.R;
import com.gwchina.launcher3.Utilities;
import com.gwchina.launcher3.util.TransformingTouchDelegate;


public class SuperGContainerView extends Qsb {
    private static final Rect sTempRect = new Rect();
    private final TransformingTouchDelegate mTouchDelegate; //bY

    public SuperGContainerView(Context paramContext) {
        this(paramContext, null);
    }

    public SuperGContainerView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public SuperGContainerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);

        if (mLauncher.getDeviceProfile().isVerticalBarLayout()) {
            View.inflate(paramContext, R.layout.qsb_blocker_view, this);
            this.mTouchDelegate = null;
        } else {
            this.mTouchDelegate = new TransformingTouchDelegate(this);
        }
    }

    @Override
    public void applyOpaPreference() {
        super.applyOpaPreference();
        if (mTouchDelegate != null) {
            mTouchDelegate.setDelegateView(mQsbView);
        }
    }

    @Override
    protected int getQsbView(boolean withMic) {
        if (mTouchDelegate != null) {
            float f;
            f = getResources().getDimension(R.dimen.qsb_touch_extension);
            mTouchDelegate.extendTouchBounds(f);
        }

        return R.layout.qsb_without_mic;
    }

    @Override
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(0, 0, 0, 0);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initTouchDelegate();
    }

    private void initTouchDelegate() {
        if (mTouchDelegate != null) {
            View view = mLauncher.findViewById(R.id.workspace_blocked_row);
            if (view != null) {
                view.setTouchDelegate(mTouchDelegate);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int qsbOverlapMargin = -getResources().getDimensionPixelSize(R.dimen.qsb_overlap_margin); //n3
        DeviceProfile deviceProfile = mLauncher.getDeviceProfile();
        Rect workspacePadding = deviceProfile.getWorkspacePadding(Utilities.isRtl(getResources()));
        int size = MeasureSpec.getSize(widthMeasureSpec) - qsbOverlapMargin;

        int n6;
        int marginStart;

        if (mLauncher.useVerticalBarLayout()) {
            final int columnsWidth = /*qsbOverlapMargin +*/ getResources().getDimensionPixelSize(R.dimen.qsb_button_elevation);
            n6 = size;
            marginStart = columnsWidth;
        } else {
            final int n7 = size - workspacePadding.left - workspacePadding.right;
            n6 = (int) (DeviceProfile.calculateCellWidth(n7, (int) deviceProfile.inv.numColumns) * deviceProfile.inv.numColumns);
            marginStart = qsbOverlapMargin + (workspacePadding.left + (n7 - n6) / 2);
        }

        if (mQsbView != null) {
            LayoutParams layoutParams = (LayoutParams) mQsbView.getLayoutParams();
            layoutParams.width = (int) (n6 / deviceProfile.inv.numColumns);
            if (mLauncher.useVerticalBarLayout()) {
                layoutParams.width = Math.max(layoutParams.width, getResources().getDimensionPixelSize(R.dimen.qsb_min_width_with_mic));
            }
            layoutParams.setMarginStart(marginStart);
            layoutParams.resolveLayoutDirection(layoutParams.getLayoutDirection());
        }
        if (qsbConnector != null) {
            LayoutParams layoutParams = (LayoutParams) qsbConnector.getLayoutParams();
            layoutParams.width = marginStart + layoutParams.height / 2;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (mTouchDelegate != null) {
            int i5 = 0;
            final boolean isRtl = Utilities.isRtl(getResources());
            if (isRtl) {
                i5 = mQsbView.getLeft() - mLauncher.getDeviceProfile().getWorkspacePadding(isRtl).left;
            }
            mTouchDelegate.setBounds(i5, mQsbView.getTop(), mQsbView.getWidth() + i5, mQsbView.getBottom());
        }
    }

    @Override
    protected void setGoogleAnimationStart(Rect rect, Intent intent) {
        if (!mLauncher.useVerticalBarLayout()) {
            int height = mQsbView.getHeight() / 2;
            if (Utilities.isRtl(getResources())) {
                rect.right = height + getRight();
            } else {
                rect.left = -height;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return mTouchDelegate == null && super.dispatchTouchEvent(motionEvent);
    }
}