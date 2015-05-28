package com.gaverchou.suidaokou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.ActionItem;

import java.util.ArrayList;


/**
 * Created by GaverChou on 2015-05-27.
 */
public class CardViewAdapter extends BaseAdapter {
    private ArrayList<ActionItem> titleList;

    public CardViewAdapter() {
        titleList = new ArrayList<>();
    }

    public CardViewAdapter(ArrayList<ActionItem> titleList) {
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public Object getItem(int i) {
        return titleList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ActionItem lActionItem = titleList.get(pos);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
            holder = new ViewHolder();
            holder.actionNameTv = (TextView) convertView
                    .findViewById(R.id.cardview_item_tit);
            holder.collegeNameTv = (TextView) convertView
                    .findViewById(R.id.cardview_item_colloge_tv);
            holder.addrTv = (TextView) convertView
                    .findViewById(R.id.cardview_item_addr_tv);
            holder.sendTimeTv = (TextView) convertView
                    .findViewById(R.id.cardview_item_sendtime_tv);
            holder.dayTv = (TextView) convertView
                    .findViewById(R.id.cardview_item_day_tv);
            holder.mouthTv = (TextView) convertView
                    .findViewById(R.id.cardview_item_mouth_tv);
            holder.timeTv = (TextView) convertView
                    .findViewById(R.id.cardview_item_time_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.actionNameTv.setText(lActionItem.mActionName);
        holder.collegeNameTv.setText(lActionItem.mCollegaName);
        holder.addrTv.setText(lActionItem.mAddr);
        holder.sendTimeTv.setText(lActionItem.mSendTime);
        holder.dayTv.setText(lActionItem.mDay);
        holder.mouthTv.setText(lActionItem.mMouth);
        holder.timeTv.setText(lActionItem.mTime);
        return convertView;
    }

    class ViewHolder {
        TextView actionNameTv;
        TextView collegeNameTv;
        TextView addrTv;
        TextView sendTimeTv;
        TextView dayTv;
        TextView mouthTv;
        TextView timeTv;
    }
}
