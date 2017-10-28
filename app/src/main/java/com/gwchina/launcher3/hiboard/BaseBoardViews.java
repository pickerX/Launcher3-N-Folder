package com.gwchina.launcher3.hiboard;

import android.animation.ObjectAnimator;
import android.view.View;

import com.gwchina.launcher3.Launcher;


/**
 * Created by tic on 17-9-28.
 */

public abstract class BaseBoardViews {

    protected Launcher mLauncher;
    protected View mContentView;

    public BaseBoardViews(Launcher launcher) {
        mLauncher = launcher;
    }

    protected abstract void init();

    protected abstract View inflaterLayout();

    protected View getContentView() {
        return mContentView;
    }

    protected void showOrNot(boolean show) {
        float from = show ? 1 : 0;
        float to = show ? 0 : 1;
        ObjectAnimator.ofFloat(mContentView, "alpha", from, to)
                .start();
    }

}
