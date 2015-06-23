package com.gaverchou.suidaokou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaverchou.suidaokou.R;
import com.gaverchou.suidaokou.model.ActionItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by GaverChou on 2015-05-27.
 */
public class InformViewAdapter extends SimpleRecyclerAdapter<ActionItem> {
    public InformViewAdapter(Context context) {
        super(context, new ArrayList<ActionItem>());
    }

    public InformViewAdapter(Context context, ArrayList<ActionItem> titleList) {
        super(context, titleList);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardViewHolder(mInflater.inflate(R.layout.inform_item, parent, false));
    }

    int[] colorArr = {0xff01c756, 0xff4487fa, 0xfffaab44, 0xff45ecfc, 0xff4945fc, 0xfffc4545, 0xffb145fc};

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof  CardViewHolder){
            CardViewHolder holder = (CardViewHolder)viewHolder;
            holder.titLL.setBackgroundColor(colorArr[position%colorArr.length]);
        }
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @InjectView(R.id.inform_item_tit_tv)
        TextView titTv;
        @InjectView(R.id.inform_item_inform_tv1)
        TextView inforTv1;
        @InjectView(R.id.inform_item_inform_tv2)
        TextView inforTv2;
        @InjectView(R.id.inform_item_inform_tv3)
        TextView inforTv3;
        @InjectView(R.id.inform_item_tit_ll)
        View titLL;

        public CardViewHolder(View convertView) {
            super(convertView);
            ButterKnife.inject(this, convertView);
            convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemOnclickListener!=null){
                itemOnclickListener.onItemClick(view,getPosition());
            }
        }
    }
}
