package com.gwchina.launcher3.hiboard;

import android.view.View;

import com.gwchina.launcher3.Launcher;
import com.gwchina.launcher3.R;
import com.gwchina.launcher3.util.Activitys;


/**
 * Created by tic on 17-9-28.
 */

public class StudyResourceViews extends BaseBoardViews implements View.OnClickListener {
    private final String PKG = "com.txtw.learn.txtw_learn";
    /** 国学经典  */
    private final String ACTIVITY_GXJD = "com.txtw.learn.txtw_learn.module.funtolearn.chinaclassic.ChinaClassicActivity";
    /** 生活猎奇 */
    private final String ACTIVITY_SHLX = "com.txtw.learn.txtw_learn.module.funtolearn.lifenovelty.LifenoveltyHomeActivity";
    /** 同步教材 */
    private final String ACTIVITY_TBJC = "com.txtw.learn.txtw_learn.module.pocketcoach.syncbook.SyncBookActivity";
    /** 动漫教学 */
    private final String ACTIVITY_DMJX = "com.txtw.learn.txtw_learn.module.pocketcoach.anime.AnimeChineseActivity";
    /** 黄冈名师 */
    private final String ACTIVITY_HGMS = "com.txtw.learn.txtw_learn.module.pocketcoach.huanggang.HuanggangActivity";

    public StudyResourceViews(Launcher launcher) {
        super(launcher);
    }

    @Override
    protected void init() {
        mContentView = inflaterLayout();
        mContentView.findViewById(R.id.tv_learn_anim).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_learn_guoxue).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_learn_learn_book).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_learn_life).setOnClickListener(this);
        mContentView.findViewById(R.id.tv_learn_teacher).setOnClickListener(this);
    }

    @Override
    protected View inflaterLayout() {
        return mLauncher.getInflater().inflate(R.layout.hi_board_study_resource, null);
    }

    @Override
    public void onClick(View view) {
        String clazz = null;
        switch (view.getId()) {
            case R.id.tv_learn_anim:
                clazz = ACTIVITY_DMJX;
                break;
            case R.id.tv_learn_guoxue:
                clazz = ACTIVITY_GXJD;
                break;
            case R.id.tv_learn_learn_book:
                clazz = ACTIVITY_TBJC;
                break;
            case R.id.tv_learn_life:
                clazz = ACTIVITY_SHLX;
                break;
            case R.id.tv_learn_teacher:
                clazz = ACTIVITY_HGMS;
                break;
        }
        Activitys.start(mLauncher, PKG, clazz);
    }
}
