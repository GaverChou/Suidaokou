package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GaverChou on 2015-05-29.
 */

public class OrgFragment extends BaseIndicatorFragment {
    public static OrgFragment mInstance = null;

    public synchronized static OrgFragment instance() {
        if (mInstance == null) {
            mInstance = new OrgFragment();
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
        toolbar.setTitle(R.string.all_shetuan);
        mTitles = getResources().getStringArray(R.array.org_tit_list);
        mFragments = new Fragment[mTitles.length];
        mIndicator.setTitles(mTitles);
        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = OgnizationTAbFragment
                    .newInstance(mTitles[i]);
        }
    }
}
