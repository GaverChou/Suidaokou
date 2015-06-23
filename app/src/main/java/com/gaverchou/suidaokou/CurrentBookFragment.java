package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.gaverchou.suidaokou.adapter.ConsulationViewAdapter;
import com.gaverchou.suidaokou.adapter.CurrentBookAdapter;
import com.gaverchou.suidaokou.adapter.InformViewAdapter;
import com.gaverchou.suidaokou.model.ActionItem;
import com.gaverchou.suidaokou.model.CurrentBookItem;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-06-18.
 */
public class CurrentBookFragment extends BaseFragment<MainActivity> {
    public static final String TITLE = "title";

    public static CurrentBookFragment newInstance(String title) {
        CurrentBookFragment tabFragment = new CurrentBookFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @InjectView(R.id.gridView1)
    GridView gridView;
    CurrentBookAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState, R.layout.fragment_currentbook);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new CurrentBookAdapter(getDummyData(3));
        gridView.setAdapter(adapter);
    }

    public static ArrayList<CurrentBookItem> getDummyData(int num) {
        ArrayList<CurrentBookItem> items = new ArrayList<CurrentBookItem>();
        for (int i = 1; i <= num; i++) {
            CurrentBookItem currentBookItem = new CurrentBookItem();
            currentBookItem.mBookName = "数据库工程";
            currentBookItem.mHinte = "点击续借";
            currentBookItem.mBookTime = "还有9天";
            currentBookItem.mExpireTime = "应还时间:2015-5-12";
            items.add(currentBookItem);
        }
        return items;
    }
}
