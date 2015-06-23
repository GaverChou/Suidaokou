package com.gaverchou.suidaokou;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gaverchou.observablescrollview.ScrollUtils;
import com.gaverchou.suidaokou.adapter.OrgListAdapter;
import com.gaverchou.suidaokou.model.OrgItem;
import com.nhaarman.listviewanimations.appearance.ViewAnimator;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class OgnizationTAbFragment extends BaseFragment<MainActivity> implements AdapterView.OnItemClickListener{

    public static final String TITLE = "title";
    @InjectView(R.id.frag_org_listview)
    ListView mListView;

    public static OgnizationTAbFragment newInstance(String title) {
        OgnizationTAbFragment tabFragment = new OgnizationTAbFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState, R.layout.fragment_org);
    }
    private SwingBottomInAnimationAdapter mListViewAnimationAdapter;
    private ViewAnimator mListViewAnimator;
    @Override
    protected void initView(View view) {
        super.initView(view);
//        mListView = (ListView)view.findViewById(R.id.frag_org_listview);
        mListViewAnimationAdapter = new SwingBottomInAnimationAdapter(new OrgListAdapter(getDummyData(3)));
        mListViewAnimationAdapter.setAbsListView(mListView);
        mListViewAnimator = mListViewAnimationAdapter.getViewAnimator();
        if (mListViewAnimator != null) {
            mListViewAnimator.setAnimationDurationMillis(500);
            mListViewAnimator.disableAnimations();
        }
        mListView.setAdapter(mListViewAnimationAdapter);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mListViewAnimator.reset();
//                mListViewAnimationAdapter.notifyDataSetChanged();
//            }
//        },3000);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mListView.setOnItemClickListener(this);
    }

    public static ArrayList<OrgItem> getDummyData(int num) {
        ArrayList<OrgItem> items = new ArrayList<OrgItem>();
        for (int i = 1; i <= num; i++) {
            OrgItem orgItem = new OrgItem();
            orgItem.mOrgName = "Quanta信息服务部";
            orgItem.mDescrip = "Nothing but powerful";
            items.add(orgItem);
        }
        return items;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        Intent intent = new Intent(mActivity,GroupActivity.class);
        startActivity(intent);
    }
}
