// IAppAuditInterface.aidl
package com.appwoo.txtw.activity.aidl;

// Declare any non-default types here with import statements

interface IAppAuditInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    boolean auditApp(String packageName, String topActivity);
}
