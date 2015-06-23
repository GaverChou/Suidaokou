package com.gaverchou.suidaokou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.ActionItem;
import com.gaverchou.suidaokou.model.ConsulationItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by GaverChou on 2015-05-27.
 */
public class ConsulationViewAdapter extends SimpleHeaderRecyclerAdapter<ConsulationItem> {
    public ConsulationViewAdapter(Context context) {
        super(context, new ArrayList<ConsulationItem>());
    }

    public ConsulationViewAdapter(Context context, ArrayList<ConsulationItem> titleList) {
        super(context, titleList);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new HeaderViewHolder(mHeaderView);
        } else {
            return new CardViewHolder(mInflater.inflate(R.layout.consultation_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (mHeaderView != null) {
            position -= 1;
        }
        if (viewHolder instanceof CardViewHolder) {
            CardViewHolder holder = (CardViewHolder) viewHolder;
            ConsulationItem lConsulationItem = mItems.get(position);
            holder.nameTv.setText(lConsulationItem.consulationName);
            holder.addrTv.setText(lConsulationItem.consulationLoc);
            holder.consulationThumb.setBackgroundResource(lConsulationItem.consulationImgId);
        }
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.consulation_item_addr_tv)
        TextView addrTv;
        @InjectView(R.id.consulation_item_zan_count_tv)
        TextView zanCountTv;
        @InjectView(R.id.consulation_item_name_tv)
        TextView nameTv;
        @InjectView(R.id.consulation_item_time_tv)
        TextView timeTv;
        @InjectView(R.id.consulation_item_img)
        View consulationThumb;

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
