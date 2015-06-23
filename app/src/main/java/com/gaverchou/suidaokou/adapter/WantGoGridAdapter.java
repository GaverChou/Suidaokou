package com.gaverchou.suidaokou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.WantgoItem;

import java.util.ArrayList;

/**
 * Created by GaverChou on 2015-06-05.
 */
public class WantGoGridAdapter extends BaseAdapter {

    private ArrayList<WantgoItem> mItems = new ArrayList<>();
    private boolean isDetail;

    public WantGoGridAdapter() {
    }

    public void setDetail(boolean isDetail) {
        this.isDetail = isDetail;
    }

    public WantGoGridAdapter(ArrayList<WantgoItem> mItems) {
        this.mItems = mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null&&!isDetail) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());
            holder = new ViewHolder();
            convertView = layoutInflator.inflate(
                    R.layout.wango_item, null);
            holder.thumb = (ImageView)convertView.findViewById(R.id.wango_userimg);
            convertView.setTag(holder);
        }else if (convertView == null&&isDetail){
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());
            holder = new ViewHolder();
            convertView = layoutInflator.inflate(
                    R.layout.wango_detail_item, null);
            holder.thumb = (ImageView)convertView.findViewById(R.id.wango_userimg);
            holder.username = (TextView)convertView.findViewById(R.id.wango_username_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.thumb.setImageResource(mItems.get(pos).imgId);
        return convertView;
    }

    class ViewHolder {
        ImageView thumb;
        TextView username;
    }
}
