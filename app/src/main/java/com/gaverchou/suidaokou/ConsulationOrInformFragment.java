package com.gaverchou.suidaokou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaverchou.suidaokou.adapter.ConsulationViewAdapter;
import com.gaverchou.suidaokou.adapter.InformViewAdapter;
import com.gaverchou.suidaokou.model.ActionItem;
import com.gaverchou.suidaokou.model.ConsulationItem;
import com.gc.materialdesign.callbak.OnItemClickListener;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by mrgaver on 15-6-12.
 */
public class ConsulationOrInformFragment extends BaseFragment<MainActivity> {

    public static final String TITLE = "title";

    public static ConsulationOrInformFragment newInstance(String title) {
        ConsulationOrInformFragment tabFragment = new ConsulationOrInformFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @InjectView(R.id.main_list)
    RecyclerView mListView;
    ConsulationViewAdapter consulationViewAdapter;
    InformViewAdapter informViewAdapter;
    private String titleStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState, R.layout.fragment_consultation);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        titleStr = getArguments().getString(TITLE);
        mListView.setLayoutManager(new LinearLayoutManager(mActivity));
        if (getResources().getString(R.string.consulation_school).equals(titleStr)) {
            consulationViewAdapter = new ConsulationViewAdapter(mActivity, getDummyData());
            mListView.setAdapter(consulationViewAdapter);
            consulationViewAdapter.setmClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mActivity,ConsulationDetailActivity.class);
                    Bundle extra = new Bundle();
                    extra.putSerializable(ConsulationDetailActivity.CONSULATION_ITEM,
                            consulationViewAdapter.getItem(position));
                    intent.putExtras(extra);
                    startActivity(intent);
                }
            });
        } else {
            informViewAdapter = new InformViewAdapter(mActivity, getDummyData(6));
            mListView.setAdapter(informViewAdapter);
            informViewAdapter.setItemOnclickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mActivity,InformDetailActivity.class);
                    intent.putExtra(InformDetailActivity.POS, position);
                    startActivity(intent);
                }
            });
        }
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        toolbar.setBackgroundColor(mBaseColor);
        if (titleStr == null) {
            titleStr = getArguments().getString(TITLE);
        }
        toolbar.setTitle(titleStr);
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
