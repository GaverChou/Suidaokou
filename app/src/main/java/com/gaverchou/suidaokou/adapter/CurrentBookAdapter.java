/**
 * @author GaverChou E-mail:1123666456@qq.com
 * @version Create on 2015年5月14日 下午5:49:56
 * @description 
 */
package com.gaverchou.suidaokou.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.CurrentBookItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class CurrentBookAdapter extends BaseAdapter {
	private List<CurrentBookItem> mInfos;

	public CurrentBookAdapter() {
		super();
		mInfos = new ArrayList<CurrentBookItem>();
	}


	public CurrentBookAdapter(List<CurrentBookItem> mInfos) {
		super();
		this.mInfos = mInfos;
	}

	public void addItem(CurrentBookItem item) {
		this.mInfos.add(item);
		this.notifyDataSetChanged();
	}

	public void addAllItem(List<CurrentBookItem> mInfos) {
		this.mInfos.addAll(mInfos);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mInfos.size();
	}

	@Override
	public Object getItem(int pos) {
		return mInfos.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		CurrentBookItem currentBookItem = mInfos.get(pos);
		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(parent
					.getContext());
			convertView = layoutInflator.inflate(R.layout.currentbook_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(currentBookItem.mBookName);
		holder.hint.setText(currentBookItem.mHinte);
		holder.time.setText(currentBookItem.mBookTime);
		holder.expire.setText(currentBookItem.mExpireTime);
		holder.backGroundImg.setBackgroundResource(bg_arr[pos%bg_arr.length]);
		return convertView;
	}
     int[] bg_arr = {R.mipmap.book1,R.mipmap.book2,R.mipmap.book3,R.mipmap.book4};
	class ViewHolder {
		@InjectView(R.id.currentbook_item_txt_bookname)
		TextView name;
		@InjectView(R.id.currentbook_item_txt_hine)
		TextView hint;
		@InjectView(R.id.currentbook_item_txt_booktime)
		TextView time;
		@InjectView(R.id.currentbook_item_txt_expiretime)
		TextView expire;
		@InjectView(R.id.currentbook_item_imv)
		View backGroundImg;
		public ViewHolder(View view) {
			ButterKnife.inject(this,view);
		}
	}
}
