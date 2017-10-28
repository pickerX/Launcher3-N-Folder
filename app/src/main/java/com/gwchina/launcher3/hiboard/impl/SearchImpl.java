package com.gwchina.launcher3.hiboard.impl;

import android.text.TextUtils;

import com.gwchina.launcher3.hiboard.contract.ISearchContract;
import com.gwchina.launcher3.util.Activitys;
import com.gwchina.launcher3.util.StringFilter;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by tic on 17-10-19.
 */

public class SearchImpl implements ISearchContract.ISearchImpl {

    private ISearchContract.ISearchView views;

    public SearchImpl(ISearchContract.ISearchView views) {
        this.views = views;
        this.views.setImpl(this);
    }

    @Override
    public void beginSearch(String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        StringFilter filter = StringFilter.contains(new HashSet<>(Arrays.asList("http://", "https://", ".com", ".cn", ".org")));
        if (filter.matches(content)) {
            Activitys.startLwBrowser(views.getContext(), content);
        }
    }


    @Override
    public void onTextChanged(String content) {

        views.onResponseTextChanged();
    }
}
