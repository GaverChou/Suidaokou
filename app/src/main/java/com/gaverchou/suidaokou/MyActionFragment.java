package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class MyActionFragment extends BaseIndicatorFragment {
    public static MyActionFragment mInstance = null;

    public synchronized static MyActionFragment instance() {
        if (mInstance == null) {
            mInstance = new MyActionFragment();
        }
        return mInstance;
    }

    private boolean isLogin = false;

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
        mTitles = getResources().getStringArray(R.array.myact_tit_list);
        mFragments = new Fragment[mTitles.length];
        mIndicator.setTitles(mTitles);
        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = MyActionTabFragment
                    .newInstance(mTitles[i]);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_profile, menu);
    }
}
