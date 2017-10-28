package com.gwchina.launcher3.model;

import android.content.Context;

/**
 * Created by tic on 17-10-19.
 */

public class IBaseContract {

    public interface BaseView<T> {
        void setImpl(T impl);

        Context getContext();
    }

    public interface BaseImpl<V> {
    }

}
