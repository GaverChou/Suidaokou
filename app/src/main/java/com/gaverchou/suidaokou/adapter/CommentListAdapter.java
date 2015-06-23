package com.gaverchou.suidaokou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.ZanCommentItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class CommentListAdapter extends BaseAdapter {
    private ArrayList<ZanCommentItem> zanList;

    public CommentListAdapter() {
        zanList = new ArrayList<>();
    }

    public CommentListAdapter(ArrayList<ZanCommentItem> zanList) {
        this.zanList = zanList;
    }

    @Override
    public int getCount() {
        return zanList.size();
    }

    @Override
    public Object getItem(int i) {
        return zanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ZanCommentItem zanItem = zanList.get(pos);
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());
            if (pos != 1) {
                holder = new ViewHolder();
                convertView = layoutInflator.inflate(
                        R.layout.fragment_message_comment_item, null);
                holder.titleTv = (TextView) convertView.findViewById(R.id.message_comment_tit_tv);
                holder.timeTv = (TextView) convertView.findViewById(R.id.message_comment_time_tv);
                holder.subTitleTv = (TextView) convertView.findViewById(R.id.message_comment_subtit_tv);
            } else {
                holder = new ViewHolder();
                convertView = layoutInflator.inflate(
                        R.layout.fragment_message_zan_item, null);
                holder.titleTv = (TextView) convertView.findViewById(R.id.message_zan_tit_tv);
                holder.timeTv = (TextView) convertView.findViewById(R.id.message_zan_time_tv);
                holder.subTitleTv = (TextView) convertView.findViewById(R.id.message_zan_subtit_tv);
                holder.commentTv = (TextView) convertView.findViewById(R.id.message_zan_comment_tv);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(zanItem.mTitleStr);
        holder.timeTv.setText(zanItem.mTimeStr);
        holder.subTitleTv.setText(zanItem.mSubTitleStr);
        return convertView;
    }

    class ViewHolder {

        TextView titleTv;
        TextView timeTv;
        TextView subTitleTv;
        TextView commentTv;

        public ViewHolder() {
        }
    }
}
