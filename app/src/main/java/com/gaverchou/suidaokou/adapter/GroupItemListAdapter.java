package com.gaverchou.suidaokou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.ActionItem;

import java.util.ArrayList;

/**
 * Created by mrgaver on 15-6-6.
 */
public class GroupItemListAdapter extends SimpleHeaderRecyclerAdapter<ActionItem> {

    public GroupItemListAdapter(Context context, ArrayList<ActionItem> items, View headerView) {
        super(context, items, headerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new HeaderViewHolder(mHeaderView);
        } else {
            return new ItemViewHolder(mInflater.inflate(R.layout.cardview_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof HeaderViewHolder){
            bindHeadView(viewHolder);
        }else if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder tmp = (ItemViewHolder) viewHolder;
            tmp.actionNameTv.setText(mItems.get(position - 1).mActionName);
            tmp.collegeNameTv.setText(mItems.get(position - 1).mCollegaName);
            tmp.addrTv.setText(mItems.get(position - 1).mAddr);
            tmp.sendTimeTv.setText(mItems.get(position - 1).mSendTime);
            tmp.dayTv.setText(mItems.get(position - 1).mDay);
            tmp.mouthTv.setText(mItems.get(position - 1).mMouth);
            tmp.timeTv.setText(mItems.get(position - 1).mTime);
        }
    }

    protected void bindHeadView(final  RecyclerView.ViewHolder viewHolder){

//        viewHolder.itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                viewHolder.itemView.getViewTreeObserver().removeOnPreDrawListener(this);
//                animateUserProfileHeader(viewHolder);
//                return false;
//            }
//        });
    }
    private void animateUserProfileHeader(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setTranslationY(-viewHolder.itemView.getHeight());
        viewHolder.itemView.animate().translationY(0).setDuration(300).setInterpolator(new DecelerateInterpolator());
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView actionNameTv;
        TextView collegeNameTv;
        TextView addrTv;
        TextView sendTimeTv;
        TextView dayTv;
        TextView mouthTv;
        TextView timeTv;

        public ItemViewHolder(View view) {
            super(view);
            actionNameTv = (TextView) view.findViewById(R.id.cardview_item_tit);
            collegeNameTv = (TextView) view.findViewById(R.id.cardview_item_colloge_tv);
            addrTv = (TextView) view.findViewById(R.id.cardview_item_addr_tv);
            sendTimeTv = (TextView) view.findViewById(R.id.cardview_item_sendtime_tv);
            dayTv = (TextView) view.findViewById(R.id.cardview_item_day_tv);
            mouthTv = (TextView) view.findViewById(R.id.cardview_item_mouth_tv);
            timeTv = (TextView) view.findViewById(R.id.cardview_item_time_tv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getPosition());
            }
        }
    }
}
