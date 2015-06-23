package com.gaverchou.suidaokou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.ActInfoCommentItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class ActInfoCommentListAdapter extends BaseAdapter {

    private List<ActInfoCommentItem> mACItems;

    public ActInfoCommentListAdapter() {
        mACItems = new ArrayList<>();
    }

    public ActInfoCommentListAdapter(List<ActInfoCommentItem> mOrgItems) {
        this.mACItems = mOrgItems;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());
            holder = new ViewHolder();
            convertView = layoutInflator.inflate(
                    R.layout.actinfo_comment_item, null);
            holder.mIcon = (ImageView) convertView.findViewById(R.id.actinfo_item_touxiang_img);
            holder.mUserNameTv = (TextView) convertView.findViewById(R.id.actinfo_item_username_tv);
            holder.mCommentTv = (TextView) convertView.findViewById(R.id.actinfo_item_comment_tv);
            holder.mTimeTv = (TextView) convertView.findViewById(R.id.actinfo_item_comment_ttime_tv);
            holder.mZanCountTv = (TextView) convertView.findViewById(R.id.actinfo_item_z_tv);
            holder.mCommentCountTv = (TextView) convertView.findViewById(R.id.actinfo_item_comment_count_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (pos == 0) {
            holder.mCommentCountTv.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView mIcon;
        TextView mUserNameTv;
        TextView mCommentTv;
        TextView mTimeTv;
        TextView mZanCountTv;
        TextView mCommentCountTv;
    }
}
