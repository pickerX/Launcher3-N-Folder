package com.gwchina.theme;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.gwchina.launcher3.IThemeServiceInterface;

/**
 * Theme Service for Study Tool
 * apply new download Theme package or applyDefaultTheme the Launcher default theme
 * <p>
 * Created by tic on 17-10-25.
 */
public class ThemeService extends Service {
    private final String ACTION_THEME_SERVICE = "com.gwchina.launcher3.THEME_SERVICE";

    private ThemeManager mThemeMgr;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

    private IThemeServiceInterface.Stub mStub = new IThemeServiceInterface.Stub() {
        @Override
        public boolean apply(String path) throws RemoteException {
            return false;
        }

        @Override
        public boolean reset() throws RemoteException {
            mThemeMgr.applyDefaultTheme(ThemeService.this);
            return false;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mThemeMgr = new ThemeManager();
    }
}
