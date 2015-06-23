package com.gaverchou.suidaokou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.DrawerSelectItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mrgaver on 15-5-28.
 */
public class DrawerListAdapter extends SimpleRecyclerAdapter<DrawerSelectItem> {
    protected static final int HAS_ICON = 0;
    protected static final int NO_ICON = 1;

    public DrawerListAdapter(Context context, ArrayList<DrawerSelectItem> mInfos) {
        super(context, mInfos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public int getItemViewType(int pos) {
        // 根据position元素返回View的类型, type值是从0开始排序的
        int type = mItems.get(pos).mType == DrawerSelectItem.HAS_ICON ? HAS_ICON : NO_ICON;
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HAS_ICON) {
            return new IconViewHolder(mInflater.inflate(R.layout.drawer_item, parent, false));
        } else {
            return new NOIconViewHolder(mInflater.inflate(R.layout.drawer_item_noicon, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof IconViewHolder) {
            IconViewHolder tmp = (IconViewHolder) viewHolder;
            tmp.mIcon.setBackgroundResource(mItems.get(position).mIconId);
            tmp.mTitle.setText(mItems.get(position).mTitleId);
            if ((position + 1) % 3 == 0) {
                tmp.divider.setVisibility(View.VISIBLE);
            }
        } else if (viewHolder instanceof NOIconViewHolder) {
            NOIconViewHolder tmp = (NOIconViewHolder) viewHolder;
            tmp.mTitle.setText(mItems.get(position).mTitleId);
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.drawer_item_icon)
        View mIcon;
        @InjectView(R.id.drawer_item_divider)
        View divider;
        @InjectView(R.id.drawer_item_tit_tv)
        TextView mTitle;
        @InjectView(R.id.ripple)
        View mRepple;

        public IconViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mRepple.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemOnclickListener != null) {
                itemOnclickListener.onItemClick(view, getPosition());
            }
        }
    }

    class NOIconViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;

        public NOIconViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.drawer_item_noicon_tit_tv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemOnclickListener != null) {
                itemOnclickListener.onItemClick(view, getPosition());
            }
        }
    }
}
