package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.gaverchou.suidaokou.adapter.CurrentBookAdapter;
import com.gaverchou.suidaokou.adapter.HistoryBookListAdapter;
import com.gaverchou.suidaokou.model.CurrentBookItem;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-06-18.
 */
public class HistoryBookFragment extends BaseFragment<MainActivity> {
    public static final String TITLE = "title";

    public static HistoryBookFragment newInstance(String title) {
        HistoryBookFragment tabFragment = new HistoryBookFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @InjectView(R.id.frag_org_listview)
    ListView listView;
    HistoryBookListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState, R.layout.fragment_org);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new HistoryBookListAdapter(getDummyData(3));
        listView.setAdapter(adapter);
    }

    public static ArrayList<CurrentBookItem> getDummyData(int num) {
        ArrayList<CurrentBookItem> items = new ArrayList<CurrentBookItem>();
        for (int i = 1; i <= num; i++) {
            CurrentBookItem currentBookItem = new CurrentBookItem();
            currentBookItem.mBookName = "数据库工程";
            currentBookItem.mAuthorName = "无名";
            items.add(currentBookItem);
        }
        return items;
    }
}
