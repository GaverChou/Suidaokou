package com.gaverchou.suidaokou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.android.volley.VolleyError;
import com.gaverchou.observablescrollview.ObservableRecyclerView;
import com.gaverchou.observablescrollview.ObservableScrollViewCallbacks;
import com.gaverchou.observablescrollview.ScrollState;
import com.gaverchou.observablescrollview.ScrollUtils;
import com.gaverchou.suidaokou.adapter.CardViewAdapter;
import com.gaverchou.suidaokou.animator.SlideInOutLeftItemAnimator;
import com.gaverchou.suidaokou.api.ActionApi;
import com.gaverchou.suidaokou.database.ActionResultDao;
import com.gaverchou.suidaokou.model.ActionItem;
import com.gaverchou.suidaokou.model.ActionResult;
import com.gaverchou.suidaokou.model.ActionResultList;
import com.gaverchou.suidaokou.network.ResponseListener;
import com.gc.materialdesign.callbak.OnItemClickListener;
import com.nineoldandroids.view.ViewHelper;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by mrgaver on 15-5-29.
 */
public class AllActionFragment extends BaseFragment<MainActivity> implements ObservableScrollViewCallbacks, OnItemClickListener {
    private final static String TAG = "HomeFragment";
    public final static String ACTION_ITEM = "ActionItem";
    @InjectView(R.id.fragment_header)
    View mImageView;
    @InjectView(R.id.main_list_background)
    View mListBackgroundView;
    @InjectView(R.id.main_list)
    ObservableRecyclerView mListView;
    //    View headView;
    private int mParallaxImageHeight;
    CardViewAdapter adapter;
    protected ActionResultDao actionResultDao;

    private static AllActionFragment mInstance = null;

    public synchronized static AllActionFragment getInstance() {
        if (mInstance == null) {
            mInstance = new AllActionFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState, R.layout.fragment_allaction);
    }

    @Override
    protected void initData() {
        super.initData();
        actionResultDao = new ActionResultDao(mActivity);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
        mSwipeRefreshLayout.setColorSchemeResources(getPullToRefreshColorResources());

        mListView.setScrollViewCallbacks(this);
        mListView.setLayoutManager(new LinearLayoutManager(mActivity));
        View paddingView = new View(mActivity);
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                mParallaxImageHeight);
        paddingView.setLayoutParams(lp);

        // This is required to disable header's list selector effect
        paddingView.setClickable(true);

        adapter = new CardViewAdapter(mActivity, paddingView);
        mListView.setAdapter(adapter);
        mListView.setItemAnimator(new SlideInOutLeftItemAnimator(mListView));
        adapter.setmClickListener(this);
        List<ActionResult> actionResults = actionResultDao.query();
        for (ActionResult actionResult : actionResults) {
            adapter.add(actionResult.toActionItem());
        }
        ViewHelper.setTranslationY(mImageView, 0);
        // Translate list background
        ViewHelper.setTranslationY(mListBackgroundView, Math.max(0, mParallaxImageHeight));
    }

    @Override
    protected void initEvent() {
        if (pullToRefreshEnabled()) {
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    ActionApi.postObjectSearchApi(0, 10, new ResponseListener<ActionResultList>() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, error.toString());
                            setRefreshing(false);
                        }

                        @Override
                        public void onResponse(ActionResultList response) {
                            actionResultDao.deleteAll();
                            adapter.removeAll();
                            for (ActionResult result : response.getActivity()) {
                                ActionItem item = result.toActionItem();
                                adapter.add(item);
                            }
                            actionResultDao.addActionResult(response.getActivity());
                            setRefreshing(false);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        toolbar.setTitle(R.string.school_action);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = mBaseColor;
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
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

    @Override
    public void onItemClick(View view, int i) {
        int[] startingLocation = new int[2];
        view.getLocationOnScreen(startingLocation);
        Intent intent = new Intent(mActivity, ActionInfoActivity.class);
        startingLocation[0] += view.getWidth() / 2;
        intent.putExtra(ProfileActivity.ARG_REVEAL_START_LOCATION, startingLocation);
        Bundle extra = new Bundle();
        extra.putSerializable(ACTION_ITEM,
                adapter.getItem(i - 1));
        intent.putExtras(extra);
        startActivity(intent);
        mActivity.overridePendingTransition(0, 0);
    }
}
