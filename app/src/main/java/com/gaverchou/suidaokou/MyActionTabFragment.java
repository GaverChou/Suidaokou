package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaverchou.suidaokou.adapter.CardViewAdapter;
import com.gaverchou.suidaokou.model.ActionItem;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class MyActionTabFragment extends BaseFragment<MainActivity> {
    public static final String TITLE = "title";
    @InjectView(R.id.fragment_tab_myaction_list)
    RecyclerView mListView;

    public static MyActionTabFragment newInstance(String title) {
        MyActionTabFragment tabFragment = new MyActionTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return createView(inflater,container,savedInstanceState,R.layout.fragment_tab_myaction);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mListView.setLayoutManager(new LinearLayoutManager(mActivity));
        mListView.setAdapter(new CardViewAdapter(mActivity,getDummyData(13),null));
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
}
