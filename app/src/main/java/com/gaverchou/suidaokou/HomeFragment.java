package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaverchou.observablescrollview.ScrollUtils;

/**
 * Created by mrgaver on 15-6-12.
 */
public class HomeFragment extends BaseIndicatorFragment {
    public static HomeFragment mInstance = null;

    public synchronized static HomeFragment instance() {
        if (mInstance == null) {
            mInstance = new HomeFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpage, container, false);
        initViews(view);
        initDatas();
        initEvents();
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.primary)));
        mFragments = new Fragment[3];
        mFragments[0] = AllActionFragment.getInstance();
        mFragments[1] = ConsulationOrInformFragment.newInstance(getResources().getString(R.string.consulation_school));
        mFragments[2] = ConsulationOrInformFragment.newInstance(getResources().getString(R.string.school_inform));
    }

    @Override
    protected void initEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            float lastPositionOffset = -1;
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                if (positionOffset == 0.0) {
                    return;
                }
                if ((positionOffset-lastPositionOffset)>0&&position==0) {
                    toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(positionOffset, mBaseColor));
                }else if ((positionOffset-lastPositionOffset)<0&&position==0){
                    toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(positionOffset, mBaseColor));
                } else {
                    toolbar.setBackgroundColor(mBaseColor);
                }
                lastPositionOffset = positionOffset;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
