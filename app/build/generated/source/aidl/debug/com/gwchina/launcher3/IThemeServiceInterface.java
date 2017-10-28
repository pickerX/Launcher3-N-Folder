/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/huangpengfei/Documents/workspace/Launcher3-N-Folder/app/src/main/aidl/com/gwchina/launcher3/IThemeServiceInterface.aidl
 */
package com.gwchina.launcher3;
// Declare any non-default types here with import statements
/**
* Theme AIDL Service for Study Tool
*/
public interface IThemeServiceInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.gwchina.launcher3.IThemeServiceInterface
{
private static final java.lang.String DESCRIPTOR = "com.gwchina.launcher3.IThemeServiceInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.gwchina.launcher3.IThemeServiceInterface interface,
 * generating a proxy if needed.
 */
public static com.gwchina.launcher3.IThemeServiceInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.gwchina.launcher3.IThemeServiceInterface))) {
return ((com.gwchina.launcher3.IThemeServiceInterface)iin);
}
return new com.gwchina.launcher3.IThemeServiceInterface.Stub.Proxy(obj);
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
case TRANSACTION_apply:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.apply(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_reset:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.reset();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.gwchina.launcher3.IThemeServiceInterface
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
    * apply the new theme.
    * @param path theme pkg file path
    * return true when apply theme success.
    */
@Override public boolean apply(java.lang.String path) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(path);
mRemote.transact(Stub.TRANSACTION_apply, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
    *  apply the default theme of Launcher
    */
@Override public boolean reset() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_reset, _data, _reply, 0);
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
static final int TRANSACTION_apply = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_reset = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
/**
    * apply the new theme.
    * @param path theme pkg file path
    * return true when apply theme success.
    */
public boolean apply(java.lang.String path) throws android.os.RemoteException;
/**
    *  apply the default theme of Launcher
    */
public boolean reset() throws android.os.RemoteException;
}
