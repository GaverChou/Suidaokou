package com.gaverchou.suidaokou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.network.LoadImageFromUrl;
import com.gaverchou.suidaokou.model.ActionItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by GaverChou on 2015-05-27.
 */
public class CardViewAdapter extends SimpleHeaderRecyclerAdapter<ActionItem> {
    private boolean isAlert;
    public CardViewAdapter(Context context,View headView) {
        super(context, new ArrayList<ActionItem>(), headView);
    }
    public CardViewAdapter(Context context, ArrayList<ActionItem> titleList, View headView) {
        super(context, titleList, headView);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setIsAlert(boolean isAlert) {
        this.isAlert = isAlert;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new HeaderViewHolder(mHeaderView);
        } else {
            return new CardViewHolder(mInflater.inflate(R.layout.cardview_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (mHeaderView != null) {
            position -= 1;
        }
        if (viewHolder instanceof CardViewHolder) {
            CardViewHolder holder = (CardViewHolder) viewHolder;
            ActionItem lActionItem = mItems.get(position);
            holder.actionNameTv.setText(lActionItem.mActionName);
            holder.collegeNameTv.setText(lActionItem.mCollegaName);
            holder.addrTv.setText(lActionItem.mAddr);
            holder.sendTimeTv.setText(lActionItem.mSendTime);
            holder.dayTv.setText(lActionItem.mDay);
            holder.mouthTv.setText(lActionItem.mMouth);
            holder.timeTv.setText(lActionItem.mTime);
            LoadImageFromUrl.initLoader(context);
            LoadImageFromUrl.loadIntoNetworkImageView(holder.actionThumb,lActionItem.mPicUrl,R.mipmap.poster);
        }
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.cardview_item_tit)
        TextView actionNameTv;
        @InjectView(R.id.cardview_item_colloge_tv)
        TextView collegeNameTv;
        @InjectView(R.id.cardview_item_addr_tv)
        TextView addrTv;
        @InjectView(R.id.cardview_item_sendtime_tv)
        TextView sendTimeTv;
        @InjectView(R.id.cardview_item_day_tv)
        TextView dayTv;
        @InjectView(R.id.cardview_item_mouth_tv)
        TextView mouthTv;
        @InjectView(R.id.cardview_item_time_tv)
        TextView timeTv;
        @InjectView(R.id.cardview_item_img)
        NetworkImageView actionThumb;

        public CardViewHolder(View convertView) {
            super(convertView);
            ButterKnife.inject(this, convertView);
            convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getPosition());
            }
        }
    }
}
