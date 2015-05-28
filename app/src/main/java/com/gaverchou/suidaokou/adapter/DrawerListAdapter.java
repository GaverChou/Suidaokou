package com.gaverchou.suidaokou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.DrawerSelectItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrgaver on 15-5-28.
 */
public class DrawerListAdapter extends BaseAdapter{

    private List<DrawerSelectItem> mSelectItems;
    public DrawerListAdapter() {
        super();
        mSelectItems = new ArrayList<DrawerSelectItem>();
    }

    public DrawerListAdapter(ArrayList<DrawerSelectItem> mInfos) {
        super();
        this.mSelectItems = mInfos;
    }

    public void addItem(DrawerSelectItem item) {
        this.mSelectItems.add(item);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSelectItems.size();
    }

    @Override
    public Object getItem(int pos) {
        return mSelectItems.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        DrawerSelectItem selectItem = mSelectItems.get(pos);
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());
            holder = new ViewHolder();
            if (selectItem.mType == DrawerSelectItem.HAS_ICON) {
                convertView = layoutInflator.inflate(
                        R.layout.drawer_item, null);
                holder.mIcon = convertView.findViewById(R.id.drawer_item_icon);
                holder.mTitle = (TextView) convertView.findViewById(R.id.drawer_item_tit_tv);
            } else {
                convertView = layoutInflator.inflate(
                        R.layout.drawer_item_noicon, null);
                holder.mTitle = (TextView) convertView.findViewById(R.id.drawer_item_noicon_tit_tv);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (selectItem.mType==DrawerSelectItem.HAS_ICON){
            holder.mIcon.setBackgroundResource(selectItem.mIconId);
            holder.mTitle.setText(selectItem.mTitleId);
        }else {
            holder.mTitle.setText(selectItem.mTitleId);
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int pos) {
        // 根据position元素返回View的类型, type值是从0开始排序的
        int type = mSelectItems.get(pos).mType == DrawerSelectItem.HAS_ICON ? 0 : 1;
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ViewHolder {
        View mIcon;
        TextView mTitle;
    }
}
