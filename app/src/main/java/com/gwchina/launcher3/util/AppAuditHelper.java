package com.gwchina.launcher3.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.appwoo.txtw.activity.aidl.IAppAuditInterface;

/**
 * Application audit helper
 * Created by tic on 17-10-24.
 */
public class AppAuditHelper {

    private static final String TAG = AppAuditHelper.class.getSimpleName();

    private static IAppAuditInterface mService;
    private static final String ACTION_APP_AUDIT = "com.appwoo.txtw.activity.AppAuditService";

    private static ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            mService = IAppAuditInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            Log.e(TAG, "onServiceDisconnected");
        }
    };

    public static boolean audit(Context context, String packageName, String className) {
        if (mService == null) {
            startBind(context);
            return true;
        }
        try {
            return mService.auditApp(packageName, className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void startBind(Context context) {
        if (context == null) return;

        Intent service = new Intent();
        service.setAction(ACTION_APP_AUDIT);
        service.setPackage("com.appwoo.txtw.activity");
        context.bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public static void unBind(Context context) {
        if (context == null) return;
        if (mService != null) context.unbindService(serviceConnection);
    }
}
