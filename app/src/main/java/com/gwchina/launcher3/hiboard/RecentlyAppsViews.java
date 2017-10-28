package com.gwchina.launcher3.hiboard;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gwchina.launcher3.Launcher;
import com.gwchina.launcher3.R;

/**
 * Created by tic on 17-10-26.
 */

public class RecentlyAppsViews extends BaseBoardViews {

    private RecyclerView mRecyclerView;

    public RecentlyAppsViews(Launcher launcher) {
        super(launcher);
    }

    @Override
    protected void init() {
        View root = inflaterLayout();
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_apps_recently);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mLauncher, 4));

    }

    @Override
    protected View inflaterLayout() {
        return mLauncher.getInflater().inflate(R.layout.hi_board_apps_recently, null);
    }
}
