package com.gaverchou.suidaokou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.ZanCommentItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by GaverChou on 2015-05-29.
 */
public class ZanCommentListAdapter extends BaseAdapter {
    private ArrayList<ZanCommentItem> zanList;

    public ZanCommentListAdapter() {
        zanList = new ArrayList<>();
    }

    public ZanCommentListAdapter(ArrayList<ZanCommentItem> zanList) {
        this.zanList = zanList;
    }

    @Override
    public int getCount() {
        return zanList.size();
    }

    @Override
    public Object getItem(int i) {
        return zanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ZanCommentItem zanItem = zanList.get(pos);
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());
            convertView = layoutInflator.inflate(
                    R.layout.sixin_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(zanItem.mTitleStr);
        holder.contentTv.setText(zanItem.mCommentStr);
        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.sixin_item_content_tv)
        TextView contentTv;
        @InjectView(R.id.sixin_item_name_tv)
        TextView nameTv;

        public ViewHolder(View view) {
            ButterKnife.inject(this,view);
        }
    }
}
