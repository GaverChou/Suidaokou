package com.gaverchou.suidaokou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.CurrentBookItem;
import com.gaverchou.suidaokou.model.OrgItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class HistoryBookListAdapter extends BaseAdapter {

    private List<CurrentBookItem> mOrgItems;

    public HistoryBookListAdapter() {
        mOrgItems = new ArrayList<>();
    }

    public HistoryBookListAdapter(List<CurrentBookItem> mOrgItems) {
        this.mOrgItems = mOrgItems;
    }

    @Override
    public int getCount() {
        return mOrgItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mOrgItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        CurrentBookItem selectItem = mOrgItems.get(pos);
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());
            holder = new ViewHolder();
            convertView = layoutInflator.inflate(
                    R.layout.history_book_item, null);
            holder.mNameTv = (TextView) convertView.findViewById(R.id.history_item_name_tv);
            holder.mAuthorTv = (TextView) convertView.findViewById(R.id.history_item_author_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mNameTv.setText(selectItem.mBookName);
        holder.mAuthorTv.setText(selectItem.mAuthorName);
        return convertView;
    }

    class ViewHolder {
        TextView mNameTv;
        TextView mAuthorTv;
    }
}
