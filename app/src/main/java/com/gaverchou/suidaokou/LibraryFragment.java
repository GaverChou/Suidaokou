package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GaverChou on 2015-06-18.
 */
public class LibraryFragment extends BaseIndicatorFragment {
    public static LibraryFragment mInstance = null;

    public synchronized static LibraryFragment instance() {
        if (mInstance == null) {
            mInstance = new LibraryFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inditor, container, false);
        initViews(view);
        initDatas();
        initEvents();
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        toolbar.setTitle(R.string.superlibray);
        mTitles = getResources().getStringArray(R.array.superlibray_tit_list);
        mFragments = new Fragment[mTitles.length];
        mIndicator.setTitles(mTitles);
        mFragments[0] = CurrentBookFragment
                .newInstance(mTitles[0]);
        mFragments[1] = HistoryBookFragment
                .newInstance(mTitles[1]);
    }
}
