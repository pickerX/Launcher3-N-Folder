package com.gwchina.launcher3.hiboard;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gwchina.launcher3.Launcher;
import com.gwchina.launcher3.R;
import com.gwchina.launcher3.hiboard.contract.ISearchContract;
import com.gwchina.launcher3.hiboard.impl.SearchImpl;


/**
 * Created by tic on 17-9-28.
 */

public class SearchViews extends BaseBoardViews implements ISearchContract.ISearchView {

    private EditText mSearchView;
    private ISearchContract.ISearchImpl mSearch;
    private LinearLayout mSearchContent;
    private OnSearchFocusChangeListener onSearchFocusChangeListener;

    public SearchViews(Launcher launcher) {
        super(launcher);
    }


    public interface OnSearchFocusChangeListener {
        void onSearchFocusChange(boolean hasFocus);
    }

    protected void init() {
        mContentView = inflaterLayout();
        mSearchView = (EditText) mContentView.findViewById(R.id.et_search);
        mSearchContent = (LinearLayout) mContentView.findViewById(R.id.fl_result);

        setListeners();

        new SearchImpl(this);
    }

    private void setListeners() {
        mSearchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT);
                mSearchContent.setLayoutParams(params);
                mSearchContent.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
                onSearchFocusChangeListener.onSearchFocusChange(hasFocus);
            }
        });

        mSearchView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    mSearch.beginSearch(mSearchView.getEditableText().toString());
                    return true;
                }
                return false;
            }
        });

        mSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSearch.onTextChanged(s.toString());
            }
        });

        mSearchContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchContent.setVisibility(View.GONE);
                onSearchFocusChangeListener.onSearchFocusChange(false);
            }
        });

    }

    @Override
    protected View inflaterLayout() {
        return mLauncher.getInflater().inflate(R.layout.hi_board_search_bar, null);
    }

    @Override
    public void onResponseTextChanged() {

    }

    @Override
    public void setImpl(ISearchContract.ISearchImpl searchImpl) {
        this.mSearch = searchImpl;
    }

    @Override
    public Context getContext() {
        return mLauncher;
    }

    public void setOnSearchFocusChangeListener(OnSearchFocusChangeListener listener) {
        this.onSearchFocusChangeListener = listener;
    }

    public void onHide() {
        mSearchView.clearFocus();
        mSearchContent.setVisibility(View.GONE);
    }

    public boolean hasFocus() {
        return mSearchView.hasFocus();
    }
}
