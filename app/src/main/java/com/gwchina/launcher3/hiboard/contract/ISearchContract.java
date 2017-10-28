package com.gwchina.launcher3.hiboard.contract;

import com.gwchina.launcher3.model.IBaseContract;

/**
 * Created by tic on 17-10-19.
 */

public class ISearchContract {

    public interface ISearchView extends IBaseContract.BaseView<ISearchImpl> {
        void onResponseTextChanged();
    }

    public interface ISearchImpl extends IBaseContract.BaseImpl<ISearchView> {
        void beginSearch(String content);

        void onTextChanged(String content);
    }
}
