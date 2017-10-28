// IThemeServiceInterface.aidl
package com.gwchina.launcher3;

// Declare any non-default types here with import statements
/**
* Theme AIDL Service for Study Tool
*/
interface IThemeServiceInterface {
    /**
    * apply the new theme.
    * @param path theme pkg file path
    * return true when apply theme success.
    */
    boolean apply(String path);

    /**
    *  apply the default theme of Launcher
    */
    boolean reset();
}
