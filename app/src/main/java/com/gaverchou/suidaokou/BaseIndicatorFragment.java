package com.gaverchou.suidaokou;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gaverchou.suidaokou.widget.SimpleViewPagerIndicator;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class BaseIndicatorFragment extends BaseFragment<MainActivity> {
    protected String[] mTitles = null;
    protected SimpleViewPagerIndicator mIndicator;
    protected ViewPager mViewPager;
    protected FragmentPagerAdapter mAdapter;
    protected Fragment[] mFragments = null;

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    protected void initEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                if (mIndicator!=null){
                    mIndicator.scroll(position, positionOffset);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (mIndicator!=null) {
            for (int i = 0, count = mIndicator.textViews.size(); i < count; i++) {
                final int index = i;
                mIndicator.textViews.get(i).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mViewPager.setCurrentItem(index);
                            }
                        });
            }
        }
    }

    protected void onInit() {

    }

    protected void initDatas() {
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    protected void initViews(View view) {
        super.initView(view);
        mIndicator = (SimpleViewPagerIndicator) view
                .findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) view
                .findViewById(R.id.id_stickynavlayout_viewpager);
    }
}
