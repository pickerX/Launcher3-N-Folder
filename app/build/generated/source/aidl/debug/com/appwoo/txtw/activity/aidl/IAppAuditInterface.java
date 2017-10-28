/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/huangpengfei/Documents/workspace/Launcher3-N-Folder/app/src/main/aidl/com/appwoo/txtw/activity/aidl/IAppAuditInterface.aidl
 */
package com.appwoo.txtw.activity.aidl;
// Declare any non-default types here with import statements

public interface IAppAuditInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.appwoo.txtw.activity.aidl.IAppAuditInterface
{
private static final java.lang.String DESCRIPTOR = "com.appwoo.txtw.activity.aidl.IAppAuditInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.appwoo.txtw.activity.aidl.IAppAuditInterface interface,
 * generating a proxy if needed.
 */
public static com.appwoo.txtw.activity.aidl.IAppAuditInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.appwoo.txtw.activity.aidl.IAppAuditInterface))) {
return ((com.appwoo.txtw.activity.aidl.IAppAuditInterface)iin);
}
return new com.appwoo.txtw.activity.aidl.IAppAuditInterface.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_auditApp:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.auditApp(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.appwoo.txtw.activity.aidl.IAppAuditInterface
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     *///    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

@Override public boolean auditApp(java.lang.String packageName, java.lang.String topActivity) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(topActivity);
mRemote.transact(Stub.TRANSACTION_auditApp, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_auditApp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     *///    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

public boolean auditApp(java.lang.String packageName, java.lang.String topActivity) throws android.os.RemoteException;
}
