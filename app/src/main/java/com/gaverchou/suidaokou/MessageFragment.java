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
public class MessageFragment extends BaseIndicatorFragment {
    public static MessageFragment mInstance = null;

    public synchronized static MessageFragment instance() {
        if (mInstance == null) {
            mInstance = new MessageFragment();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_profile, menu);
    }
    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mTitles = getResources().getStringArray(R.array.msg_tit_list);
        mFragments = new Fragment[mTitles.length];
        mIndicator.setTitles(mTitles);
        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = ZanFragment
                    .newInstance(mTitles[i]);
        }
    }
}
