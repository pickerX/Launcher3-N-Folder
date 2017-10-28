package com.gwchina.launcher3.hiboard;

import android.view.View;
import android.widget.LinearLayout;

import com.gwchina.launcher3.Launcher;
import com.gwchina.launcher3.R;

import static com.gwchina.launcher3.hiboard.HiBoardSettings.CARD_INDEX_HEALTHY;
import static com.gwchina.launcher3.hiboard.HiBoardSettings.CARD_INDEX_SEARCH_BAR;
import static com.gwchina.launcher3.hiboard.HiBoardSettings.CARD_INDEX_STUDY_RESOURCE;


/**
 * 负一屏
 * Created by tic on 17-9-25.
 */
public class HiBoardController implements SearchViews.OnSearchFocusChangeListener {
    private static final String TAG = HiBoardController.class.getSimpleName();

    private final Launcher mLauncher;
    private LinearLayout mCustomContainer;

    private SearchViews mSearchView;
    private HealthyViews mHealthyView;
    private StudyResourceViews mStudyResourceView;

    public HiBoardController(Launcher launcher) {
        mLauncher = launcher;
        mSearchView = new SearchViews(mLauncher);
        mHealthyView = new HealthyViews(mLauncher);
        mStudyResourceView = new StudyResourceViews(mLauncher);
    }

    public void appendToCustomContentPage(View customContent, int index) {
        if (mCustomContainer == null) {
            return;
        }

        mCustomContainer.addView(customContent, index);
    }

    public void populateBoard() {
        View root = mLauncher.getLayoutInflater().inflate(R.layout.hi_board_container, null);
        int paddingTop = root.getResources().getDimensionPixelSize(R.dimen.dynamic_grid_search_bar_height_tall);
        root.setPadding(0, paddingTop, 0, 0);
        mCustomContainer = (LinearLayout) root.findViewById(R.id.ll_container);

        mSearchView.init();
        mHealthyView.init();
        mStudyResourceView.init();
        mSearchView.setOnSearchFocusChangeListener(this);

        appendToCustomContentPage(mSearchView.getContentView(), CARD_INDEX_SEARCH_BAR);
        appendToCustomContentPage(mHealthyView.getContentView(), CARD_INDEX_HEALTHY);
        appendToCustomContentPage(mStudyResourceView.getContentView(), CARD_INDEX_STUDY_RESOURCE);

        mLauncher.addToCustomContentPage(root, mCustomCallback, TAG);
    }


    private Launcher.CustomContentCallbacks mCustomCallback = new Launcher.CustomContentCallbacks() {
        @Override
        public void onShow(boolean fromResume) {
            // update the use time of phone
            mHealthyView.onShow();
        }

        @Override
        public void onHide() {
            //Log.i(TAG, "onHide");
            mSearchView.onHide();
        }

        @Override
        public void onScrollProgressChanged(float progress) {
            //Log.i(TAG, "onScrollProgressChanged:" + progress);
        }

        @Override
        public boolean isScrollingAllowed() {
            return true;
        }
    };

    @Override
    public void onSearchFocusChange(boolean hasFocus) {
        mStudyResourceView.showOrNot(hasFocus);
        mHealthyView.showOrNot(hasFocus);
    }

    public boolean isInSearching() {
        return mSearchView.hasFocus();
    }

    public void exitSearch() {
        mSearchView.onHide();
        onSearchFocusChange(false);
    }
}
