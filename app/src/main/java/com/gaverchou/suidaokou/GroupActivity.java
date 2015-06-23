package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.gaverchou.observablescrollview.ObservableRecyclerView;
import com.gaverchou.observablescrollview.ObservableScrollViewCallbacks;
import com.gaverchou.observablescrollview.ScrollState;
import com.gaverchou.observablescrollview.ScrollUtils;
import com.gaverchou.suidaokou.adapter.ConsulationViewAdapter;
import com.gaverchou.suidaokou.adapter.GroupItemListAdapter;
import com.gaverchou.suidaokou.animator.SlideInOutLeftItemAnimator;
import com.gaverchou.suidaokou.model.ActionItem;
import com.gaverchou.suidaokou.model.ConsulationItem;
import com.gc.materialdesign.callbak.OnItemClickListener;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by mrgaver on 15-6-6.
 */
public class GroupActivity extends BaseActivity implements ObservableScrollViewCallbacks {
    @InjectView(R.id.fragment_group_list)
    ObservableRecyclerView mListView;
    View headView;
    @InjectView(R.id.group_headview)
    View testView;
    @InjectView(R.id.main_list_background)
    View mListViewBg;
    @InjectView(R.id.group_dongtai)
    TextView mTabDongtaiTv;
    @InjectView(R.id.group_action)
    TextView mTabActionTv;


    public static GroupActivity mInstance = null;

    public synchronized static GroupActivity instance() {
        if (mInstance == null) {
            mInstance = new GroupActivity();
        }
        return mInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        headView = inflater.inflate(R.layout.group_headerview, container, false);
//        return createView(inflater, container, savedInstanceState, R.layout.fragment_group);
        setContentView(R.layout.fragment_group);
    }

    private int mParallaxImageHeight;
    GroupItemListAdapter adapter;

    ConsulationViewAdapter consulationViewAdapter;
    int mParallaxImageMaxHeight;

    @Override
    protected void initView() {
        super.initView();
        mToolbar.setTitle(null);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.fragment_header__height);
        mParallaxImageMaxHeight = getResources().getDimensionPixelSize(R.dimen.fragment_header_exp_height);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setHasFixedSize(false);
        headView = new View(this);
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                mParallaxImageHeight);
        headView.setLayoutParams(lp);

        adapter = new GroupItemListAdapter(this, getDummyData(0), headView);
        mListView.setAdapter(adapter);
        mListView.setScrollViewCallbacks(this);
        mListView.setItemAnimator(new SlideInOutLeftItemAnimator(mListView));
        adapter.setmClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.remove(position);
            }
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        for (ActionItem item : getDummyData(3)) {
            adapter.add(item);
        }
        testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("headerView", "click");
                for (ActionItem item : getDummyData(1)) {
                    adapter.add(item);
                }
            }
        });
        mTabActionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulationViewAdapter = new ConsulationViewAdapter(GroupActivity.this, getDummyData());
                consulationViewAdapter.setmHeaderView(headView);
                mListView.setAdapter(consulationViewAdapter);
                mTabDongtaiTv.setTextColor(getResources().getColor(R.color.gray));
                mTabActionTv.setTextColor(getResources().getColor(R.color.black));
            }
        });
        mTabDongtaiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTabActionTv.setTextColor(getResources().getColor(R.color.gray));
                mTabDongtaiTv.setTextColor(getResources().getColor(R.color.black));
                adapter = new GroupItemListAdapter(GroupActivity.this, getDummyData(5), headView);
                mListView.setAdapter(adapter);
            }
        });
    }

    public static ArrayList<ConsulationItem> getDummyData() {
        ArrayList<ConsulationItem> items = new ArrayList<ConsulationItem>();
        ConsulationItem actionItem = new ConsulationItem();
        actionItem.consulationName = "书吧假日优惠";
        actionItem.consulationLoc = "云山书亭";
        actionItem.consulationImgId = R.mipmap.c1;
        items.add(actionItem);
        ConsulationItem actionItem2 = new ConsulationItem();
        actionItem2.consulationName = "Rouby 英剧";
        actionItem2.consulationLoc = "信息团学";
        actionItem2.consulationImgId = R.mipmap.c2;
        items.add(actionItem2);
        ConsulationItem actionItem3 = new ConsulationItem();
        actionItem3.consulationName = "足球广外杯";
        actionItem3.consulationLoc = "体育部";
        actionItem3.consulationImgId = R.mipmap.c3;
        items.add(actionItem3);
        return items;
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
        int baseColor = mBaseColor;
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(testView, -scrollY);
        ViewHelper.setTranslationY(mListViewBg, Math.max(0, -scrollY + mParallaxImageHeight));
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
