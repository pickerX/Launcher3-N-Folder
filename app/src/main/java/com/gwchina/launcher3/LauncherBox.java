package com.gwchina.launcher3;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.gwchina.launcher3.hiboard.HiBoardController;

/**
 * Created by tic on 17-10-16.
 */

public class LauncherBox extends Launcher {
    private static final String TAG = LauncherBox.class.getSimpleName();

    private HiBoardController mHiBoardCtr;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void populateCustomContentContainer() {
        mHiBoardCtr = new HiBoardController(this);
        mHiBoardCtr.populateBoard();
    }

    @Override
    protected boolean hasCustomContentToLeft() {
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (mHiBoardCtr != null &&
                mHiBoardCtr.isInSearching()) {
            mHiBoardCtr.exitSearch();
        }
    }
}
