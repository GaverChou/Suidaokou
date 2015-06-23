package com.gaverchou.suidaokou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.OrgItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class OrgListAdapter extends BaseAdapter {

    private List<OrgItem> mOrgItems;

    public OrgListAdapter() {
        mOrgItems = new ArrayList<>();
    }

    public OrgListAdapter(List<OrgItem> mOrgItems) {
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
        OrgItem selectItem = mOrgItems.get(pos);
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());
            holder = new ViewHolder();
            convertView = layoutInflator.inflate(
                    R.layout.ognization_item, null);
            holder.mIcon = convertView.findViewById(R.id.org_item_icon_img);
            holder.mOrgNameTv = (TextView) convertView.findViewById(R.id.org_item_orgname_tv);
            holder.mDescriptionTv = (TextView) convertView.findViewById(R.id.org_item_descrip_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mDescriptionTv.setText(selectItem.mDescrip);
        holder.mOrgNameTv.setText(selectItem.mOrgName);
        return convertView;
    }

    class ViewHolder {
        View mIcon;
        TextView mOrgNameTv;
        TextView mDescriptionTv;
    }
}
