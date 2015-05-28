package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.gaverchou.observablescrollview.ObservableListView;
import com.gaverchou.observablescrollview.ObservableScrollViewCallbacks;
import com.gaverchou.observablescrollview.ScrollState;
import com.gaverchou.observablescrollview.ScrollUtils;
import com.gaverchou.suidaokou.adapter.CardViewAdapter;
import com.gaverchou.suidaokou.model.ActionItem;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

/**
 * Created by mrgaver on 15-5-29.
 */
public class HomeFragment extends BaseFragment implements ObservableScrollViewCallbacks {
    private View mImageView;
    private View mListBackgroundView;
    private ObservableListView mListView;
    private int mParallaxImageHeight;
    private MainActivity mMainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMainActivity = (MainActivity)getActivity();
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mImageView = view.findViewById(R.id.fragment_header);
        mListView = (ObservableListView) view.findViewById(R.id.main_list);
        mListView.setScrollViewCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
        View paddingView = new View(getActivity());
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                mParallaxImageHeight);
        paddingView.setLayoutParams(lp);

        // This is required to disable header's list selector effect
        paddingView.setClickable(true);

        mListView.addHeaderView(paddingView);
        CardViewAdapter adapter = new CardViewAdapter(getDummyData(12));
        mListView.setAdapter(adapter);
//        setDummyData(mListView);

        // mListBackgroundView makes ListView's background except header view.
        mListBackgroundView = view.findViewById(R.id.main_list_background);
    }

    public static ArrayList<ActionItem> getDummyData(int num) {
        ArrayList<ActionItem> items = new ArrayList<ActionItem>();
        for (int i = 1; i <= num; i++) {
            ActionItem actionItem = new ActionItem();
            actionItem.mActionName = "Happy new year";
            actionItem.mCollegaName = "Cisco";
            actionItem.mAddr = "Big house";
            items.add(actionItem);
        }
        return items;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.primary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mMainActivity.getmToolbarView().setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(mImageView, -scrollY * 2 / 3);
        // Translate list background
        ViewHelper.setTranslationY(mListBackgroundView, Math.max(0, -scrollY + mParallaxImageHeight));
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
