package com.gaverchou.suidaokou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gaverchou.suidaokou.adapter.CommentListAdapter;
import com.gaverchou.suidaokou.adapter.ZanCommentListAdapter;
import com.gaverchou.suidaokou.model.ZanCommentItem;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class ZanFragment extends BaseFragment {
    public static final String TITLE = "title";
    @InjectView(R.id.frag_message_listview)
    ListView mListView;
    private String titleStr;

    public static ZanFragment newInstance(String title) {
        ZanFragment tabFragment = new ZanFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return createView(inflater,container,savedInstanceState,R.layout.fragment_message);
    }


    @Override
    protected void initView(View view) {
        titleStr = getArguments().getString(TITLE);
        String[] titArr = getResources().getStringArray(R.array.msg_tit_list);
        if (titArr[0].equals(titleStr)) {
            mListView.setAdapter(new CommentListAdapter(getDummyData(3)));
        } else {
            mListView.setAdapter(new ZanCommentListAdapter(getDummyData(3)));
        }
    }

    public static ArrayList<ZanCommentItem> getDummyData(int num) {
        ArrayList<ZanCommentItem> items = new ArrayList<ZanCommentItem>();
        for (int i = 1; i <= num; i++) {
            ZanCommentItem zanItem = new ZanCommentItem();
            zanItem.mTitleStr = "Draw攒了你";
            zanItem.mTimeStr = "12/10 12:10";
            zanItem.mSubTitleStr = "你在元旦晚会的评论(已有5人赞)";
            zanItem.mCommentStr = "一起去打球啊";
            items.add(zanItem);
        }
        return items;
    }
}
