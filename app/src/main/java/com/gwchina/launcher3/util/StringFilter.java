package com.gwchina.launcher3.util;

import android.text.TextUtils;

import java.util.Set;

/**
 * Abstract class to filter a set of strings.
 */
public abstract class StringFilter {

    private StringFilter() { }

    public abstract boolean matches(String str);

    public static StringFilter matchesAll() {
        return new StringFilter() {
            @Override
            public boolean matches(String str) {
                return true;
            }
        };
    }

    public static StringFilter of(final Set<String> validEntries) {
        return new StringFilter() {
            @Override
            public boolean matches(String str) {
                return validEntries.contains(str);
            }
        };
    }

    public static StringFilter contains(final Set<String> keywords) {
        return new StringFilter() {
            @Override
            public boolean matches(String str) {
                if (TextUtils.isEmpty(str)) return false;
                for (String key : keywords) {
                    if(str.contains(key)) return true;
                }
                return false;
            }
        };
    }

}
